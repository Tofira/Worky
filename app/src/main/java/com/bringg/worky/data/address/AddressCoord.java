package com.bringg.worky.data.address;

/**
 * Created by Mickael on 08/03/2018.
 */
public class AddressCoord {

    private double userLat;

    private double userLongitude;

    public AddressCoord(double userLat, double userLongitude) {
        this.userLat = userLat;
        this.userLongitude = userLongitude;
    }

    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
    }
}
