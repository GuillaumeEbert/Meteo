package shindra.meteo.UrlBuilder;

import android.location.Location;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class UrlBuilder {

    private static final String API_KEY = "22b96bd76e2df6d151db1dba669fc1e6";
    private static final String URL_START = "http://api.openweathermap.org/data/2.5/weather?";
    private static final String URL_ICON_START = "http://openweathermap.org/img/w/";
    private static final String URL_ICON_STOP = ".png";

    public URL buildUrl(String cityName, String cityCountry) throws MalformedURLException {
        String Url = URL_START + "q=" +firstChar2UpperCase(cityName) +
                "," + firstChar2UpperCase(cityCountry) + getEndUrl();
        return new URL(Url);

    }

    public URL buildUrl(String cityID) throws MalformedURLException{
        String Url = URL_START + "id=" +cityID + getEndUrl();
        return new URL(Url);
    }

    public URL buildUrl(int latitude, int longitude)throws MalformedURLException {

        String lat = Integer.toString(latitude);
        String longi = Integer.toString(longitude);

        String Url = URL_START + "lat=" +lat + "lon=" + longi;

        return new URL(Url);

    }

    private String getEndUrl(){
        return "&units=metric"+ "&APPID=" + API_KEY;
    }



    private String firstChar2UpperCase(String s) {
        String s1 = s.substring(0, 1).toUpperCase();
        String nameCapitalized = s1 + s.substring(1);

        return nameCapitalized;
    }

}
