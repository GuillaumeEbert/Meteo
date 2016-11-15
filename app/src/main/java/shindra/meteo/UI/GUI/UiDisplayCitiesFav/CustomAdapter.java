package shindra.meteo.UI.GUI.UiDisplayCitiesFav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import shindra.meteo.City.City;
import shindra.meteo.R;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<City> mylCities;
    private LayoutInflater _layoutInflater;

    public CustomAdapter(Context context, ArrayList<City> arrayList) {

        mylCities = arrayList;
        //get the layout inflater
        _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mylCities.size();
    }

    @Override
    public City getItem(int position) {
        return mylCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = _layoutInflater.inflate(R.layout.list_view_city_fav_layout, null);

        }

        City aCity = mylCities.get(position);

        if (aCity != null) {

            TextView tvCityName = (TextView) view.findViewById(R.id.lv_item_tv_city_name);
            TextView tvCityTemp = (TextView) view.findViewById(R.id.lv_item_city_temp);
            ImageView ivIcon = (ImageView) view.findViewById(R.id.lv_item_weather_icon);

            tvCityName.setText(aCity.geName());
            ivIcon.setImageBitmap(aCity.getWeathers().getIconImg()); //TODO


        }

        return view;
    }

}
