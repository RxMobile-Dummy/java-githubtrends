package com.radixweb.trendingrepo.data.local.converter;

import static com.radixweb.trendingrepo.AppConstants.DATE_TIME_FORMAT;

import android.annotation.SuppressLint;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TrendRepoConverter {

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);

    /**
     * @param value provide fromTime to convert according to TimeZone
     * @return converted according to TimeZone (IST)
     */
    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                TimeZone timeZone = TimeZone.getTimeZone("IST");
                df.setTimeZone(timeZone);
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param value provide date to convert according to TimeZone
     * @return converted according to TimeZone (IST)
     */
    @TypeConverter
    public static String dateToTimestamp(Date value) {
        TimeZone timeZone = TimeZone.getTimeZone("IST");
        df.setTimeZone(timeZone);
        return value == null ? null : df.format(value);
    }
}
