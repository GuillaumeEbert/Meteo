package shindra.meteo.City.Internal;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class Weather implements Parcelable {

    @SerializedName("id")
    int myId;
    @SerializedName("main")
    private String myMainWeather;
    @SerializedName("description")
    private String myDescription;
    @SerializedName("icon")
    private String myIconId;
    private Bitmap myIconImg;

    protected Weather(Parcel in) {
        myId = in.readInt();
        myMainWeather = in.readString();
        myDescription = in.readString();
        myIconId = in.readString();
        myIconImg = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(myId);
        dest.writeString(myMainWeather);
        dest.writeString(myDescription);
        dest.writeString(myIconId);
        dest.writeParcelable(myIconImg, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public Bitmap getIconImg() {
        return myIconImg;
    }

    public void setIconImg(Bitmap myIconImg) {
        this.myIconImg = myIconImg;
    }

    public int getId() {
        return myId;
    }

    public String getIconId() {
        return myIconId;
    }

    public String getDescription() {
        return myDescription;
    }

    public String getMainWeather() {
        return myMainWeather;
    }
}
