package ehb.be.eindprojectrjd_brusselsguru.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ehb.be.eindprojectrjd_brusselsguru.model.Museum;
import ehb.be.eindprojectrjd_brusselsguru.model.POIDatabase;

/**
 * Created by dajian on 20/03/18.
 */

public class GetMuseumHandler extends Handler{

    private ArrayAdapter<Museum> museumList;
    private Context context;

    public GetMuseumHandler(ArrayAdapter<Museum> museumList){

        this.museumList = museumList;

    }

    @Override
    public void handleMessage (Message message) {
        try {

        String jsonData = message.getData().getString("json_museum_data");

        JSONObject json = new JSONObject(jsonData);

        JSONArray records = json.getJSONArray("records");


            int recordsSize = records.length();

            for (int i=0;i<recordsSize;i++){
            JSONObject e = records.getJSONObject(i);
            JSONObject fields = e.getJSONObject("fields");

            Museum museum = new Museum();

            museum.setName(fields.getString("naam_van_het_museum"));

            museum.setAdres(fields.getString("adres"));
            museum.setPostcode(Integer.parseInt(fields.getString("code_postal_postcode")));
            museum.setLocality(fields.getString("gemeente"));

            museum.setTel(fields.getString("telephone_telefoon"));
            museum.setMail(fields.getString("e_mail"));
            museum.setWebsite(fields.getString("site_web_website"));

            museum.setLat(Double.parseDouble(fields.getString("latitude_breedtegraad")));
            museum.setLongitude(Double.parseDouble(fields.getString("longitude_lengtegraad")));

            museumList.add(museum);
            POIDatabase.getInstance(context).getMuseumDao().insert(museum);
        }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
