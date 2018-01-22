package shindra.meteo.UI.GUI;


import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.net.MalformedURLException;
import java.util.ArrayList;

import shindra.meteo.CitiesManager;
import shindra.meteo.City.City;
import shindra.meteo.Favoris.FavoritesManager;
import shindra.meteo.GPS.GPS;
import shindra.meteo.Loader;
import shindra.meteo.R;
import shindra.meteo.UI.GUI.UiDisplayCitiesFav.UiDisplayCitiesFav;
import shindra.meteo.UI.GUI.UiSearchCity.UiSearchCity;


public class GUI extends AppCompatActivity implements CitiesManager.CitiesManagerCallback, View.OnClickListener {

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
    private FavoritesManager myCityInFavoritesManager;
    private ImageButton btnLeftNav;
    private ImageButton btnRightNav;
    private GPS myGps;

    public static final int SEARCH_NEW_CITY_REQUEST = 1;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGps = new GPS(this);

        myCityInFavoritesManager = new FavoritesManager(getSharedPreferences(FavoritesManager.PREFS_NAME, MODE_PRIVATE));
        //myCityInFavoritesManager.deleteAllCityFav();
        myLoader = new Loader(myCityInFavoritesManager);
        myCitiesManager = new CitiesManager(this, this, myLoader);


