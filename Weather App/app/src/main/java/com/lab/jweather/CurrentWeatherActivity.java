package com.lab.jweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeatherActivity extends AppCompatActivity {
    EditText city;
    Button weatherBtn;
    TextView  mainText, descText, tempText;

public class City extends AsyncTask<String,Void,String>{

    @Override
    protected String doInBackground(String... param) {
       try{
           URL url=new URL(param[0]);
           HttpURLConnection connection= (HttpURLConnection) url.openConnection();
           connection.connect();
           InputStream i=connection.getInputStream();
           InputStreamReader r=new InputStreamReader(i);
           int data=r.read();
           String content="";
           char ch;
           while(data != -1){
               ch=(char) data;
               content=content + ch;
               data=r.read();
           }
           return content;
       }
       catch (MalformedURLException e){
           e.printStackTrace();
       } catch (IOException e){
           e.printStackTrace();
       }
       return null;
    }
}
    public void getWeatherInfo(View view){
        city = (EditText)findViewById(R.id.cityText);
        weatherBtn = (Button)findViewById(R.id.btn_weather);
        mainText = (TextView) findViewById(R.id.tv_main);
        descText = (TextView) findViewById(R.id.tv_desc);
        tempText = (TextView) findViewById(R.id.tv_temp);
        String cityName = city.getText().toString();
        String content;
        City ci=new City();
        try{
            content=ci.execute("http://openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=439d4b804bc8187953eb36d2a8c26a02").get();
            //Log.i("content",content);
            JSONObject jsonObject=new JSONObject(content);
            String weatherData=jsonObject.getString("weather");
            String mainTem=jsonObject.getString("main");
            //Log.i("weatherData",weatherData);
            JSONArray array=new JSONArray(weatherData);
            String main="";
            String description="";
            String temperature="";
            for(int i=0;i<array.length();i++){
                JSONObject weatherPart=array.getJSONObject(i);
                main=weatherPart.getString("main");
                description=weatherPart.getString("description");
            }
            JSONObject mainPart=new JSONObject(mainTem);
            temperature=mainPart.getString("temp");
            /*Log.i("Temperature",temperature);
            Log.i("main",main);
            Log.i("description",description);*/
            String result1=main;
            String result2=description;
            String result3=temperature+"°C";
            mainText.setText(result1);
            descText.setText(result2);
            tempText.setText(result3);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

    }}