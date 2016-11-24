package shindra.meteo.UI.GUI.UiSearchCity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import shindra.meteo.R;
import shindra.meteo.UI.GUI.GUI;

public class UiSearchCity extends AppCompatActivity {

    public static final String CITY_NAME ="cityName";
    public static  final String CITY_COUNTRY ="cityCountry";
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_search_city);




        Button btnCancel = (Button) findViewById(R.id.btn_dialog_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();

            }
        });

        Button btnOk = (Button) findViewById(R.id.btn_dialog_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edCityName = (EditText) findViewById(R.id.et_city_name);
                EditText edCityCountry = (EditText) findViewById(R.id.et_city_country);



                Intent resultIntent = new Intent();


                resultIntent.putExtra(CITY_NAME,edCityName.getText().toString());
                resultIntent.putExtra(CITY_COUNTRY,edCityCountry.getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });

    }
}
