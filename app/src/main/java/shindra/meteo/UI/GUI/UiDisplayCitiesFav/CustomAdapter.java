package shindra.meteo.UI.GUI.UiDisplayCitiesFav;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import shindra.meteo.City.City;
import shindra.meteo.R;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<City> mylCities;
    private LayoutInflater _layoutInflater;
    private City aCity;
    ListView aListView;

    public CustomAdapter(Context context, ArrayList<City> arrayList, ListView aListView) {

        mylCities = arrayList;
        //get the layout inflater
        _layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.aListView= aListView;
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

        aCity = mylCities.get(position);

        if (aCity != null) {

            TextView tvCityName = (TextView) view.findViewById(R.id.lv_item_tv_city_name);
            TextView tvCityTemp = (TextView) view.findViewById(R.id.lv_item_city_temp);
            ImageView ivIcon = (ImageView) view.findViewById(R.id.lv_item_weather_icon);

            tvCityName.setText(aCity.geName());
            ivIcon.setImageBitmap(aCity.getWeathers().getIconImg()); //TODO


        }


        final ImageButton deleteButton = (ImageButton) view.findViewById(R.id.ib_delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteCity(aCity);
               notifyDataSetChanged();

            }
        });

        return view;
    }


    public ArrayList<City> getMylCities() {
        return mylCities;
    }

    void deleteCity(City aCity){
        Iterator i = mylCities.iterator();

        for(City a : mylCities){
            if(a.getId() == aCity.getId()) mylCities.remove(a);
        }

    }

}
