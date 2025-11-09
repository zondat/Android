package vn.fpt.coursesupport.prm.services.webservice.datamodel;

import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("name")
    private String name;

    @SerializedName("region")
    private String region;

    @SerializedName("country")
    private String country;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("tz_id")
    private String timezoneId;

    @SerializedName("localtime_epoch")
    private long localtimeEpoch;

    @SerializedName("localtime")
    private String localtime;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public String getTimezoneId() { return timezoneId; }
    public void setTimezoneId(String timezoneId) { this.timezoneId = timezoneId; }

    public long getLocaltimeEpoch() { return localtimeEpoch; }
    public void setLocaltimeEpoch(long localtimeEpoch) { this.localtimeEpoch = localtimeEpoch; }

    public String getLocaltime() { return localtime; }
    public void setLocaltime(String localtime) { this.localtime = localtime; }
}