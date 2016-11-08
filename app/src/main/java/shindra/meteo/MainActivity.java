package shindra.meteo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.MalformedURLException;

import shindra.meteo.Json.JsonDeserializer;
import shindra.meteo.UrlBuilder.UrlBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonDeserializer test = new JsonDeserializer();
        UrlBuilder myUrlBuilder = new UrlBuilder();

        try {
            test.getData(myUrlBuilder.buildUrl("Lyon","france"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
