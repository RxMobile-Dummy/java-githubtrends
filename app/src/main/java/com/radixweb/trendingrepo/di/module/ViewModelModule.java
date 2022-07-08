package com.radixweb.trendingrepo.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.radixweb.trendingrepo.di.ViewModelKey;
import com.radixweb.trendingrepo.factory.ViewModelFactory;
import com.radixweb.trendingrepo.ui.viewmodel.TrendRepoListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(TrendRepoListViewModel.class)
    protected abstract ViewModel githubListViewModel(TrendRepoListViewModel githubListViewModel);
}