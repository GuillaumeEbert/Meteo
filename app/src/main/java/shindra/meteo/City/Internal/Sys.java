package shindra.meteo.City.Internal;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class Sys {
    @SerializedName("id")
    private int myId;
    @SerializedName("country")
    private String myCountry;
    @SerializedName("sunrise")
    private long mySunRiseTimeUTC;
    @SerializedName("sunset")
    private long mySunSetTimeUTC;

    public int getId() {
        return myId;
    }

    public void setId(int myId) {
        this.myId = myId;
    }

    public String getCountry() {
        return myCountry;
    }

    public void setCountry(String myCountry) {
        this.myCountry = myCountry;
    }

    public long getSunRiseTimeUTC() {
        return mySunRiseTimeUTC;
    }

    public void setSunRiseTimeUTC(long mySunRiseTimeUTC) {
        this.mySunRiseTimeUTC = mySunRiseTimeUTC;
    }

    public long getSunSetTimeUTC() {
        return mySunSetTimeUTC;
    }

    public void setSunSetTimeUTC(long mySunSetTimeUTC) {
        this.mySunSetTimeUTC = mySunSetTimeUTC;
    }


}



/*"type":1,
      "id":5657,
      "message":0.4116,
      "country":"FR",
      "sunrise":1478759450,
      "sunset":1478793279*/