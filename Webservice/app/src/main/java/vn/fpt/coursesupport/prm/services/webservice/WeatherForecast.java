package vn.fpt.coursesupport.prm.services.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.fpt.coursesupport.prm.services.webservice.datamodel.Weather;

public interface WeatherForecast {
    @GET("current.json")
    Call<Weather> currentWeather(@Query("key") String key, @Query("q") String location);

}
