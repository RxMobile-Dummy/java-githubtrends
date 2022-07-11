package com.radixweb.trendingrepo.ui.viewmodel;


import androidx.lifecycle.ViewModel;

import com.radixweb.trendingrepo.data.SingleLiveEvent;
import com.radixweb.trendingrepo.data.local.dao.TrendRepoDao;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.data.remote.api.TrendRepoApiServices;
import com.radixweb.trendingrepo.data.repository.TrendRepoRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TrendRepoListViewModel extends ViewModel {

    private Long currentPage = 0l;
    private TrendRepoRepository repository;

    private List<TrendRepoEntity> repositories = new ArrayList<>();
    private SingleLiveEvent<List<TrendRepoEntity>> repoListLiveData = new SingleLiveEvent<>();

    @Inject
    public TrendRepoListViewModel(TrendRepoDao trendRepoDao, TrendRepoApiServices trendRepoApiServices) {
        repository = new TrendRepoRepository(trendRepoDao, trendRepoApiServices);
    }

    public void fetchRepositories() {
        repository.getRepositories(++currentPage)
                .subscribe(resource -> {
                    if (resource.isLoaded()) {
                        repositories.addAll(resource.data);
                        getRepositoryListLiveData().postValue(resource.data);
                    }
                });
    }


    public List<TrendRepoEntity> getRepositories() {
        return repositories;
    }

    public SingleLiveEvent<List<TrendRepoEntity>>   getRepositoryListLiveData() {
        return repoListLiveData;
    }

    public boolean isLastPage() {
        return getRepositoryListLiveData().getValue() != null &&
                !getRepositoryListLiveData().getValue().isEmpty() ?
                getRepositoryListLiveData().getValue().get(0).isLastPage() :
                false;
    }
}

