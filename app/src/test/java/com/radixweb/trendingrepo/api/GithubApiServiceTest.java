package com.radixweb.trendingrepo.api;

import com.radixweb.trendingrepo.data.remote.api.TrendRepoApiServices;
import com.radixweb.trendingrepo.data.remote.model.TrendRepoApiResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

public class GithubApiServiceTest extends ApiAbstract<TrendRepoApiServices> {

    private TrendRepoApiServices service;

    @Before
    public void initService() {
        this.service = createService(TrendRepoApiServices.class);
    }

    @Test
    public void fetchPostsTest() throws IOException {
        enqueueResponse("test_repositories.json");
        Response<TrendRepoApiResponse> response = service.fetchRepositories("stars", "desc", 1L).blockingFirst();
        Assert.assertTrue(response.isSuccessful());

        TrendRepoApiResponse apiResponse = response.body();
        assert apiResponse != null;
        Assert.assertEquals(new Long(806201), apiResponse.getTotalCount());
        Assert.assertEquals(10, apiResponse.getItems().size());
    }
}
