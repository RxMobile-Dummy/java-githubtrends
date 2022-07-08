package com.radixweb.trendingrepo.di.component;

import android.app.Application;

import com.radixweb.trendingrepo.AppController;
import com.radixweb.trendingrepo.di.module.ActivityModule;
import com.radixweb.trendingrepo.di.module.ApiModule;
import com.radixweb.trendingrepo.di.module.DbModule;
import com.radixweb.trendingrepo.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        ApiModule.class,
        DbModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }


    void inject(AppController appController);
}


