package com.radixweb.trendingrepo.ui.activity;

import static com.radixweb.trendingrepo.AppConstants.INTENT_POST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.radixweb.trendingrepo.R;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.databinding.TrendingRepoDetailActivityBinding;
import com.radixweb.trendingrepo.utils.AppUtils;
import com.radixweb.trendingrepo.utils.NavigatorUtils;
import com.radixweb.trendingrepo.utils.ShareUtils;
import com.squareup.picasso.Picasso;

public class TrendingRepoDetailActivity extends AppCompatActivity {

    private TrendRepoEntity trendRepoEntity;
    private TrendingRepoDetailActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseView();
    }

    /**
     * initialization of views
     */
    private void initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trending_repo_detail);
        trendRepoEntity = getIntent().getParcelableExtra(INTENT_POST);

        Picasso.get().load(trendRepoEntity.getOwner().getAvatarUrl())
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.itemProfileImg);

        binding.itemTitle.setText(trendRepoEntity.getFullName());
        binding.itemStars.setText(String.valueOf(trendRepoEntity.getStarsCount()));
        binding.itemWatchers.setText(String.valueOf(trendRepoEntity.getWatchers()));
        binding.itemForks.setText(String.valueOf(trendRepoEntity.getForks()));

        if (trendRepoEntity.getLanguage() != null) {
            updateColorTheme();
        }

        binding.btnShare.setOnClickListener(v -> ShareUtils.shareUrl(TrendingRepoDetailActivity.this, trendRepoEntity.getHtmlUrl()));
        binding.btnVisit.setOnClickListener(v -> NavigatorUtils.openBrowser(TrendingRepoDetailActivity.this, trendRepoEntity.getHtmlUrl()));
    }

    /**
     * To update theme color to appbar & status bar
     */
    private void updateColorTheme() {
        int bgColor = AppUtils.getColorByLanguage(getApplicationContext(), trendRepoEntity.getLanguage());

        binding.appBarLayout.setBackgroundColor(bgColor);
        binding.mainToolbar.toolbar.setBackgroundColor(bgColor);

        binding.mainToolbar.toolbarTitle.setText(trendRepoEntity.getLanguage());

        binding.btnShare.setTextColor(bgColor);
        binding.btnVisit.setBackgroundDrawable(AppUtils.updateStateListDrawableColor(getResources().getDrawable(R.drawable.btn_visit), bgColor));
        binding.btnShare.setBackgroundDrawable(AppUtils.updateStateListDrawableStrokeColor(getResources().getDrawable(R.drawable.btn_share), bgColor));
        AppUtils.updateStatusBarColor(this, bgColor);
    }
}