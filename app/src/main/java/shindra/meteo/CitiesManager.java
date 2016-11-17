package shindra.meteo;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.UI.GUI.GUI;

/**
 * Created by Guillaume on 11/11/2016.
 */

public class CitiesManager implements CityBuilder.CityBuilderCallback, Parcelable {

    private ArrayList<City> lCity;
    private CityBuilder myCityBuilder;
    private CitiesManagerCallback myCallback;
    private GUI theGui;
    private Loader theLoader;


    public CitiesManager(GUI aGui, Loader aLoader) {
        lCity = new ArrayList<City>();
        myCityBuilder = new CityBuilder(this, aGui.getResources());
        theGui = aGui;
        theLoader = aLoader;
    }

    protected CitiesManager(Parcel in) {
        lCity = in.createTypedArrayList(City.CREATOR);
    }

    public static final Creator<CitiesManager> CREATOR = new Creator<CitiesManager>() {
        @Override
        public CitiesManager createFromParcel(Parcel in) {
            return new CitiesManager(in);
        }

        @Override
        public CitiesManager[] newArray(int size) {
            return new CitiesManager[size];
        }
    };

    public void buildCity(String cityName, String cityCountry) throws MalformedURLException {
        myCityBuilder.buildCity(cityName, cityCountry);
    }

    public void buildCity(String cityId) throws MalformedURLException {
       myCityBuilder.buildCity(cityId);

        }

    public void buildCity(Location aLocation){

    }

    public City getCity(String cityName, String cityCountry) {

        return null;
    }

    public City getCity(int position) {
        return lCity.get(position);
    }

    public ArrayList<City> getCities() {
        return lCity;
    }


    @Override
    public void cityConstructed(City aCity) {

        /*If in loading the app */
        if (theLoader.isExecuting()) {
            /*add the city to the list*/
            lCity.add(aCity);
            if (theLoader.hasNext()) theLoader.loadCity();   /*If loader has next, build new city*/
            else {
                theLoader.setIsExecuting(false);
                myCallback.loadingCompleted();            /*Loading completed*/
            }

        } else {
         /*New entry from a search*/
            /*Check if city already exist*/
            if (!checkIfCityExist(aCity.getId())) {
                  /*If not add the city to the list*/
                lCity.add(aCity);
                /*Call the callback of a new city available*/
                myCallback.newCityAvailable(aCity);
            } else {
                //If exist put the city fragment on focus on the GUI*/
            }
        }
    }

    private boolean checkIfCityExist(int cityId) {
        for (City a : lCity) {
            if (a.getId() == cityId) return true;
        }
        return false;
    }

    public void setCallBackListener(CitiesManagerCallback aListener) {
        myCallback = aListener;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(lCity);
    }

    public interface CitiesManagerCallback {
        void newCityAvailable(City newCityBuild);
        void loadingCompleted();
    }
}
