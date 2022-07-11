package com.radixweb.trendingrepo.ui.custom.recyclerview;

import android.view.View;

import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;

public interface RecyclerLayoutClickListener {

    void redirectToDetailScreen(View imageView,
                                View titleView,
                                View revealView,
                                View languageView,
                                TrendRepoEntity trendRepoEntity);

    void sharePost(TrendRepoEntity trendRepoEntity);
}
