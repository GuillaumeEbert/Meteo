package shindra.meteo.Json;

import android.os.Handler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import shindra.meteo.CityBuilder;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class JsonFetcher implements Runnable {
     private Handler myCityBuilderHandler;
     private URL myUrl2Connect;

    public JsonFetcher(Handler aCityBuilderHandler, URL url2Connect){
        myCityBuilderHandler = aCityBuilderHandler;
        myUrl2Connect = url2Connect;

    }

    /**
     * Fetch the Json Object from the url passed in the constructor.
     * Raise callback once the Json Object is fetched
     */
    @Override
    public void run() {

        BufferedReader bufferedReader  = null;
        StringBuilder stringBuilder = null;
        String line = null;
        JSONObject aJsonObject;

        try{

            HttpURLConnection connection = (HttpURLConnection) myUrl2Connect.openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);

            connection.connect();

            bufferedReader  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            /*Build Json Object in string*/
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line + '\n');
            }

            bufferedReader.close();

            aJsonObject = new JSONObject((stringBuilder.toString()));

            /*Send Json file to cityBuilder*/
            myCityBuilderHandler.obtainMessage(CityBuilder.JSON_READY,aJsonObject).sendToTarget();


        }catch (Exception e){
            e.printStackTrace();
             /*Send Exception file to cityBuilder*/
            myCityBuilderHandler.obtainMessage(CityBuilder.JSON_READY,e).sendToTarget();
        }

    }

}
