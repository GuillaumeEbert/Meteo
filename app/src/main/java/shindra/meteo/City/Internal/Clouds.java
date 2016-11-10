package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class Clouds {

   @SerializedName("all")
    private double myCloudiness;

    public double getCloudiness() {
        return myCloudiness;
    }

    public void setCloudiness(double myCloudiness) {
        this.myCloudiness = myCloudiness;
    }
}
