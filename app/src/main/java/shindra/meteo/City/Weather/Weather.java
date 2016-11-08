package shindra.meteo.City.Weather;

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
