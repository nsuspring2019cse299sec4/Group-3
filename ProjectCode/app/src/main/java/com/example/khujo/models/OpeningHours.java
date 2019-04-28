
package com.example.play.bazarmind.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OpeningHours implements Parcelable, Serializable
{

    @SerializedName("open_now")
    @Expose
    private boolean openNow;
    public final static Creator<OpeningHours> CREATOR = new Creator<OpeningHours>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OpeningHours createFromParcel(Parcel in) {
            return new OpeningHours(in);
        }

        public OpeningHours[] newArray(int size) {
            return (new OpeningHours[size]);
        }

    }
    ;

    protected OpeningHours(Parcel in) {
        this.openNow = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public OpeningHours() {
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(openNow);
    }

    public int describeContents() {
        return  0;
    }

}
