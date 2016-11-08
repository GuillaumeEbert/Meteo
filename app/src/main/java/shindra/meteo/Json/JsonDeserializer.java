package shindra.meteo.Json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.City.Main;
import shindra.meteo.City.Weather.Weather;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class JsonDeserializer implements JsonFetcher.FetcherCallBack {

     private Thread aJsonThread;
    private Gson gson;

    public JsonDeserializer(){
        gson = new Gson();
    }

    public void getData(URL aUrl){
            aJsonThread = new Thread(new JsonFetcher(this,aUrl));
            aJsonThread.start();
    }

    @Override
    public void JsonObjectAvailable(JSONObject JsonFetched) {
        Log.d("JsonFile in call back", JsonFetched.toString());

        //City aCity = gson.fromJson(JsonFetched.toString(),City.class) ;
        City aCity = deserializeGeneric(City.class, JsonFetched);
        Main aMain = deserializeGeneric(Main.class, JsonFetched);


        Log.d("CityName", aCity.geName());

    }

    private <T> T deserializeGeneric(Class<T> clazz,JSONObject aJsonObject){

        return (T) gson.fromJson(aJsonObject.toString(),clazz) ;

    }

    private <T>  T deserializeArrayGeneric (TypeToken aToken){

        return null;
    }
}
