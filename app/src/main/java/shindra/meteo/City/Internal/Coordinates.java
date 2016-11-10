package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class Coordinates {

    @SerializedName("lon")
    private String myLongitude;
    @SerializedName("lat")
    private String myLatitude;



    public String getMyLongitude() {
        return myLongitude;
    }

    public String getMyLatitude() {
        return myLatitude;
    }

}
