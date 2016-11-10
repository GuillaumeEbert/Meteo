package shindra.meteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.MalformedURLException;

import shindra.meteo.City.City;
import shindra.meteo.Json.JsonDeserializer;
import shindra.meteo.UrlBuilder.UrlBuilder;

public class MainActivity extends AppCompatActivity implements  JsonDeserializer.DeserializerCallBack  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonDeserializer test = new JsonDeserializer();
        UrlBuilder myUrlBuilder = new UrlBuilder();

        String urlImgTest = "http://openweathermap.org/img/w/10d.png";

        try {
            test.getData(this,myUrlBuilder.buildUrl("hji","france"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /*Call when aCity is available from a Json*/
    @Override
    public void cityAvailable(City aCity) {
        Log.d("City available", aCity.toString());
    }
}
