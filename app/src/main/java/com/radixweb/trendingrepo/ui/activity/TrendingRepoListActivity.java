package com.radixweb.trendingrepo.ui.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radixweb.trendingrepo.R;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.databinding.TrendingRepoListActivityBinding;
import com.radixweb.trendingrepo.factory.ViewModelFactory;
import com.radixweb.trendingrepo.ui.adapter.FilterListAdapter;
import com.radixweb.trendingrepo.ui.adapter.TrendRepoAdapter;
import com.radixweb.trendingrepo.ui.custom.SwipeController;
import com.radixweb.trendingrepo.ui.custom.SwipeControllerActions;
import com.radixweb.trendingrepo.ui.custom.recyclerview.RecyclerItemClickListener;
import com.radixweb.trendingrepo.ui.custom.recyclerview.RecyclerLayoutClickListener;
import com.radixweb.trendingrepo.ui.custom.recyclerview.RecyclerViewPaginator;
import com.radixweb.trendingrepo.ui.viewmodel.TrendRepoListViewModel;
import com.radixweb.trendingrepo.utils.AnimUtils;
import com.radixweb.trendingrepo.utils.ShareUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class TrendingRepoListActivity extends AppCompatActivity implements RecyclerLayoutClickListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private TrendingRepoListActivityBinding binding;
    private TrendRepoListViewModel githubListViewModel;

    private TrendRepoAdapter githubListAdapter;
    private FilterListAdapter filterListAdapter;

    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        initialiseViewModel();
        initialiseView();
    }

    private void initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tranding_repo_list);
        setSupportActionBar(binding.mainToolbar.toolbar);

        filterListAdapter = new FilterListAdapter(Arrays.asList(getResources().getStringArray(R.array.list_filters)));
        binding.filterList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.filterList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), (parentView, childView, position) -> {
            filterListAdapter.updateSelection(position);
            githubListAdapter.getFilter().filter(filterListAdapter.getItem(position));
        }));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        githubListAdapter = new TrendRepoAdapter(getApplicationContext(),this);
        binding.recyclerView.setAdapter(githubListAdapter);
        binding.recyclerView.addOnScrollListener(new RecyclerViewPaginator(binding.recyclerView) {
            @Override
            public boolean isLastPage() {
                return githubListViewModel.isLastPage();
            }

            @Override
            public void loadMore() {
                githubListViewModel.fetchRepositories();
            }
        });

        if (githubListViewModel.getRepositories().isEmpty()) {
            displayLoader();
            githubListViewModel.fetchRepositories();
        } else animateView(githubListViewModel.getRepositories());

        /// TODO : For swiping layout
        swipeController = new SwipeController(this,new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                sharePost(githubListAdapter.getItem(position));
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(binding.recyclerView);

        binding.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    private void initialiseViewModel() {
        githubListViewModel = new ViewModelProvider(this, viewModelFactory).get(TrendRepoListViewModel.class);
        githubListViewModel.getRepositoryListLiveData().observe(this, repositories -> {
            if (githubListAdapter.getItemCount() == 0) {
                if (!repositories.isEmpty()) {
                    animateView(repositories);

                } else displayEmptyView();

            } else if (!repositories.isEmpty()) displayDataView(repositories);
        });
    }

    private void displayLoader() {
        binding.viewLoader.rootView.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        binding.viewLoader.rootView.setVisibility(View.GONE);
    }


    private void animateView(List<TrendRepoEntity> repositories) {
        hideLoader();
        AnimUtils.slideView(binding.filterLayout, binding.filterList, filterListAdapter);
        displayDataView(repositories);
        binding.recyclerView.scheduleLayoutAnimation();
    }

    private void displayDataView(List<TrendRepoEntity> repositories) {
        binding.viewEmpty.emptyContainer.setVisibility(View.GONE);
        githubListAdapter.setItems(repositories);
    }

    private void displayEmptyView() {
        hideLoader();
        binding.viewEmpty.emptyContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void sharePost(TrendRepoEntity trendRepoEntity) {
        ShareUtils.shareUrl(this, trendRepoEntity.getHtmlUrl());
    }
}