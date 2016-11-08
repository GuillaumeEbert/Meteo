package shindra.meteo.City;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class City {

    private Coordinates myCoordinates;
    private Main myMain;


    @SerializedName("visibility")
    private int myVisibility;
    @SerializedName("id")
    private int myId;
    @SerializedName("name")
    private String myName;
    @SerializedName("cod")
    private int myCod;


    public String geName() {
        return myName;
    }

    public int getMyCod() {
        return myCod;
    }

    public Coordinates getCoordinates() {
        return myCoordinates;
    }

    public Main getMain() {
        return myMain;
    }

    public int getVisibility() {
        return myVisibility;
    }

    public int getId() {
        return myId;
    }


}
