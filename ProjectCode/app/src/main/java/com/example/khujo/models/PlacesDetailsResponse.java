
package com.example.play.bazarmind.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacesDetailsResponse implements Parcelable, Serializable
{

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = new ArrayList<>();
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Creator<PlacesDetailsResponse> CREATOR = new Creator<PlacesDetailsResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PlacesDetailsResponse createFromParcel(Parcel in) {
            return new PlacesDetailsResponse(in);
        }

        public PlacesDetailsResponse[] newArray(int size) {
            return (new PlacesDetailsResponse[size]);
        }

    }
    ;

    protected PlacesDetailsResponse(Parcel in) {
        in.readList(this.htmlAttributions, (Object.class.getClassLoader()));
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PlacesDetailsResponse() {
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(htmlAttributions);
        dest.writeValue(result);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
