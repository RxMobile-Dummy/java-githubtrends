package com.radixweb.trendingrepo.repository;

import static com.radixweb.trendingrepo.AppConstants.QUERY_ORDER;
import static com.radixweb.trendingrepo.AppConstants.QUERY_SORT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.radixweb.trendingrepo.data.Resource;
import com.radixweb.trendingrepo.data.local.dao.TrendRepoDao;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.data.remote.api.TrendRepoApiServices;
import com.radixweb.trendingrepo.data.repository.TrendRepoRepository;
import com.radixweb.trendingrepo.util.MockTestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;


@RunWith(MockitoJUnitRunner.class)
public class TrendingRepositoryTest {

    @Mock
    TrendRepoDao trendRepoDao;

    @Mock
    TrendRepoApiServices trendRepoApiServices;

    private TrendRepoRepository repository;

//    @Rule
//    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        repository = new TrendRepoRepository(trendRepoDao, trendRepoApiServices);
    }

    @Test
    public void loadPostsTest() {
        Long page = 1l;

        List<TrendRepoEntity> loadFromDB = MockTestUtil.mockRepositories();

        when(trendRepoDao.getRepositoriesByPage(page)).thenReturn(loadFromDB);

        when(trendRepoApiServices.fetchRepositories(QUERY_SORT, QUERY_ORDER, page)).thenReturn(Observable.empty());

        Observable<Resource<List<TrendRepoEntity>>> data = repository.getRepositories(page);
        verify(trendRepoDao).getRepositoriesByPage(page);
        verify(trendRepoApiServices).fetchRepositories(QUERY_SORT, QUERY_ORDER, page);

        TestObserver testObserver = new TestObserver();
        data.subscribe(testObserver);
        testObserver.assertNoErrors();
    }
}
