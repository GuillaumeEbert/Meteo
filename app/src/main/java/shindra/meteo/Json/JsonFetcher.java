package shindra.meteo.Json;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class JsonFetcher implements Runnable {
     private FetcherCallBack callBack;
     private URL url2Connect;

    public JsonFetcher(FetcherCallBack aCallBack, URL aUrl){
        callBack = aCallBack;
        url2Connect = aUrl;
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

            HttpURLConnection connection = (HttpURLConnection) url2Connect.openConnection();
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

            aJsonObject = new JSONObject(stringBuilder.toString());

            callBack.JsonObjectAvailable(aJsonObject); /* Raise callback*/


        }catch (Exception E){
            //TODO
        }

    }



    public interface FetcherCallBack{
        void JsonObjectAvailable(JSONObject JsonFetched);

    }
}
