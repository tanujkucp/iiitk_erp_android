package in.ac.iiitkota.iiitk_erp.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.iiitkota.iiitk_erp.R;
/*
This fragment is for student account;
This fragment will show attendance details and courses registered details for the registered student;
//todo Use of circular and bar graphs to show attendance of the user
*/
public class StudentDashboardFragment extends Fragment {

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_student_dashboard, container, false);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching courses...");
        progressDialog.show();

        fetchAttendance();

        return v;
    }

    public void fetchAttendance(){
        progressDialog.dismiss();
    }

}
