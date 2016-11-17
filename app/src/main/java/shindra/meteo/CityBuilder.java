package shindra.meteo;

import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import shindra.meteo.City.City;
import shindra.meteo.City.IconFetcher;
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
    private Queue<URL> qUrl;
    private boolean isFetcherWorking;
    private IconFetcher myIconFetcher;

    public static final int JSON_READY = 0;
    public static final int DESERIALIZER_FINISH = 1;
    public static final int JSON_FETCHER_EXCEPTION = 10;
    public static final int DESERIALIZER_EXCEPTION = 11;


    public CityBuilder(CityBuilderCallback aCallBack, Resources appResources) {
        myHandler = new Handler(this);
        myUrlBuilder = new UrlBuilder();
        myCallBack = aCallBack;
        qUrl = new LinkedBlockingQueue<URL>();
        isFetcherWorking = false;
        myIconFetcher = new IconFetcher(appResources);
    }

    public void buildCity(String cityName, String cityCountry) throws MalformedURLException {

        //Build Json Url
        URL theUrl2Connect = myUrlBuilder.buildUrl(cityName, cityCountry);
        if(isFetcherWorking) qUrl.add(theUrl2Connect);
        else initJsonFetcherThread(theUrl2Connect);

    }

    public void buildCity(String id) throws MalformedURLException {
        //Build Json Url
        URL theUrl2Connect = myUrlBuilder.buildUrl(id);
        if(isFetcherWorking) qUrl.add(theUrl2Connect);
        else initJsonFetcherThread(theUrl2Connect);
    }

    public void buildCity(Location aLocation) throws MalformedURLException {

        int lat = (int) aLocation.getAltitude();
        int longi = (int) aLocation.getLongitude();

        //Build Json Url
        URL theUrl2Connect = myUrlBuilder.buildUrl(lat,longi);
        if(isFetcherWorking) qUrl.add(theUrl2Connect);
        else initJsonFetcherThread(theUrl2Connect);

    }

    private void initJsonFetcherThread(URL urlToConnect){
        isFetcherWorking = true;
        JsonFetcher myJsonFetcher = new JsonFetcher(myHandler, urlToConnect);
        new Thread(myJsonFetcher).start();

    }

    @Override
    public boolean handleMessage(Message inputMessage) {

        switch (inputMessage.what) {

            case JSON_READY:
                /* Json file is here*/
                JSONObject jSonFile = (JSONObject) inputMessage.obj;

                Log.d("JsonReceived", jSonFile.toString());

                isFetcherWorking = false;
                /*Launch deserializer*/
                JsonDeserializer myDeserializer = new JsonDeserializer(myHandler, jSonFile);
                new Thread(myDeserializer).start();

                break;

            case DESERIALIZER_FINISH:
                /*City is Here*/
                City anIncompleteCity = (City) inputMessage.obj;

                anIncompleteCity.getWeathers().setIconImg( myIconFetcher.getWeatherIcon(anIncompleteCity.getWeathers().getIconId()));

                if(!qUrl.isEmpty() && !isFetcherWorking) initJsonFetcherThread(qUrl.poll());

                myCallBack.cityConstructed(anIncompleteCity);

                break;

            case JSON_FETCHER_EXCEPTION:
                break;

            case DESERIALIZER_EXCEPTION:
                break;


        }

        return false;
    }

    public interface CityBuilderCallback {
        void cityConstructed(City aCity);
    }


}
