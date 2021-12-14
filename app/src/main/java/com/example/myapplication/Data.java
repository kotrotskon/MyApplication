package com.example.myapplication;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;

public class Data {

    private String android_id;
    private double latitude;
    private double longitude;
    private double speed;
    private long timestamp;
    private double acceleration;

    public Data(Location location, Location oldLocation, Context context) {
        this.android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.speed = location.getSpeed();
        this.timestamp = location.getTime();
        if (oldLocation != null)
            this.acceleration = oldLocation.getSpeed() - location.getSpeed();
        else
            this.acceleration = 0;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
}
