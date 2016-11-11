package shindra.meteo.City.Internal;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class Weather {

    @SerializedName("id")
    int myId;
    @SerializedName("main")
    String myMainWeather;
    @SerializedName("description")
    String myDescription;
    @SerializedName("icon")
    String myIconId;

    public Bitmap getIconImg() {
        return myIconImg;
    }

    public void setIconImg(Bitmap myIconImg) {
        this.myIconImg = myIconImg;
    }

    Bitmap myIconImg;

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
