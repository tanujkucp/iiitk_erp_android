package in.ac.iiitkota.iiitk_erp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.iiitkota.iiitk_erp.Adapters.FacultyCoursesAdapter;
import in.ac.iiitkota.iiitk_erp.R;

public class FacultyDashboardFragment extends Fragment {

    RecyclerView recyclerCourses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_faculty_dashboard, container, false);

        recyclerCourses=v.findViewById(R.id.courses);
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getActivity()));


        //fetch the courses for the faculty
        fetchCourses();

        //todo attach to the FacultyCoursesAdapter
        //populate adapter with the data
        int arr[]={1,2,3,4};
        recyclerCourses.setAdapter(new FacultyCoursesAdapter(getActivity(),arr));

        return v;
    }

    public void fetchCourses(){
        //todo call an API with the faculty ID to fetch the courses taught by him/her

        //todo resolve the result received

    }


}
