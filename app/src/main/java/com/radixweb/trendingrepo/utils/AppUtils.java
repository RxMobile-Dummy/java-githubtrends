package com.radixweb.trendingrepo.utils;

import static com.radixweb.trendingrepo.AppConstants.COLOR_LANGUAGE_MAP;
import static com.radixweb.trendingrepo.AppConstants.DATE_TIME_FORMAT;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;

import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import com.radixweb.trendingrepo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppUtils {

    /**
     * @param dateString to get DATE according to DATE_TIME_FORMAT format.
     * @return convert date according to given format.
     */
    public static String getDate(String dateString) {

        try {
            SimpleDateFormat format1 = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            return sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "xx";
        }
    }

    /**
     * @param dateString to get TIME according to DATE_TIME_FORMAT format.
     * @return convert time according to given format.
     */
    public static String getTime(String dateString) {

        try {
            SimpleDateFormat format1 = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("h:mm a");
            Date netDate = (date);
            return sdf.format(netDate);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "xx";
        }
    }

    /**
     * @param context which contain application context.
     * @param imageView which contain the imageview to manage the animation.
     * @param titleView which contain the textview to manage the animation.
     * @param revealView which contain the constraint to manage the animation.
     * @param languageView which contain the textview to manage the animation.
     * @return apply the animation according to the pair of element of array
     */
    public static Pair[] getTransitionElements(Context context,
                                               View imageView,
                                               View titleView,
                                               View revealView,
                                               View languageView) {
        List<Pair<View, String>> pairs = new ArrayList<>();
        pairs.add(new Pair(imageView, context.getString(R.string.transition_image)));
        pairs.add(new Pair(titleView, context.getString(R.string.transition_title)));
        if (revealView.getVisibility() == View.VISIBLE)
            pairs.add(new Pair(revealView, context.getString(R.string.transition_background)));
        if (languageView.getVisibility() == View.VISIBLE)
            pairs.add(new Pair(languageView, context.getString(R.string.transition_language)));

        Pair[] pairArr = new Pair[pairs.size()];
        pairArr = pairs.toArray(pairArr);
        return pairArr;
    }

    /**
     * @param context which contain application context.
     * @param language which follow according to selection of language.
     * @return provide the color according to selection of the language.
     */
    public static int getColorByLanguage(Context context,
                                         String language) {
        if (COLOR_LANGUAGE_MAP.containsKey(language))
            return ContextCompat.getColor(context, COLOR_LANGUAGE_MAP.get(language));
        else return ContextCompat.getColor(context, R.color.colorPrimary);
    }

    /**
     * @param activity contain current activity.
     * @param color contain selected color apply to the statusBar color.
     */
    public static void updateStatusBarColor(Activity activity,
                                            int color) {
        activity.getWindow().setStatusBarColor(color);
    }


    /**
     * @param color contain selected color
     * @param fraction contain fraction to apply on colors.
     * @return apply lighten color using color and fraction
     */
    public static int lighten(int color, double fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        red = lightenColor(red, fraction);
        green = lightenColor(green, fraction);
        blue = lightenColor(blue, fraction);
        int alpha = Color.alpha(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * @param color contain selected color
     * @param fraction contain fraction to apply on colors.
     * @return apply calculation on brighten color using color and fraction
     */
    private static int lightenColor(int color, double fraction) {
        return (int) Math.min(color + (color * fraction), 255);
    }

    /**
     * @param stateListDrawable to update button using this drawable.
     * @param bgColor apply this color to the drawable.
     * @return update the button drawable.
     */
    public static Drawable updateStateListDrawableColor(Drawable stateListDrawable,
                                                        int bgColor) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
        Drawable[] children = drawableContainerState.getChildren();
        GradientDrawable selectedDrawable = (GradientDrawable) children[0];
        GradientDrawable unselectedDrawable = (GradientDrawable) children[1];
        selectedDrawable.setColor(AppUtils.lighten(bgColor, 0.1));
        unselectedDrawable.setColor(bgColor);
        return stateListDrawable;
    }

    /**
     * @param stateListDrawable to update button using this drawable.
     * @param bgColor apply this color to the drawable Stroke.
     * @return update the button drawable Stroke.
     */
    public static Drawable updateStateListDrawableStrokeColor(Drawable stateListDrawable,
                                                              int bgColor) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
        Drawable[] children = drawableContainerState.getChildren();
        GradientDrawable selectedDrawable = (GradientDrawable) children[0];
        GradientDrawable unselectedDrawable = (GradientDrawable) children[1];
        selectedDrawable.setColor(AppUtils.lighten(bgColor, 0.1));
        unselectedDrawable.setStroke(1, bgColor);
        return stateListDrawable;
    }

}
