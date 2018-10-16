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

import in.ac.iiitkota.iiitk_erp.Adapters.NewsAdapter;
import in.ac.iiitkota.iiitk_erp.Models.News;
import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.Utilities.Server;

/*
This fragment will show the latest news from iiitk website to both the faculty and student user.
//todo Using cool looking Cards and scroll views for showing news
 */
public class NewsFragment extends Fragment {

    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        //todo show some loading animation or placeholder image
        recycler = v.findViewById(R.id.news_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //fetch Latest news from IIIT Kota website server
        new NewsFetchTask().execute();

        return v;
    }

    class NewsFetchTask extends AsyncTask<String, String, ArrayList<News>> {
        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            try {
                ArrayList<News> news=new ArrayList<>();
                String result = Server.post(getResources().getString(R.string.url_news));
                JSONObject obj = new JSONObject(result);
                if (obj.getBoolean("success")) {
                    JSONArray data= obj.getJSONArray("data");
                    Gson gson = new Gson();
                    for (int i=0;i<data.length();i++){
                        News news1=gson.fromJson(data.get(i).toString(),News.class);
                        news.add(news1);
                    }
                    Collections.reverse(news);
                    return news;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);
            if (news != null) {
                //todo pass ther JSON array to populate adapter for news
                Log.d("NewsFetchTask", news.toString());
                NewsAdapter adapter=new NewsAdapter(getActivity(),news);
                recycler.setAdapter(adapter);
            }
            //else todo show error message on screen
            else new MyToast(getActivity(), "Error fetching news.", false).show();
        }

    }

}
