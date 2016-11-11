package shindra.meteo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.MalformedURLException;

import shindra.meteo.City.City;
import shindra.meteo.Json.JsonDeserializer;
import shindra.meteo.UrlBuilder.UrlBuilder;

public class MainActivity extends AppCompatActivity implements CitiesManager.CitiesManagerCallback {

    private CitiesManager myCitiesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCitiesManager = new CitiesManager(this);

        try {
            myCitiesManager.buildCity("Strasbourg", "France");
            myCitiesManager.buildCity("Paris", "France");
            myCitiesManager.buildCity("Lyon", "France");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void newCityAvailable() {
        Log.d("GUI","City available");
    }
}
