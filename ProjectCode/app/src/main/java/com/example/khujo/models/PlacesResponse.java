
package com.example.play.bazarmind.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacesResponse implements Serializable, Parcelable {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("next_page_token")
    @Expose
    private String nextPageToken;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("error_message")
    @Expose
    private String error_message;

    public PlacesResponse() {
    }



    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.htmlAttributions);
        dest.writeString(this.nextPageToken);
        dest.writeTypedList(this.results);
        dest.writeString(this.status);
        dest.writeString(this.error_message);
    }

    protected PlacesResponse(Parcel in) {
        this.htmlAttributions = new ArrayList<Object>();
        in.readList(this.htmlAttributions, Object.class.getClassLoader());
        this.nextPageToken = in.readString();
        this.results = in.createTypedArrayList(Result.CREATOR);
        this.status = in.readString();
        this.error_message = in.readString();
    }

    public static final Creator<PlacesResponse> CREATOR = new Creator<PlacesResponse>() {
        @Override
        public PlacesResponse createFromParcel(Parcel source) {
            return new PlacesResponse(source);
        }

        @Override
        public PlacesResponse[] newArray(int size) {
            return new PlacesResponse[size];
        }
    };
}
