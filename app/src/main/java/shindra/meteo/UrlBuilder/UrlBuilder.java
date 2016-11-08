package shindra.meteo.UrlBuilder;

import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import shindra.meteo.Json.JsonDeserializer;
import shindra.meteo.Json.JsonFetcher;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class UrlBuilder {

   private static final String API_KEY = "22b96bd76e2df6d151db1dba669fc1e6";
    private static final String URL_START ="http://api.openweathermap.org/data/2.5/weather?q=";

    public URL buildUrl(String cityName, String cityCountry) throws MalformedURLException {


        String Url = URL_START + firstChar2UpperCase(cityName) + "," + firstChar2UpperCase(cityCountry) +"&APPID=" + API_KEY;

        return new URL(Url);

    }

    private String firstChar2UpperCase(String s){
        String s1 = s.substring(0, 1).toUpperCase();
        String nameCapitalized = s1 + s.substring(1);

        return nameCapitalized;
    }

}
