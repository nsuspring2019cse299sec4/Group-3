
package com.example.play.bazarmind.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Period implements Parcelable, Serializable
{

    @SerializedName("close")
    @Expose
    private Close close;
    @SerializedName("open")
    @Expose
    private Open open;
    public final static Creator<Period> CREATOR = new Creator<Period>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }

        public Period[] newArray(int size) {
            return (new Period[size]);
        }

    }
    ;

    protected Period(Parcel in) {
        this.close = ((Close) in.readValue((Close.class.getClassLoader())));
        this.open = ((Open) in.readValue((Open.class.getClassLoader())));
    }

    public Period() {
    }

    public Close getClose() {
        return close;
    }

    public void setClose(Close close) {
        this.close = close;
    }

    public Open getOpen() {
        return open;
    }

    public void setOpen(Open open) {
        this.open = open;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(close);
        dest.writeValue(open);
    }

    public int describeContents() {
        return  0;
    }

}
