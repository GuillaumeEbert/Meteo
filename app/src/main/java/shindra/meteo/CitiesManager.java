package shindra.meteo;

import android.os.Handler;
import android.os.Message;

import java.net.MalformedURLException;
import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.City.CityBuilder;

/**
 * Created by Guillaume on 11/11/2016.
 */

public class CitiesManager implements CityBuilder.CityBuilderCallback{

    private ArrayList<City> lCity;
    private CityBuilder myCityBuilder;
    private  CitiesManagerCallback myCallback;

    public CitiesManager(CitiesManagerCallback aCallBack){
        lCity = new ArrayList<City>();
        myCityBuilder = new CityBuilder(this);
        myCallback = aCallBack;
    }

    public void buildCity(String cityName, String cityCountry) throws MalformedURLException {
        myCityBuilder.buildCity(cityName,cityCountry);

    }

    public void buildCity(String cityLocalisation){

    }

    public  City getCity(String cityName, String cityCountry){

       return null;
    }


    @Override
    public void cityConstructed(City aCity) {
        lCity.add(aCity);
        myCallback.newCityAvailable();
    }

    public interface CitiesManagerCallback{
        void newCityAvailable();
    }
}
