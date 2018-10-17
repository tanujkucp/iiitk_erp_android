package in.ac.iiitkota.iiitk_erp.Views;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import in.ac.iiitkota.iiitk_erp.Adapters.EventsAdapter;
import in.ac.iiitkota.iiitk_erp.Models.Event;
import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.Utilities.Server;

public class EventsFragment extends Fragment {

    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);

        recycler = v.findViewById(R.id.events_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        new EventFetchTask(getActivity()).execute();

        return v;
    }

    class EventFetchTask extends AsyncTask<String, String, ArrayList<Event>> {
       Context context;
       public EventFetchTask(Context con){
           context=con;
       }
        @Override
        protected ArrayList<Event> doInBackground(String... strings) {
            ArrayList<Event> events=new ArrayList<>();
            try {
                String result=new Server(context).get(getResources().getString(R.string.url_events));
                JSONObject obj = new JSONObject(result);
                if (obj.getBoolean("success")) {
                    JSONArray data= obj.getJSONArray("data");
                    Gson gson = new Gson();
                    for (int i=0;i<data.length();i++){
                        Event event=gson.fromJson(data.get(i).toString(),Event.class);
                        events.add(event);
                    }
                    Collections.reverse(events);
                    return events;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Event> events) {
            super.onPostExecute(events);
            if (events != null) {
                // pass the json array to populate adapter for events
                Log.d("EventFetchTask", events.toString());
                EventsAdapter adapter=new EventsAdapter(getActivity(),events);
                recycler.setAdapter(adapter);
            }
            //else todo show error message on screen
            else new MyToast(getActivity(), "Error fetching events.", false).show();
        }

    }

}
