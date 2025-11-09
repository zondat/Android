package vn.fpt.coursesupport.prm.services.webservice;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.fpt.coursesupport.prm.services.webservice.datamodel.Weather;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.weatherapi.com/v1/";
    private TextView txtWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtWeather = findViewById(R.id.txtWeather);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        WeatherForecast weatherForecastAPI = retrofit.create(WeatherForecast.class);
        weatherForecastAPI.currentWeather("...", "hanoi").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                String weather = response.body().getCurrent().toString();
                Log.d("WeatherForecast", "The weather today: " + weather);
                txtWeather.setText(weather);
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d("WeatherForecast", "Failed");
            }
        });

    }
}