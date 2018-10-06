package in.ac.iiitkota.iiitk_erp.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.ac.iiitkota.iiitk_erp.Adapters.FacultyPollAdapter;
import in.ac.iiitkota.iiitk_erp.Others.MyToast;
import in.ac.iiitkota.iiitk_erp.R;

public class FacultyAttendanceActivity extends AppCompatActivity {

    RecyclerView recyclerStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        recyclerStudents = findViewById(R.id.students);

        //todo get data of selected course from intent

        //todo fetch values from API using this data
        fetchStudentsList();

        recyclerStudents.setLayoutManager(new LinearLayoutManager(this));
        //todo resolve the result and populate adapter using FacultyPollAdapter
        //for checking---------
        FacultyPollAdapter adapter=new FacultyPollAdapter(new FacultyPollAdapter.FacultyPollAdapterListener() {
            @Override
            public void onClickPresent(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this,position+" Present").show();
            }

            @Override
            public void onClickAbsent(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this,position+" Absent",false).show();
            }

            @Override
            public void onClickLeave(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this,position+" Leave").show();
            }
        });
        recyclerStudents.setAdapter(adapter);
    }

    public void fetchStudentsList() {
        //call an ApI
    }

    public void uploadAttendance() {
        //call an API
    }
}
