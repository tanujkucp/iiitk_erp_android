package in.ac.iiitkota.iiitk_erp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.iiitkota.iiitk_erp.R;
/*
This fragment will show the user profile to both faculty and student user
//todo make cool UI and then show data by using stored shared preferences
//eg. UserData.getInstance(context).getUser();
 */
public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
