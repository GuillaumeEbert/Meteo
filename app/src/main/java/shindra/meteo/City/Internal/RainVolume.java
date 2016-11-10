package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class RainVolume {

    @SerializedName("3h")
    private double rainVolume;

    public double getRainVolume() {
        return rainVolume;
    }

    public void setRainVolume(double rainVolume) {
        this.rainVolume = rainVolume;
    }
}
