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

/**
 * Created by Guillaume on 14/11/2016.
 */

public class PlaceholderFragment  extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_CITIES_MANAGER = "test";
    private CitiesManager myCitiesManager;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, CitiesManager aManager) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_CITIES_MANAGER, aManager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d("SectionNumber",Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

        myCitiesManager = (CitiesManager) getArguments().getSerializable(ARG_CITIES_MANAGER);
        int position = getArguments().getInt(ARG_SECTION_NUMBER) - 1;

        City aCity = myCitiesManager.getCity(position);

        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        TextView tvCityName = (TextView) rootView.findViewById(R.id.tv_city_name);
        ImageView ivWeatherIcaon = (ImageView) rootView.findViewById(R.id.iv_weather_icon);

        tvCityName.setText(aCity.geName());
        ivWeatherIcaon.setImageBitmap(aCity.getMyWeathers().get(0).getIconImg());
        return rootView;
    }
}
