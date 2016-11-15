package shindra.meteo.UI.GUI;

import android.app.Fragment;
import android.content.Intent;



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
import shindra.meteo.Favoris.Fav;
import shindra.meteo.Loader;
import shindra.meteo.R;
import shindra.meteo.UI.GUI.UiDisplayCitiesFav.UiDisplayCitiesFav;
import shindra.meteo.UI.GUI.UiSearchCity.UiSearchCity;

import android.app.FragmentTransaction;

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
    private Fav myCityInFav;
    private ImageButton btnLeftNav;
    private ImageButton btnRightNav;

    public static final int SEARCH_NEW_CITY_REQUEST = 1;  // The request code

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
        displayArrowNav();

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
        displayArrowNav();

    }

    @Override
    public void onClick(View v) {
        int btnId = v.getId();

        switch (btnId){
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

    private void sendMailCityOnDisplay(){
        /*Todo*/
    }

    private void displayUiCitiesInFav(View v){
        /*Open the activity to show the city in fav*/
        Intent it = new Intent(v.getContext(), UiDisplayCitiesFav.class);
        ArrayList<City> test = myCitiesManager.getCities();
        it.putParcelableArrayListExtra("CitiesManager", test);
        startActivity(it);

    }

    private void displayUiSearchCity(View v){
           /*For test only*/
      /*  try {
            myCitiesManager.buildCity("Lyon", "France");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        Intent intent = new Intent(v.getContext(), UiSearchCity.class);
        startActivityForResult(intent, GUI.SEARCH_NEW_CITY_REQUEST);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     Log.d("Test","YOLO");
        if(resultCode == RESULT_OK){


            String cityName = data.getStringExtra(UiSearchCity.CITY_NAME);
            String cityCountry = data.getExtras().getString(UiSearchCity.CITY_COUNTRY);

            if(cityCountry != null & cityCountry != null){
                try {
                    myCitiesManager.buildCity(cityName,cityCountry);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void handleLeftNav(){
        int viewPosition = myViewPager.getCurrentItem();

        /*Is not at index zeros*/
        if(viewPosition > 0 ) myViewPager.setCurrentItem(viewPosition -1);

        if((viewPosition - 1 == 0)) btnLeftNav.setVisibility(View.INVISIBLE);

        if(btnRightNav.getVisibility() == View.INVISIBLE) btnRightNav.setVisibility(View.VISIBLE);

    }
    private void handleRightNav(){
        /*Get nb of position from the view manager*/
        int nbOfView = myViewPager.getChildCount() -1 ;
        int currentPos = myViewPager.getCurrentItem();
        int nextPos = currentPos+1;
        /*There is a view next this position*/
        if(nextPos <= nbOfView) myViewPager.setCurrentItem(nextPos);

        if(nextPos == nbOfView) btnRightNav.setVisibility(View.INVISIBLE);

        if(btnLeftNav.getVisibility() == View.INVISIBLE) btnLeftNav.setVisibility(View.VISIBLE);



    }

    private void handleLocalisation(){

    }

    private void displayArrowNav(){
        int nbOfView = myViewPager.getChildCount() -1;
        int currentPos = myViewPager.getCurrentItem();

        if(currentPos == 0 ){
            btnLeftNav.setVisibility(View.INVISIBLE);
            btnRightNav.setVisibility(View.VISIBLE);
        }
        if(nbOfView == currentPos) {
            btnRightNav.setVisibility(View.INVISIBLE);
            btnLeftNav.setVisibility(View.VISIBLE);
        }
    }




    /*TODO Résolution de bug arrow
      TODO Géolocalisation
      TODO Supprimer une ville des favories
      TODO Ajouter l'indication d'humiditer
      TODO Tester les emails
      TODO Traduction en anglais
     */
}
