package ehb.be.eindprojectrjd_brusselsguru.fragments;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

import ehb.be.eindprojectrjd_brusselsguru.R;
import ehb.be.eindprojectrjd_brusselsguru.model.Museum;
import ehb.be.eindprojectrjd_brusselsguru.util.GetMuseumHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dajian on 19/03/18.
 */

public class ListFragment extends Fragment {

    ArrayAdapter<Museum> museumArrayAdapter;
    GetMuseumHandler handler;

    public ListFragment() {
    }

    public static ListFragment newInstance(){
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.list_fragment,container,false);

        ListView lv_museum = root.findViewById(R.id.lv_museum);
        museumArrayAdapter = new ArrayAdapter<Museum>(getActivity(),android.R.layout.simple_list_item_1);

        lv_museum.setAdapter(museumArrayAdapter);

        handler = new GetMuseumHandler(museumArrayAdapter);
        downloadData();
        return root;
    }

    private void downloadData(){

        Thread backGroundThread = new Thread(new Runnable(){


            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://opendata.brussel.be/api/records/1.0/search/?dataset=musea-in-brussel&rows=45")
                            .get()
                            .build();

                    Response response = client.newCall(request).execute();

                    Message message = new Message();
                    Bundle data = new Bundle();
                    data.putString("json_museum_data",response.body().string());
                    message.setData(data);
                    handler.sendMessage(message);

                    Log.d("DATA",response.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        backGroundThread.start();
    }

}
