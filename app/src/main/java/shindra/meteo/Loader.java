package shindra.meteo;

import java.util.ArrayList;
import java.util.Iterator;

import shindra.meteo.Favoris.FavoritesManager;
import shindra.meteo.GPS.GPS;

/**
 * Created by Guillaume on 14/11/2016.
 */

public class Loader{


    private FavoritesManager theCitiesInFavoritesManager;
    private boolean isExecuting;
    private boolean hasNext;
    private ArrayList<String> lCitiesIdInFav;
    private Iterator iterator;

    public Loader(FavoritesManager citiesInFavoritesManager){
        theCitiesInFavoritesManager = citiesInFavoritesManager;
        isExecuting = false;
        hasNext = true;
        lCitiesIdInFav = theCitiesInFavoritesManager.getCityInFavByID();
        iterator = lCitiesIdInFav.iterator();


        /*Fav exist ? */
        if(iterator.hasNext()) isExecuting = true;

    }

    public void loadCities(CitiesManager aCitiesManager){
          String aCityId;
    try{
        /*Has next ?*/
        if(iterator.hasNext() && isExecuting ){
            hasNext = true;
            aCityId = (String) iterator.next();
            aCitiesManager.buildCity(aCityId);

            /*Check if there is another city in the fav */
             if(!iterator.hasNext()) hasNext = false;

        }else{
            hasNext = false;
        }

    }catch (Exception e){
        //TOdo
    }

    }

    public boolean isExecuting(){return isExecuting;}
    public void setIsExecuting(boolean newState){isExecuting = newState;}
    public boolean hasNext() {return hasNext;}
}
