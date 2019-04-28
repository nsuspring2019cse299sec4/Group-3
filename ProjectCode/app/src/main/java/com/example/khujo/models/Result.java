
package com.example.play.bazarmind.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable, Serializable
{

    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents = new ArrayList<>();
    @SerializedName("adr_address")
    @Expose
    private String adrAddress;
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("formatted_phone_number")
    @Expose
    private String formattedPhoneNumber;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("international_phone_number")
    @Expose
    private String internationalPhoneNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<>();
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("plus_code")
    @Expose
    private PlusCode plusCode;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = new ArrayList<>();
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<>();
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("utc_offset")
    @Expose
    private long utcOffset;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("website")
    @Expose
    private String website;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    protected Result(Parcel in) {
        in.readList(this.addressComponents, (com.example.play.bazarmind.models.AddressComponent.class.getClassLoader()));
        this.adrAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.formattedAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.formattedPhoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.internationalPhoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.openingHours = ((OpeningHours) in.readValue((OpeningHours.class.getClassLoader())));
        in.readList(this.photos, (com.example.play.bazarmind.models.Photo.class.getClassLoader()));
        this.placeId = ((String) in.readValue((String.class.getClassLoader())));
        this.plusCode = ((PlusCode) in.readValue((PlusCode.class.getClassLoader())));
        this.rating = ((double) in.readValue((double.class.getClassLoader())));
        this.reference = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.reviews, (com.example.play.bazarmind.models.Review.class.getClassLoader()));
        this.scope = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.types, (String.class.getClassLoader()));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.utcOffset = ((long) in.readValue((long.class.getClassLoader())));
        this.vicinity = ((String) in.readValue((String.class.getClassLoader())));
        this.website = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getAdrAddress() {
        return adrAddress;
    }

    public void setAdrAddress(String adrAddress) {
        this.adrAddress = adrAddress;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(long utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addressComponents);
        dest.writeValue(adrAddress);
        dest.writeValue(formattedAddress);
        dest.writeValue(formattedPhoneNumber);
        dest.writeValue(geometry);
        dest.writeValue(icon);
        dest.writeValue(id);
        dest.writeValue(internationalPhoneNumber);
        dest.writeValue(name);
        dest.writeValue(openingHours);
        dest.writeList(photos);
        dest.writeValue(placeId);
        dest.writeValue(plusCode);
        dest.writeValue(rating);
        dest.writeValue(reference);
        dest.writeList(reviews);
        dest.writeValue(scope);
        dest.writeList(types);
        dest.writeValue(url);
        dest.writeValue(utcOffset);
        dest.writeValue(vicinity);
        dest.writeValue(website);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (Double.compare(result.getRating(), getRating()) != 0) return false;
        if (getUtcOffset() != result.getUtcOffset()) return false;
        if (getAddressComponents() != null ? !getAddressComponents().equals(result.getAddressComponents()) : result.getAddressComponents() != null)
            return false;
        if (getAdrAddress() != null ? !getAdrAddress().equals(result.getAdrAddress()) : result.getAdrAddress() != null)
            return false;
        if (getFormattedAddress() != null ? !getFormattedAddress().equals(result.getFormattedAddress()) : result.getFormattedAddress() != null)
            return false;
        if (getFormattedPhoneNumber() != null ? !getFormattedPhoneNumber().equals(result.getFormattedPhoneNumber()) : result.getFormattedPhoneNumber() != null)
            return false;
        if (getGeometry() != null ? !getGeometry().equals(result.getGeometry()) : result.getGeometry() != null)
            return false;
        if (getIcon() != null ? !getIcon().equals(result.getIcon()) : result.getIcon() != null)
            return false;
        if (getId() != null ? !getId().equals(result.getId()) : result.getId() != null)
            return false;
        if (getInternationalPhoneNumber() != null ? !getInternationalPhoneNumber().equals(result.getInternationalPhoneNumber()) : result.getInternationalPhoneNumber() != null)
            return false;
        if (getName() != null ? !getName().equals(result.getName()) : result.getName() != null)
            return false;
        if (getOpeningHours() != null ? !getOpeningHours().equals(result.getOpeningHours()) : result.getOpeningHours() != null)
            return false;
        if (getPhotos() != null ? !getPhotos().equals(result.getPhotos()) : result.getPhotos() != null)
            return false;
        if (getPlaceId() != null ? !getPlaceId().equals(result.getPlaceId()) : result.getPlaceId() != null)
            return false;
        if (getPlusCode() != null ? !getPlusCode().equals(result.getPlusCode()) : result.getPlusCode() != null)
            return false;
        if (getReference() != null ? !getReference().equals(result.getReference()) : result.getReference() != null)
            return false;
        if (getReviews() != null ? !getReviews().equals(result.getReviews()) : result.getReviews() != null)
            return false;
        if (getScope() != null ? !getScope().equals(result.getScope()) : result.getScope() != null)
            return false;
        if (getTypes() != null ? !getTypes().equals(result.getTypes()) : result.getTypes() != null)
            return false;
        if (getUrl() != null ? !getUrl().equals(result.getUrl()) : result.getUrl() != null)
            return false;
        if (getVicinity() != null ? !getVicinity().equals(result.getVicinity()) : result.getVicinity() != null)
            return false;
        return getWebsite() != null ? getWebsite().equals(result.getWebsite()) : result.getWebsite() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getAddressComponents() != null ? getAddressComponents().hashCode() : 0;
        result = 31 * result + (getAdrAddress() != null ? getAdrAddress().hashCode() : 0);
        result = 31 * result + (getFormattedAddress() != null ? getFormattedAddress().hashCode() : 0);
        result = 31 * result + (getFormattedPhoneNumber() != null ? getFormattedPhoneNumber().hashCode() : 0);
        result = 31 * result + (getGeometry() != null ? getGeometry().hashCode() : 0);
        result = 31 * result + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getInternationalPhoneNumber() != null ? getInternationalPhoneNumber().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getOpeningHours() != null ? getOpeningHours().hashCode() : 0);
        result = 31 * result + (getPhotos() != null ? getPhotos().hashCode() : 0);
        result = 31 * result + (getPlaceId() != null ? getPlaceId().hashCode() : 0);
        result = 31 * result + (getPlusCode() != null ? getPlusCode().hashCode() : 0);
        temp = Double.doubleToLongBits(getRating());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getReference() != null ? getReference().hashCode() : 0);
        result = 31 * result + (getReviews() != null ? getReviews().hashCode() : 0);
        result = 31 * result + (getScope() != null ? getScope().hashCode() : 0);
        result = 31 * result + (getTypes() != null ? getTypes().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (int) (getUtcOffset() ^ (getUtcOffset() >>> 32));
        result = 31 * result + (getVicinity() != null ? getVicinity().hashCode() : 0);
        result = 31 * result + (getWebsite() != null ? getWebsite().hashCode() : 0);
        return result;
    }
}
