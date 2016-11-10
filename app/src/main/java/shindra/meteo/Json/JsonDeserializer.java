package shindra.meteo.Json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import shindra.meteo.City.City;
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

public class JsonDeserializer implements JsonFetcher.FetcherCallBack {

    private Thread aJsonThread;
    private Gson gson;
    private DeserializerCallBack myCallBack;

    public JsonDeserializer() {
        gson = new Gson();
    }

    public void getData(DeserializerCallBack aCallback, URL aUrl) {
        aJsonThread = new Thread(new JsonFetcher(this, aUrl));
        aJsonThread.start();
        myCallBack = aCallback;
    }

    @Override
    public void JsonObjectAvailable(JSONObject JsonFetched) {
        Log.d("JsonFile in call back", JsonFetched.toString());

        try {
            City aCity = deserializeGeneric(City.class, JsonFetched, null);
            aCity.setCoordinates(deserializeGeneric(Coordinates.class, JsonFetched, "coord"));
            aCity.setMain(deserializeGeneric(Main.class, JsonFetched, "main"));
            aCity.setWind(deserializeGeneric(Wind.class, JsonFetched, "wind"));
            aCity.setClouds(deserializeGeneric(Clouds.class, JsonFetched, "clouds"));
            aCity.setRainVolume(deserializeGeneric(RainVolume.class, JsonFetched, "rain"));
            aCity.setSnowVolume(deserializeGeneric(SnowVolume.class, JsonFetched, "snow"));
            aCity.setSys(deserializeGeneric(Sys.class, JsonFetched, "sys"));

            myCallBack.cityAvailable(aCity);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private <T> T deserializeGeneric(Class<T> clazz, JSONObject aJsonObject, String objectTittle) throws JSONException {
        if (objectTittle == null) return gson.fromJson(aJsonObject.toString(), clazz);

        /*Check if the key is in the Json*/
        if (!aJsonObject.has(objectTittle)) return null;  /*no, return null*/

        /*Parse the key to obtain the JsonObject of the key*/
        JSONObject current = aJsonObject.getJSONObject(objectTittle);
        Log.d("deserializeGeneric", current.toString());
        return gson.fromJson(current.toString(), clazz); /*Return the deserialize object*/

    }

    private <T> T deserializeArrayGeneric(TypeToken aToken, JSONObject aJsonObject, String arrayObjectTittle) {

        return null;
    }

    public interface DeserializerCallBack {
        void cityAvailable(City aCity);
    }
}
