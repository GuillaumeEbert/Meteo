package shindra.meteo.UI.GUI.UiDisplayCitiesFav;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.R;

public class UiDisplayCitiesFav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_display_cities_fav);

        Intent intent = getIntent();

        ArrayList<City> lCities = intent.getParcelableArrayListExtra("CitiesManager");

        ListView lvCitiesInFav = (ListView) findViewById(R.id.lv_cities_fav);
        CustomAdapter adapter = new CustomAdapter(this, lCities);
        lvCitiesInFav.setAdapter(adapter);

        Log.d("YOLO","YOLO");

    }
}


