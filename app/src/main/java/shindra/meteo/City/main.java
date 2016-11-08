package shindra.meteo.City;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class Main {

    @SerializedName("temp")
    private double aTemperatureMetric;
    @SerializedName("pressure")
    private int aPressure;
    @SerializedName("humidity")
    private int aHumidityPercent;
    @SerializedName("temp_mini")
    private double aTempMiniMetric;
    @SerializedName("temp_Max")
    private double aTempMaxMetric;
}
