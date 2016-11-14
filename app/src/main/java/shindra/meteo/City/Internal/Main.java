package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class Main {

    @SerializedName("temp")
    private double aTemperature;
    @SerializedName("pressure")
    private double aPressure;
    @SerializedName("humidity")
    private int aHumidityPercent;
    @SerializedName("temp_min")
    private double aTempMini;
    @SerializedName("temp_max")
    private double aTempMax;
}
