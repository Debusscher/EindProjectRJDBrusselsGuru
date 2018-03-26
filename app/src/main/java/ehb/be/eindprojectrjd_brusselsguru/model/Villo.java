package ehb.be.eindprojectrjd_brusselsguru.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by dajian on 19/03/18.
 */
@Entity
public class Villo {

    //POJO

    @PrimaryKey(autoGenerate = true)
    private int number;

    public String name;
    private String address;
    private double lat;
    private double lng;
    public int available_bike_stands;
    public int available_bikes;
    private long last_update;



    public Villo() {
    }

    @Ignore
    public Villo(int id, String name, String address, double lat, double lng, int available_bike_stands, int available_bikes, long last_update) {
        this.number = number;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.available_bike_stands = available_bike_stands;
        this.available_bikes = available_bikes;
        this.last_update = last_update;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getId() {
        return number;
    }

    public void setId(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;

    }
    public void setLng(double lng) {
        this.lng = lng;

    }
    public double getLng() {
        return lng;
    }

    public int getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands(int available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }

    public int getAvailable_bikes() {
        return available_bikes;
    }

    public void setAvailable_bikes(int available_bikes) {
        this.available_bikes = available_bikes;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }


    @Override
    public String toString() {
        return "Villo{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", available_bike_stands=" + available_bike_stands +
                ", available_bikes=" + available_bikes +
                ", last_update=" + last_update +
                '}';
    }
}

