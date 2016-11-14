package shindra.meteo.UI.GUI;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;

import java.net.MalformedURLException;
import java.util.ArrayList;

import shindra.meteo.CitiesManager;
import shindra.meteo.City.City;
import shindra.meteo.Favoris.Fav;
import shindra.meteo.Loader;
import shindra.meteo.R;

public class GUI extends AppCompatActivity implements CitiesManager.CitiesManagerCallback {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mySectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager myViewPager;
    private CitiesManager myCitiesManager;
    private Loader myLoader;
    private Fav myCityInFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCityInFav = new Fav(getSharedPreferences(Fav.PREFS_NAME,MODE_PRIVATE));
       // myCityInFav.deleteAllCityFav();
        myLoader = new Loader(myCityInFav);
        myCitiesManager = new CitiesManager(this,myLoader);
        myCitiesManager.setCallBackListener(this);
        myLoader.setTheCitiesManager(myCitiesManager);

        /*Create the adapter that will return a fragment for each city*/
        mySectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), myCitiesManager);

        /*Set up the ViewPager with the sections adapter.*/
        myViewPager = (ViewPager) findViewById(R.id.container);
        myViewPager.setAdapter(mySectionsPagerAdapter);

        /*Start loading the city in fav*/
        myLoader.loadCity();

        /*For test only*/
        Button btnAddCity = (Button) findViewById(R.id.btn_add_city);
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myCitiesManager.buildCity("Strasbourg", "France");
                    myCitiesManager.buildCity("Paris","France");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        myCityInFav.addAllCitiesById(myCitiesManager.getCities());

        /*Search the focus view*/
        int viewPosition = myViewPager.getCurrentItem();
        /*Saved it to the sharedPreference*/
        myCityInFav.addLastFragmentPositionOnDisplay(viewPosition);

    }

    @Override
    public void newCityAvailable(City newCityBuild) {
        /*A new city is available*/

        /*Update the myViewPager*/
        myViewPager.getAdapter().notifyDataSetChanged();
        myViewPager.setCurrentItem(myCitiesManager.getCities().size()-1);

    }

    @Override
    public void loadingCompleted() {
        Log.d("GUI", "Callback loading completed");
        /*Find the last city on display*/

        /*update the GUI with all the city*/
        myViewPager.getAdapter().notifyDataSetChanged();
        /*Put the last city on display in focus*/
        int lastFragmentPos = myCityInFav.getLastFragmentPositionOnDisplayId();
        myViewPager.setCurrentItem(lastFragmentPos);

    }




}
