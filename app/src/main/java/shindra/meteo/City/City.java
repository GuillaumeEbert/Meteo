package shindra.meteo.City;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.lang.*;


import shindra.meteo.City.Internal.Coordinates;
import shindra.meteo.City.Internal.Main;
import shindra.meteo.City.Internal.Sys;
import shindra.meteo.City.Internal.Weather;


/**
 * Created by Guillaume on 08/11/2016.
 */
public class City implements Parcelable{
    private Sys mySys;
    private Main myMain;
    private Coordinates myCoordinates;
    private Weather myWeathers;

    @SerializedName("visibility")
    private int myVisibility;
    @SerializedName("id")
    private int myId;
    @SerializedName("name")
    private String myName;
    @SerializedName("cod")
    private int myCod;


    protected City(Parcel in) {
        myMain = in.readParcelable(Main.class.getClassLoader());
        myWeathers = in.readParcelable(Weather.class.getClassLoader());
        myVisibility = in.readInt();
        myId = in.readInt();
        myName = in.readString();
        myCod = in.readInt();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String geName() {
        return myName;
    }

    public Coordinates getCoordinates() {
        return myCoordinates;
    }

    public Main getMain() {
        return myMain;
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

    public Sys getSys() {
        return mySys;
    }

    public void setSys(Sys mySys) {
        this.mySys = mySys;
    }

    public Weather getWeathers() {
        return myWeathers;
    }

    public void setMyWeathers(Weather myWeathers) {
        this.myWeathers = myWeathers;
    }

    public int getTemperature(){
        return myMain.getTemperature().intValue() ;
    }

    public int getHumidityPercent(){
        return myMain.getHumidityPercent();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(myMain, flags);
        dest.writeParcelable(myWeathers, flags);
        dest.writeInt(myVisibility);
        dest.writeInt(myId);
        dest.writeString(myName);
        dest.writeInt(myCod);
    }
}
