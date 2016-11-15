package shindra.meteo.UI.GUI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import shindra.meteo.CitiesManager;
import shindra.meteo.City.City;
import shindra.meteo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Guillaume on 14/11/2016.
 */

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_CITIES_MANAGER = "test";


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, CitiesManager aManager) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putParcelable(ARG_CITIES_MANAGER, aManager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d("SectionNumber", Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

        CitiesManager myCitiesManager = (CitiesManager) getArguments().getParcelable(ARG_CITIES_MANAGER);


        int position = getArguments().getInt(ARG_SECTION_NUMBER) - 1;

        City aCity = myCitiesManager.getCity(position);


        TextView tvCityName = (TextView) rootView.findViewById(R.id.tv_city_name);
        ImageView ivWeatherIcon = (ImageView) rootView.findViewById(R.id.iv_weather_icon);
        TextView tvCityTemp = (TextView) rootView.findViewById(R.id.tv_temp);
        TextView tvDate = (TextView) rootView.findViewById(R.id.tv_date);

        tvCityName.setText(aCity.geName());
        ivWeatherIcon.setImageBitmap(aCity.getWeathers().getIconImg());
        tvCityTemp.setText(Integer.toString(aCity.getTemperature()) +"°c");
        tvDate.setText(buildDate(Calendar.getInstance(TimeZone.getDefault() )));


        return rootView;
    }

    private String buildDate(Calendar aCalendar){
        TimeZone a = aCalendar.getTimeZone();
        int month = aCalendar.get(Calendar.MONTH);
        int dayNb = aCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = aCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = aCalendar.get(Calendar.MINUTE);
        return "Date todo";
    }

    private String getDateName(int aDate){
        switch (aDate){
            case Calendar.MONDAY:
                return getResources().getResourceName(R.string.mon);
            case Calendar.TUESDAY:
                return getResources().getResourceName(R.string.thu);
            case Calendar.WEDNESDAY:
                return getResources().getResourceName(R.string.wen);
            case Calendar.THURSDAY:
                return getResources().getResourceName(R.string.thur);
            case Calendar.FRIDAY:
                return getResources().getResourceName(R.string.fri);
            case Calendar.SATURDAY:
                return getResources().getResourceName(R.string.sat);
            case Calendar.SUNDAY:
                return getResources().getResourceName(R.string.sun);
        }
        return "oulala";
    }
}
