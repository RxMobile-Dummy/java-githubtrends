package com.radixweb.trendingrepo.util;

import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.data.remote.model.TrendRepoApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MockTestUtil {

    public static TrendRepoApiResponse mockApiResponse() {
        TrendRepoApiResponse apiResponse = new TrendRepoApiResponse();
        apiResponse.setTotalCount(1L);
        apiResponse.setItems(mockRepositories());
        return apiResponse;
    }

    public static List<TrendRepoEntity> mockRepositories() {
        List<TrendRepoEntity> repositories = new ArrayList<>();

        TrendRepoEntity repository1 = new TrendRepoEntity();
        repository1.setId(1L);
        repository1.setPage(1L);
        repository1.setTotalPages(10L);
        repository1.setName("AndroidTest1");
        repositories.add(repository1);

        TrendRepoEntity repository2 = new TrendRepoEntity();
        repository2.setId(2L);
        repository2.setPage(1L);
        repository2.setTotalPages(10L);
        repository2.setName("AndroidTest2");
        repositories.add(repository2);

        TrendRepoEntity repository3 = new TrendRepoEntity();
        repository3.setId(3L);
        repository3.setPage(2L);
        repository3.setTotalPages(10L);
        repository3.setName("AndroidTest3");
        repositories.add(repository3);

        return repositories;
    }
}
