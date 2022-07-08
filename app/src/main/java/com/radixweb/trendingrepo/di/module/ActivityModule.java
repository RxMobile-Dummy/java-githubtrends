package com.radixweb.trendingrepo.di.module;

import com.radixweb.trendingrepo.ui.activity.TrendingRepoListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract TrendingRepoListActivity contributeGithubListActivity();
}