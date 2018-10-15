package in.ac.iiitkota.iiitk_erp.Views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.Server;

/*
This fragment will show the latest news from iiitk website to both the faculty and student user.
//todo Using cool looking Cards and scroll views for showing news
 */
public class NewsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_news, container, false);
        //todo show some loading animation or placeholder image

        //fetch Latest news from IIIT Kota website server
        new NewsFetchTask().execute();
        return v;
    }

    class NewsFetchTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                return Server.get(getResources().getString(R.string.url_news));
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String news) {
            super.onPostExecute(news);
            if (news!=null){
                //todo parse the json object here and populate adapter for news
            }
            //else ..todo show some error message
        }

    }

}
