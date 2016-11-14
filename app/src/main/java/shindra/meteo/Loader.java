package shindra.meteo;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import shindra.meteo.Favoris.Fav;

/**
 * Created by Guillaume on 14/11/2016.
 */

public class Loader{

    private CitiesManager theCitiesManager;
    private Fav theCitiesInFav;
    private boolean isExecuting;
    private boolean hasNext;
    private ArrayList<String> lCitiesIdInFav;
    private Iterator iterator;

    public Loader(Fav citiesInFav){
        theCitiesInFav = citiesInFav;
        isExecuting = false;
        hasNext = true;
        lCitiesIdInFav = theCitiesInFav.getCityInFavByID();
        iterator = lCitiesIdInFav.iterator();

        /*Fav exist ? */
        if(iterator.hasNext()) isExecuting = true;
    }

    public void loadCity(){
          String aCityId;
    try{
        /*Has next ?*/
        if(iterator.hasNext() && isExecuting ){
            hasNext = true;
            aCityId = (String) iterator.next();
            theCitiesManager.buildCity(aCityId);
            /*Check if there is another city in the fav */

             if(!iterator.hasNext()) hasNext = false;
        }else{
            hasNext = false;

        }

    }catch (Exception e){
        //TOdo
    }


    }

    public void setTheCitiesManager(CitiesManager theCitiesManager) {
        this.theCitiesManager = theCitiesManager;
    }

    public boolean isExecuting(){return isExecuting;}
    public void setIsExecuting(boolean newState){isExecuting = newState;}
    public boolean hasNext() {return hasNext;}
}
