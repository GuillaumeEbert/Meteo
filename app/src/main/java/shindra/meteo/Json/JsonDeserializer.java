package shindra.meteo.Json;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.CityBuilder;
import shindra.meteo.City.Internal.Coordinates;
import shindra.meteo.City.Internal.Main;
import shindra.meteo.City.Internal.Sys;
import shindra.meteo.City.Internal.Weather;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class JsonDeserializer implements Runnable {

    private Gson gson;
    private Handler myCityBuilderHandler;
    private JSONObject myJsonToDeserialize;

    public JsonDeserializer(Handler aCityBuilderHandler, JSONObject jsonToDeserialize) {
        gson = new Gson();
        myCityBuilderHandler = aCityBuilderHandler;
        myJsonToDeserialize = jsonToDeserialize;

    }

    @Override
    public void run() {

        try{
            //deserialize
            City aCity = deserializeGeneric(City.class, myJsonToDeserialize, null);
            aCity.setCoordinates(deserializeGeneric(Coordinates.class, myJsonToDeserialize, "coord"));
            aCity.setMain(deserializeGeneric(Main.class, myJsonToDeserialize, "main"));
            aCity.setSys(deserializeGeneric(Sys.class, myJsonToDeserialize, "sys"));

            ArrayList<Weather> weatherArrayList = deserializeArrayGeneric(new TypeToken<ArrayList<Weather>>(){}.getType(),myJsonToDeserialize, "weather");
            aCity.setMyWeathers(weatherArrayList.get(0));

            //Send city to cityBuilder
            myCityBuilderHandler.obtainMessage(CityBuilder.DESERIALIZER_FINISH, aCity).sendToTarget();

        } catch (JSONException e) {
            e.printStackTrace();
            //Send JSONException to cityBuilder
            myCityBuilderHandler.obtainMessage(CityBuilder.DESERIALIZER_EXCEPTION, e).sendToTarget();

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

    private <T> ArrayList<T> deserializeArrayGeneric( Type aToken,JSONObject aJsonObject, String objectTittle) throws JSONException {

         /*Check if the key is in the Json*/
        if (!aJsonObject.has(objectTittle)) return null;  /*no, return null*/

        JSONArray aJsonArray = aJsonObject.getJSONArray(objectTittle);

        return gson.fromJson(aJsonArray.toString(), aToken);
    }



}
