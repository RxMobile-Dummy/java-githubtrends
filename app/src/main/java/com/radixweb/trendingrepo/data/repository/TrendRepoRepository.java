package com.radixweb.trendingrepo.data.repository;


import static com.radixweb.trendingrepo.AppConstants.QUERY_ORDER;
import static com.radixweb.trendingrepo.AppConstants.QUERY_SORT;


import androidx.annotation.NonNull;

import com.radixweb.trendingrepo.data.NetworkBoundResource;
import com.radixweb.trendingrepo.data.Resource;
import com.radixweb.trendingrepo.data.local.dao.TrendRepoDao;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.data.remote.api.TrendRepoApiServices;
import com.radixweb.trendingrepo.data.remote.model.TrendRepoApiResponse;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class TrendRepoRepository {

    private TrendRepoDao trendRepoDao;
    private TrendRepoApiServices trendRepoApiServices;

    public TrendRepoRepository(TrendRepoDao trendRepoDao, TrendRepoApiServices trendRepoApiServices) {
        this.trendRepoDao = trendRepoDao;
        this.trendRepoApiServices = trendRepoApiServices;
    }

    /**
     * @param page contain page number
     * @return fetch repository details from the API using page field.
     */
    public Observable<Resource<List<TrendRepoEntity>>> getRepositories(Long page) {
        return new NetworkBoundResource<List<TrendRepoEntity>, TrendRepoApiResponse>() {

            @Override
            protected void saveCallResult(@NonNull TrendRepoApiResponse item) {
                List<TrendRepoEntity> repositories = item.getItems();
                for (TrendRepoEntity TrendRepoEntity : repositories) {
                    TrendRepoEntity.setPage(page);
                    TrendRepoEntity.setTotalPages(item.getTotalCount());
                }
                trendRepoDao.insertRepositories(repositories);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<TrendRepoEntity>> loadFromDb() {
                List<TrendRepoEntity> repositories = trendRepoDao.getRepositoriesByPage(page);
                return (repositories == null || repositories.isEmpty()) ?
                        Flowable.empty() : Flowable.just(repositories);
            }

            @NonNull
            @Override
            protected Observable<Resource<TrendRepoApiResponse>> createCall() {
                return trendRepoApiServices.fetchRepositories(QUERY_SORT, QUERY_ORDER, page)
                        .flatMap(response ->
                                Observable.just(response.isSuccessful()
                                        ? Resource.success(response.body())
                                        : Resource.error("", new TrendRepoApiResponse())));
            }

        }.getAsObservable();
    }
}

