package com.radixweb.trendingrepo.utils;


import static com.radixweb.trendingrepo.AppConstants.INTENT_POST;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;
import com.radixweb.trendingrepo.ui.activity.TrendingRepoDetailActivity;


public class NavigatorUtils {

    /**
     * @param activity contain current activity.
     * @param trendRepoEntity contain model data to pass between two activity.
     * @param options contain list of option view which need to apply animation.
     */
    public static void redirectToDetailScreen(Activity activity,
                                              TrendRepoEntity trendRepoEntity,
                                              ActivityOptionsCompat options) {

        Intent intent = new Intent(activity, TrendingRepoDetailActivity.class);
        intent.putExtra(INTENT_POST, trendRepoEntity);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    /**
     * @param activity contain current activity.
     * @param url contain URL which need to open in a webView.
     */
    public static void openBrowser(Activity activity,
                                   String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }
}
