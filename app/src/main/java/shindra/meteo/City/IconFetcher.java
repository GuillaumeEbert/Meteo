package shindra.meteo.City;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import shindra.meteo.City.Internal.Weather;
import shindra.meteo.CityBuilder;
import shindra.meteo.R;
import shindra.meteo.UrlBuilder.UrlBuilder;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class IconFetcher {

    private static final int NIGHT_ICON = 0;
    private static final int DAY_ICON = 1;
    private Resources myRessouces;


    public IconFetcher(Resources appRessource) {
        myRessouces = appRessource;

    }


    public Bitmap getWeatherIcon(String weatherIconId) {
        switch (isDayOrNightTime(weatherIconId)) {
            case NIGHT_ICON:
                return getNightIcon(weatherIconId);

            case DAY_ICON:
                return getDayIcon(weatherIconId);
        }
        return null;
    }

    public int isDayOrNightTime(String weatherIconId) {

        if (weatherIconId.endsWith("n")) {
            return NIGHT_ICON;
        }
        if (weatherIconId.endsWith("d")) {
            return DAY_ICON;
        }

        return -1;

    }


    private Bitmap getDayIcon(String weatherIcon) {
        int id;
        String subString = weatherIcon.substring(0, 2);
        id = Integer.parseInt(subString);

        switch (id) {
            case 1:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_01_clear_sky_d);

            case 2:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_02_few_cloud_d, null);

            case 3:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_03_scattered_clouds_d_n, null);

            case 4:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_04_broken_clouds_d_n, null);

            case 9:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_09_shower_rain_d_n, null);

            case 10:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_10_rain_d, null);

            case 11:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_11_thunderstorm_d_n, null);

            case 13:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_13_snow_d_n, null);

            case 50:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_50_mist_d_n, null);


        }
        return null;
    }

    private Bitmap getNightIcon(String weatherIcon) {
        int id;
        String subString = weatherIcon.substring(0, 2);
        id = Integer.parseInt(subString);

        switch (id) {
            case 1:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_01_clear_sky_n);

            case 2:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_02_few_cloud_n, null);

            case 3:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_03_scattered_clouds_d_n, null);

            case 4:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_04_broken_clouds_d_n, null);

            case 9:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_09_shower_rain_d_n, null);

            case 10:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_10_rain_n, null);

            case 11:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_11_thunderstorm_d_n, null);

            case 13:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_13_snow_d_n, null);

            case 50:
                return BitmapFactory.decodeResource(myRessouces, R.mipmap.weather_icon_50_mist_d_n, null);


        }
        return null;
    }


}


