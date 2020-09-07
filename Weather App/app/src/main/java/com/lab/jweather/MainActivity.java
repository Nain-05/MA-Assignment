package com.lab.jweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lab.jweather.JCommon.JCommon;
import com.lab.jweather.JHelper.JHelper;
import com.lab.jweather.JModel.JOpenWeatherMap;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements LocationListener {
    Button bt;
    TextView city, des, time, lastUpdate, hum, cel;
    ImageView iv;
    LocationManager locationManager;
    String provider;
    static double lat, lng;
    JOpenWeatherMap jOpenWeatherMap = new JOpenWeatherMap();
    int MY_PERMISSION= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (TextView) findViewById(R.id.tvc);
        bt=(Button)  findViewById(R.id.go);
        des = (TextView) findViewById(R.id.description);
        time = (TextView) findViewById(R.id.time);
        lastUpdate = (TextView) findViewById(R.id.tlastupdate);
        hum = (TextView) findViewById(R.id.humidity);
        cel = (TextView) findViewById(R.id.celsi);
        iv = (ImageView) findViewById(R.id.imagev);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, CurrentWeatherActivity.class);
                startActivity(i);
            }
        });



        //Get Coordinates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


    }, MY_PERMISSION);
}
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null)
            Log.e("TAG","No Location");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE


            }, MY_PERMISSION);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();

        new GetWeather().execute(JCommon.apiRequest(String.valueOf(lat),String.valueOf(lng)));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    private class GetWeather extends AsyncTask<String,Void,String>{
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();

        }


        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            JHelper http = new JHelper();
            stream = http.getHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("Error: Not found city")){
                pd.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<JOpenWeatherMap>(){}.getType();
            jOpenWeatherMap = gson.fromJson(s,mType);
            pd.dismiss();

            city.setText(String.format("%s,%s",jOpenWeatherMap.getName(),jOpenWeatherMap.getSys().getCountry()));
            lastUpdate.setText(String.format("%s", JCommon.getDateNow()));
            des.setText(String.format("%s",jOpenWeatherMap.getWeather().get(0).getDescription()));
            hum.setText(String.format("%d%%",jOpenWeatherMap.getMain().getHumidity()));
            time.setText(String.format("SUNRISE AT: %s AM \n SUNSET AT: %s PM",JCommon.unixTimeStampToDateTime(jOpenWeatherMap.getSys().getSunrise()),JCommon.unixTimeStampToDateTime(jOpenWeatherMap.getSys().getSunset())));
            cel.setText(String.format("%.2f °C",jOpenWeatherMap.getMain().getTemp()));
            Picasso.with(MainActivity.this)
                    .load(JCommon.getImage(jOpenWeatherMap.getWeather().get(0).getIcon()))
                    .into(iv);

        }

    }

}

