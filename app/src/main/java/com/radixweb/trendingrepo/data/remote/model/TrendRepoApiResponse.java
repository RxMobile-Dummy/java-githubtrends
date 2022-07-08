package com.radixweb.trendingrepo.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;

import java.util.ArrayList;
import java.util.List;

public class TrendRepoApiResponse implements Parcelable {

    public TrendRepoApiResponse() {
        this.items = new ArrayList<>();
    }

    @SerializedName("total_count")
    private Long totalCount;

    private List<TrendRepoEntity> items;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<TrendRepoEntity> getItems() {
        return items;
    }

    public void setItems(List<TrendRepoEntity> items) {
        this.items = items;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.totalCount);
        dest.writeTypedList(this.items);
    }

    protected TrendRepoApiResponse(Parcel in) {
        this.totalCount = (Long) in.readValue(Long.class.getClassLoader());
        this.items = in.createTypedArrayList(TrendRepoEntity.CREATOR);
    }

    public static final Creator<TrendRepoApiResponse> CREATOR = new Creator<TrendRepoApiResponse>() {
        @Override
        public TrendRepoApiResponse createFromParcel(Parcel source) {
            return new TrendRepoApiResponse(source);
        }

        @Override
        public TrendRepoApiResponse[] newArray(int size) {
            return new TrendRepoApiResponse[size];
        }
    };


}
