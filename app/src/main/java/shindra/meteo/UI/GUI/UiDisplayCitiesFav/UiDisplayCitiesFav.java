package shindra.meteo.UI.GUI.UiDisplayCitiesFav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.R;

public class UiDisplayCitiesFav extends AppCompatActivity {

    public static final int REQUEST_CODE = 2;
    private  CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_display_cities_fav);


        Intent intent = getIntent();

        ArrayList<City> lCities = intent.getParcelableArrayListExtra("CitiesManager");

        ListView lvCitiesInFav = (ListView) findViewById(R.id.lv_cities_fav);

        adapter = new CustomAdapter(this, lCities,lvCitiesInFav);
        lvCitiesInFav.setAdapter(adapter);

    }
    @Override
    protected void onPause(){
        super.onPause();

        Intent resultIntent = new Intent();

        ArrayList<City> newlCities = adapter.getMylCities();

        /*Bundle a = new Bundle();
        a.putParcelableArrayList("Test" ,newlCities);

       // resultIntent.putExtra("Bundle", a);
        resultIntent.putExtra("TEST", "TEST");
        setResult(Activity.RESULT_OK, resultIntent);*/
        //finish();


    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        Intent mIntent = new Intent();

        ArrayList<City> newlCities = adapter.getMylCities();

        bundle.putParcelableArrayList("newCities", newlCities);

        mIntent.putExtra("newCities",bundle);
        setResult(RESULT_OK, mIntent);
        super.onBackPressed();
    }
}


