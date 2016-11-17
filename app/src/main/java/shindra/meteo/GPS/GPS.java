package shindra.meteo.GPS;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import shindra.meteo.UI.GUI.GUI;

/**
 * Created by Guillaume on 17/11/2016.
 */

public class GPS {

    private GUI gui;
    private LocationManager myLocationManager;

    public GPS(GUI aGui) {
        gui = aGui;
        myLocationManager = (LocationManager) gui.getSystemService(Context.LOCATION_SERVICE);

    }

    public Location getLastLocation() {
        Location aLocation;

        aLocation = myLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(aLocation == null) return null;

        Log.d("GPS_Lat=",Double.toString(aLocation.getLatitude()));
        Log.d("GPS_Long=",Double.toString(aLocation.getLongitude()));

        return aLocation;
    }
}
