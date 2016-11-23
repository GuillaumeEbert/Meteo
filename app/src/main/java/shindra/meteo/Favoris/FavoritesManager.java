package shindra.meteo.Favoris;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import shindra.meteo.City.City;

/**
 * Created by Guillaume on 13/11/2016.
 */

public class FavoritesManager {

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String ID_LAST_FRAGMENT_ON_DISPLAY = "last_fragment_on_display";
    public static final String CITY_ID ="city ID";

    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor mySharedPrefEditor;

    public FavoritesManager(SharedPreferences aSharedPreference){
        mySharedPreferences = aSharedPreference;
        mySharedPrefEditor = aSharedPreference.edit();
    }

    public void addAllCitiesById(ArrayList<City> cities2Save){

        Set<String> i = new HashSet<String>();

        for(City a : cities2Save){
            i.add(Integer.toString(a.getId()));
        }

        mySharedPrefEditor.putStringSet(CITY_ID,i).commit();

    }

    public void addLastFragmentPositionOnDisplay(int fragmentId){

        mySharedPrefEditor.putInt(ID_LAST_FRAGMENT_ON_DISPLAY,fragmentId).commit();
    }

    public ArrayList<String> getCityInFavByID(){

        ArrayList<String> lCitiesIdInFav = new ArrayList<String>();

        Set<String> aSetOfString = mySharedPreferences.getStringSet(CITY_ID,null);

        if(aSetOfString == null) return new ArrayList<String>();
        lCitiesIdInFav.addAll(aSetOfString);

        return lCitiesIdInFav;

    }

    public int getLastFragmentPositionOnDisplayId(){return mySharedPreferences.getInt(ID_LAST_FRAGMENT_ON_DISPLAY,0);}

    public void deleteACityFav(String cityName){
        mySharedPrefEditor.remove(cityName).commit();
    }

    public void deleteAllCityFav(){
        mySharedPrefEditor.clear().commit();
    }



}
