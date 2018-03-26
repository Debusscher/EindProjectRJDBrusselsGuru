package ehb.be.eindprojectrjd_brusselsguru.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by dajian on 19/03/18.
 */
@Entity
public class Museum {

    //POJO

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name, tel, mail, website;
    private String adres, locality;
    private int postcode;
    private double lat, longitude;

    public Museum() {
    }

    @Ignore
    public Museum(int id, String name, String tel, String mail, String website, String adres, String locality, int postcode, LatLng coord) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.mail = mail;
        this.website = website;
        this.adres = adres;
        this.locality = locality;
        this.postcode = postcode;
        this.lat = coord.latitude;
        this.longitude = coord.longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Ignore
    public LatLng getCoord() {
        return new LatLng(lat, longitude);
    }

    @Ignore
    public void setCoord(LatLng coord) {
        this.lat = coord.latitude;
        this.longitude = coord.longitude;
    }

    public double getLat() {
        return lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return getName();
    }
}