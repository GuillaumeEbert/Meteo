package shindra.meteo.City;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import shindra.meteo.CitiesManager;
import shindra.meteo.IconFetcher;
import shindra.meteo.Json.JsonDeserializer;
import shindra.meteo.Json.JsonFetcher;
import shindra.meteo.UrlBuilder.UrlBuilder;

/**
 * Created by Guillaume on 11/11/2016.
 */

public class CityBuilder implements Handler.Callback {
    private Handler myHandler;
    private UrlBuilder myUrlBuilder;
    private CityBuilderCallback myCallBack;

    public static final int JSON_READY = 0;
    public static final int DESERIALIZER_FINISH = 1;
    public static final int ICONS_FETCHED = 2;
    public static final int JSON_FETCHER_EXCEPTION = 10;
    public static final int DESERIALIZER_EXCEPTION = 11;
    public static final int ICON_FETCHER_EXCEPTION = 12;

    public CityBuilder(CityBuilderCallback aCallBack) {
        myHandler = new Handler(this);
        myUrlBuilder = new UrlBuilder();
        myCallBack = aCallBack;
    }

    public void buildCity(String cityName, String cityCountry) throws MalformedURLException {
        //Build Json Url
        URL theUrl2Connect = myUrlBuilder.buildUrl(cityName, cityCountry);

        //Launch JsonFetcher
        JsonFetcher myJsonFetcher = new JsonFetcher(myHandler, theUrl2Connect);
        new Thread(myJsonFetcher).start();


    }

    @Override
    public boolean handleMessage(Message inputMessage) {

        switch (inputMessage.what) {

            case JSON_READY:
                /* Json file is here*/
                JSONObject jSonFile = (JSONObject) inputMessage.obj;

                /*Launch deserializer*/
                JsonDeserializer myDeserializer = new JsonDeserializer(myHandler, jSonFile);
                new Thread(myDeserializer).start();

                break;

            case DESERIALIZER_FINISH:
                /*City is Here*/
                City anIncompleteCity = (City) inputMessage.obj;

                /*Start the icon Fetcher*/
                IconFetcher myIconFetcher = new IconFetcher(myHandler, anIncompleteCity, myUrlBuilder);
                new Thread(myIconFetcher).start();
                break;

            case ICONS_FETCHED:
                City aCompleteCity = (City) inputMessage.obj;
                myCallBack.cityConstructed(aCompleteCity);
                break;

            case JSON_FETCHER_EXCEPTION:
                break;

            case DESERIALIZER_EXCEPTION:
                break;

            case ICON_FETCHER_EXCEPTION:
                break;

        }

        return false;
    }

    public interface CityBuilderCallback {
        void cityConstructed(City aCity);
    }


}
