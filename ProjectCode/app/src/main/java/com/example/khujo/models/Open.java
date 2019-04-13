
package com.example.khujo.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Open implements Parcelable, Serializable
{

    @SerializedName("day")
    @Expose
    private long day;
    @SerializedName("time")
    @Expose
    private String time;
    public final static Creator<Open> CREATOR = new Creator<Open>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Open createFromParcel(Parcel in) {
            return new Open(in);
        }

        public Open[] newArray(int size) {
            return (new Open[size]);
        }

    }
    ;

    protected Open(Parcel in) {
        this.day = ((long) in.readValue((long.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Open() {
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(day);
        dest.writeValue(time);
    }

    public int describeContents() {
        return  0;
    }

}
