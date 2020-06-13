package com.harish.lowes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ForecastModel implements Parcelable {
    public static final Creator<ForecastModel> CREATOR = new Creator<ForecastModel>() {
        @Override
        public ForecastModel createFromParcel(Parcel in) {
            return new ForecastModel(in);
        }

        @Override
        public ForecastModel[] newArray(int size) {
            return new ForecastModel[size];
        }
    };
    private String temp;
    private String feels_like;
    private String cloud;
    private String description;

    public ForecastModel() {
    }

    protected ForecastModel(Parcel in) {
        temp = in.readString();
        feels_like = in.readString();
        cloud = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(temp);
        dest.writeString(feels_like);
        dest.writeString(cloud);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(String feels_like) {
        this.feels_like = feels_like;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
