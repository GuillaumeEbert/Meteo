package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class SnowVolume {
    @SerializedName("snow")
    private double mySnowVolume;

    public double getSnowVolume() {
        return mySnowVolume;
    }

    public void setSnowVolume(double mySnowVolume) {
        this.mySnowVolume = mySnowVolume;
    }
}
