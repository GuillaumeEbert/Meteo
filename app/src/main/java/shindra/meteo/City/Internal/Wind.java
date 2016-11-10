package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class Wind {

    @SerializedName("speed")
    private double mySpeed;
    @SerializedName("deg")
    private double myDirection;

    public double getSpeed() {
        return mySpeed;
    }

    public void setSpeed(double mySpeed) {
        this.mySpeed = mySpeed;
    }

    public double getDirection() {
        return myDirection;
    }

    public void setDirection(double myDirection) {
        this.myDirection = myDirection;
    }

}
