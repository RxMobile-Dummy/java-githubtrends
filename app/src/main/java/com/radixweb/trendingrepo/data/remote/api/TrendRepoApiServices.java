package com.radixweb.trendingrepo.data.remote.api;

import com.radixweb.trendingrepo.data.remote.model.TrendRepoApiResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrendRepoApiServices {

    @GET("search/repositories")
    Observable<Response<TrendRepoApiResponse>> fetchRepositories(@Query("sort") String sort,
                                                                 @Query("order") String order,
                                                                 @Query("page") Long page);
}
