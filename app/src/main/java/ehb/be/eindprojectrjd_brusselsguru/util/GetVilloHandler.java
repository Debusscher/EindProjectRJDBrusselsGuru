package ehb.be.eindprojectrjd_brusselsguru.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import ehb.be.eindprojectrjd_brusselsguru.model.Museum;
import ehb.be.eindprojectrjd_brusselsguru.model.POIDatabase;
import ehb.be.eindprojectrjd_brusselsguru.model.Villo;

/**
 * Created by dajian on 20/03/18.
 */

public class GetVilloHandler extends Handler{

    private Context mContext;

    public GetVilloHandler(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void handleMessage (Message message) {
        try {
        String villoData = message.getData().getString("villo_data");
        JSONArray jsonArray = new JSONArray(villoData);

        int arraySize = jsonArray.length();

        for(int i=0; i<arraySize; i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);


            Villo villo = new Villo();
            villo.setNumber(jsonObject.getInt("number"));
            villo.setName(jsonObject.getString("name"));
            villo.setAddress(jsonObject.getString("address"));
            villo.setAvailable_bike_stands (jsonObject.getInt("available_bike_stands"));
            villo.setAvailable_bikes (jsonObject.getInt("available_bikes"));

            //lat en long zitten in object position
            JSONObject position = jsonObject.getJSONObject("position");
            villo.setLat(position.getDouble("lat"));
            villo.setLng(position.getDouble("lng"));

            //zelf toegevoegd
            villo.setLast_update(System.currentTimeMillis());

            POIDatabase.getInstance(mContext).getVilloDAO().insert(villo);

            Date date = new Date(villo.getLast_update());
            Log.d("Update", date.toString());
            Log.d("Update", villo.toString());

        }

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
            sp.edit().putBoolean("data_downloaded", true).apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
