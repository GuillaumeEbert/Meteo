package shindra.meteo.City;

import com.google.gson.annotations.SerializedName;

import java.lang.*;

import shindra.meteo.City.Internal.Clouds;
import shindra.meteo.City.Internal.Coordinates;
import shindra.meteo.City.Internal.Main;
import shindra.meteo.City.Internal.RainVolume;
import shindra.meteo.City.Internal.SnowVolume;
import shindra.meteo.City.Internal.Sys;
import shindra.meteo.City.Internal.Wind;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class City {
    private Sys mySys;
    private Main myMain;
    private Wind myWind;
    private Clouds myClouds;
    private RainVolume myRainVolume;
    private SnowVolume mySnowVolume;
    private Coordinates myCoordinates;

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


    public void setCoordinates(Coordinates myCoordinates) {
        this.myCoordinates = myCoordinates;
    }

    public void setMain(Main myMain) {
        this.myMain = myMain;
    }


    public Wind getWind() {
        return myWind;
    }

    public void setWind(Wind myWind) {
        this.myWind = myWind;
    }

    public Clouds getClouds() {
        return myClouds;
    }

    public void setClouds(Clouds myClouds) {
        this.myClouds = myClouds;
    }

    public RainVolume getRainVolume() {
        return myRainVolume;
    }

    public void setRainVolume(RainVolume myRainVolume) {
        this.myRainVolume = myRainVolume;
    }

    public SnowVolume getSnowVolume() {
        return mySnowVolume;
    }

    public void setSnowVolume(SnowVolume mySnowVolume) {
        this.mySnowVolume = mySnowVolume;
    }

    public Sys getSys() {
        return mySys;
    }

    public void setSys(Sys mySys) {
        this.mySys = mySys;
    }

}
