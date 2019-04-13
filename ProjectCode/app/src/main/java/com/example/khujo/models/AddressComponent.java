
package com.example.khujo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressComponent implements Parcelable, Serializable
{

    @SerializedName("long_name")
    @Expose
    private String longName;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<>();
    public final static Creator<AddressComponent> CREATOR = new Creator<AddressComponent>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AddressComponent createFromParcel(Parcel in) {
            return new AddressComponent(in);
        }

        public AddressComponent[] newArray(int size) {
            return (new AddressComponent[size]);
        }

    }
    ;

    protected AddressComponent(Parcel in) {
        this.longName = ((String) in.readValue((String.class.getClassLoader())));
        this.shortName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.types, (String.class.getClassLoader()));
    }

    public AddressComponent() {
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(longName);
        dest.writeValue(shortName);
        dest.writeList(types);
    }

    public int describeContents() {
        return  0;
    }

}