        /*Create the adapter that will return a fragment for each city*/
        mySectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), myCitiesManager);

        /*Set up the ViewPager with the sections adapter.*/
        myViewPager = (ViewPager) findViewById(R.id.container);
        myViewPager.setAdapter(mySectionsPagerAdapter);

        /*Start loading the cities in fav*/
        myLoader.loadCities(myCitiesManager);

        Button btnAddCity = (Button) findViewById(R.id.btn_add_city);
        btnAddCity.setOnClickListener(this);

        Button btnViewCities = (Button) findViewById(R.id.btn_view_cities);
        btnViewCities.setOnClickListener(this);

        ImageButton btnSendToMail = (ImageButton) findViewById(R.id.btn_mail);
        btnSendToMail.setOnClickListener(this);

        ImageButton btnLocalisation = (ImageButton) findViewById(R.id.btn_gps);
        btnLocalisation.setOnClickListener(this);

        btnLeftNav = (ImageButton) findViewById(R.id.left_nav);
        btnLeftNav.setOnClickListener(this);

        btnRightNav = (ImageButton) (ImageButton) findViewById(R.id.right_nav);
        btnRightNav.setOnClickListener(this);

        myGps.getLastLocation();

    }

    @Override
    protected void onStop() {
        super.onStop();
        myCityInFavoritesManager.addAllCitiesById(myCitiesManager.getCities());

        /*Search the view in focus*/
        int viewPosition = myViewPager.getCurrentItem();
        /*Saved it to the sharedPreference*/
        myCityInFavoritesManager.addLastFragmentPositionOnDisplay(viewPosition);

    }

    /**
     * Call back from the citiesManager containing a new city
     * @param newCityBuild
     */
    @Override
    public void newCityAvailable(City newCityBuild) {
        /*A new city is available*/

        /*Notify the view pager to add a new fragment*/
        myViewPager.getAdapter().notifyDataSetChanged();
        /*Put the new fragment in focus*/
        myViewPager.setCurrentItem(myCitiesManager.getCities().size() - 1);

        /*handle the navigation arrows*/
        displayArrowNav();

    }

    /**
     *
     */
    @Override
    public void loadingCompleted() {
        Log.d("GUI", "Callback loading completed");

        /*update the GUI with all the city*/
        myViewPager.getAdapter().notifyDataSetChanged();

        /*Put the last city on display in focus*/
        int lastFragmentPos = myCityInFavoritesManager.getLastFragmentPositionOnDisplayId();
        myViewPager.setCurrentItem(lastFragmentPos);

        /*handle the navigation arrows*/
        displayArrowNav();

    }

    private void displayUiSearchCity(View v) {
        Intent intent = new Intent(v.getContext(), UiSearchCity.class);
        startActivityForResult(intent, UiSearchCity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* Result from activity*/

        if (requestCode == UiSearchCity.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String cityName = data.getStringExtra(UiSearchCity.CITY_NAME);
                String cityCountry = data.getExtras().getString(UiSearchCity.CITY_COUNTRY);

                if (cityCountry != null & cityCountry != null) {
                    try {
                        myCitiesManager.buildCity(cityName, cityCountry);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(requestCode == UiDisplayCitiesFav.REQUEST_CODE){

            Bundle a = data.getBundleExtra("newCities");

            /*Get new city list*/
            ArrayList<City> lCities = a.getParcelableArrayList("newCities") ;

            /*Delete all the fav*/
            myCityInFavoritesManager.deleteAllCityFav();
            myCityInFavoritesManager.addAllCitiesById(lCities); /*Refav the new city list*/

            myCitiesManager.setCities(lCities); /*Redo the city manager with the new cities*/

            myViewPager.getAdapter().notifyDataSetChanged(); /*Update UI*/
            displayArrowNav();

            Log.d("test","test");
        }
    }

    /**
     * Handle the click for all the buttons on the GUI
     * @param v
     */
    @Override
    public void onClick(View v) {
        int btnId = v.getId();

        switch (btnId) {
            case R.id.btn_mail:
                sendMailCityOnDisplay();
                break;
            case R.id.btn_view_cities:
                displayUiCitiesInFav(v);
                break;

            case R.id.btn_add_city:
                displayUiSearchCity(v);
                break;

            case R.id.left_nav:
                handleLeftNav();
                break;

            case R.id.right_nav:
                handleRightNav();
                break;

            case R.id.btn_gps:
                handleLocalisation();
                break;
        }
    }

    private void sendMailCityOnDisplay() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    /**
     * Open the activity that will handle the favorites
     * @param v
     */
    private void displayUiCitiesInFav(View v) {
        Intent it = new Intent(v.getContext(), UiDisplayCitiesFav.class);
        ArrayList<City> test = myCitiesManager.getCities();
        it.putParcelableArrayListExtra("CitiesManager", test);
        startActivityForResult(it, UiDisplayCitiesFav.REQUEST_CODE);

    }



    private void handleLeftNav() {
        int viewPosition = myViewPager.getCurrentItem();

        /*Is not at index zeros*/
        if (viewPosition > 0) myViewPager.setCurrentItem(viewPosition - 1);

        if ((viewPosition - 1 == 0)) btnLeftNav.setVisibility(View.INVISIBLE);

        if (btnRightNav.getVisibility() == View.INVISIBLE) btnRightNav.setVisibility(View.VISIBLE);

    }

    private void handleRightNav() {
        /*Get nb of position from the view manager*/
        int nbOfView = mySectionsPagerAdapter.getCount() - 1;
        int currentPos = myViewPager.getCurrentItem();
        int nextPos = currentPos + 1;
        /*There is a view next this position*/
        if (nextPos <= nbOfView) myViewPager.setCurrentItem(nextPos);

        if (nextPos == nbOfView) btnRightNav.setVisibility(View.INVISIBLE);

        if (btnLeftNav.getVisibility() == View.INVISIBLE) btnLeftNav.setVisibility(View.VISIBLE);

    }

    private void handleLocalisation() {
        //Get the last update
        Location lastLocation = myGps.getLastLocation();

        //get the city with location
        if (lastLocation != null) try {
            myCitiesManager.buildCity(lastLocation);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void displayArrowNav() {
        int nbOfView = mySectionsPagerAdapter.getCount() - 1;
        int currentPos = myViewPager.getCurrentItem();

        if (nbOfView == 0) {
            btnLeftNav.setVisibility(View.INVISIBLE);
            btnRightNav.setVisibility(View.INVISIBLE);
            return;
        } else if (currentPos == 0) {
            btnLeftNav.setVisibility(View.INVISIBLE);
            btnRightNav.setVisibility(View.VISIBLE);
            return;
        } else if (nbOfView == currentPos) {
            btnRightNav.setVisibility(View.INVISIBLE);
            btnLeftNav.setVisibility(View.VISIBLE);
        }
    }



}
