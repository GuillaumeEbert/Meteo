package shindra.meteo;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.UI.GUI.GUI;

/**
 * Created by Guillaume on 11/11/2016.
 */

public class CitiesManager implements CityBuilder.CityBuilderCallback, Serializable {

    private ArrayList<City> lCity;
    private CityBuilder myCityBuilder;
    private CitiesManagerCallback myCallback;
    private GUI theGui;
    private Loader theLoader;


    public CitiesManager(GUI aGui, Loader aLoader) {
        lCity = new ArrayList<City>();
        myCityBuilder = new CityBuilder(this);
        theGui = aGui;
        theLoader = aLoader;
    }

    public void buildCity(String cityName, String cityCountry) throws MalformedURLException {
        myCityBuilder.buildCity(cityName, cityCountry);
    }

    public void buildCity(String cityId) throws MalformedURLException {
       myCityBuilder.buildCity(cityId);

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

    public interface CitiesManagerCallback {
        void newCityAvailable(City newCityBuild);

        void loadingCompleted();
    }
}
