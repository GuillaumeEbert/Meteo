package shindra.meteo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.City.CityBuilder;
import shindra.meteo.City.Internal.Weather;
import shindra.meteo.UrlBuilder.UrlBuilder;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class IconFetcher implements Runnable {

    private City myCity;
    private Handler myCityBuilderHandler;
    private UrlBuilder theUrlBuilder;

    public IconFetcher(Handler aCityBuilderHandler, City aCityToComplete, UrlBuilder aUrlBuilder) {
        myCityBuilderHandler = aCityBuilderHandler;
        myCity = aCityToComplete;
        theUrlBuilder = aUrlBuilder;

    }

    @Override
    public void run() {

        /* get through the weather lis*/
        for (Weather a : myCity.getMyWeathers()) {
            try {
                /*Build the icon URL*/
                URL urlToConnect = theUrlBuilder.buildIconUrl(a.getIconId());
                /*Search the Icon*/
                HttpURLConnection connection = (HttpURLConnection) urlToConnect.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                /*Add it to the current weather obj*/
                a.setIconImg(BitmapFactory.decodeStream(input));

            } catch (Exception e) {
                e.printStackTrace();
                myCityBuilderHandler.obtainMessage(CityBuilder.ICON_FETCHER_EXCEPTION, e).sendToTarget();
            }

        }

        myCityBuilderHandler.obtainMessage(CityBuilder.ICONS_FETCHED, myCity).sendToTarget();

    }
}


