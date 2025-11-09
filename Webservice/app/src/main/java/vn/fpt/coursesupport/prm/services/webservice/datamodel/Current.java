package vn.fpt.coursesupport.prm.services.webservice.datamodel;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("last_updated_epoch")
    private long lastUpdatedEpoch;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("temp_c")
    private double tempC;

    @SerializedName("temp_f")
    private double tempF;

    @SerializedName("is_day")
    private int isDay;

    @SerializedName("condition")
    private Condition condition;

    @SerializedName("wind_mph")
    private double windMph;

    @SerializedName("wind_kph")
    private double windKph;

    @SerializedName("wind_degree")
    private int windDegree;

    @SerializedName("wind_dir")
    private String windDir;

    @SerializedName("pressure_mb")
    private double pressureMb;

    @SerializedName("pressure_in")
    private double pressureIn;

    @SerializedName("precip_mm")
    private double precipMm;

    @SerializedName("precip_in")
    private double precipIn;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("cloud")
    private int cloud;

    @SerializedName("feelslike_c")
    private double feelslikeC;

    @SerializedName("feelslike_f")
    private double feelslikeF;

    @SerializedName("windchill_c")
    private double windchillC;

    @SerializedName("windchill_f")
    private double windchillF;

    @SerializedName("heatindex_c")
    private double heatindexC;

    @SerializedName("heatindex_f")
    private double heatindexF;

    @SerializedName("dewpoint_c")
    private double dewpointC;

    @SerializedName("dewpoint_f")
    private double dewpointF;

    @SerializedName("vis_km")
    private double visKm;

    @SerializedName("vis_miles")
    private double visMiles;

    @SerializedName("uv")
    private double uv;

    @SerializedName("gust_mph")
    private double gustMph;

    @SerializedName("gust_kph")
    private double gustKph;

    @SerializedName("short_rad")
    private double shortRad;

    @SerializedName("diff_rad")
    private double diffRad;

    @SerializedName("dni")
    private double dni;

    @SerializedName("gti")
    private double gti;

    // Getters and setters for all fields
    public long getLastUpdatedEpoch() { return lastUpdatedEpoch; }
    public void setLastUpdatedEpoch(long lastUpdatedEpoch) { this.lastUpdatedEpoch = lastUpdatedEpoch; }

    public String getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }

    public double getTempC() { return tempC; }
    public void setTempC(double tempC) { this.tempC = tempC; }

    public double getTempF() { return tempF; }
    public void setTempF(double tempF) { this.tempF = tempF; }

    public int getIsDay() { return isDay; }
    public void setIsDay(int isDay) { this.isDay = isDay; }

    public Condition getCondition() { return condition; }
    public void setCondition(Condition condition) { this.condition = condition; }

    public double getWindMph() { return windMph; }
    public void setWindMph(double windMph) { this.windMph = windMph; }

    public double getWindKph() { return windKph; }
    public void setWindKph(double windKph) { this.windKph = windKph; }

    public int getWindDegree() { return windDegree; }
    public void setWindDegree(int windDegree) { this.windDegree = windDegree; }

    public String getWindDir() { return windDir; }
    public void setWindDir(String windDir) { this.windDir = windDir; }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cloud>70) stringBuilder.append("Cloudy day\n"); else stringBuilder.append("Clear day\n");
        if (humidity<100) stringBuilder.append("Sunny"); else stringBuilder.append("Rainy");
        return stringBuilder.toString();
    }
}
