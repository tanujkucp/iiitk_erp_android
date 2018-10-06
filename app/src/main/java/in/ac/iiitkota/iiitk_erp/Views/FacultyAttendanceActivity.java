package in.ac.iiitkota.iiitk_erp.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import in.ac.iiitkota.iiitk_erp.Adapters.FacultyPollAdapter;
import in.ac.iiitkota.iiitk_erp.Others.DBHelper;
import in.ac.iiitkota.iiitk_erp.Others.MyToast;
import in.ac.iiitkota.iiitk_erp.R;

public class FacultyAttendanceActivity extends AppCompatActivity {

    RecyclerView recyclerStudents;
    TextView pollStatus;
    DBHelper dbHelper;
    ArrayList<String> IDs = new ArrayList<>(), names = new ArrayList<>();
    HashMap<String, String> data = new HashMap<>();
    int counter=0,size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        recyclerStudents = findViewById(R.id.students);
        pollStatus=findViewById(R.id.poll_status);
        pollStatus.setEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //todo get data of selected course from intent

        //todo fetch values from API using this data
        fetchStudentsList();

        recyclerStudents.setLayoutManager(new LinearLayoutManager(this));
        //todo resolve the result and populate adapter using FacultyPollAdapter
        //for checking---------
        FacultyPollAdapter adapter = new FacultyPollAdapter(IDs, names, new FacultyPollAdapter.FacultyPollAdapterListener() {
            @Override
            public void onClickPresent(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this, (position+1) + " Present").show();
                //add this to the map
                addOneRow(position, 0);
            }

            @Override
            public void onClickAbsent(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this, (position+1) + " Absent", false).show();
                addOneRow(position, 1);
            }

            @Override
            public void onClickLeave(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this, (position+1) + " Leave").show();
                addOneRow(position, 2);
            }
        });
        recyclerStudents.setAdapter(adapter);

        //initialize sqlite
        dbHelper = new DBHelper(this, null, 1);
        //find backup of this batch if exists and update the adapter
        findBackup();
    }

    public void fetchStudentsList() {
        //call an API
        //fill the lists
        for (int i=0;i<5;i++){
            IDs.add("2017kucp101"+i);
            names.add("Name "+i);
        }
        size=IDs.size();
        pollStatus.setText("0/"+size+" Marked.");
    }

    public void uploadAttendance(View view) {
        pollStatus.setEnabled(false);
        //call an API
        new MyToast(this,"Uploading").show();
        Log.e("attend",data.toString());
        HashMap<String,String> database=dbHelper.readAll();
        Log.e("attend",database.toString());

        //todo once uploaded successfully , delete all data backup
        if (dbHelper.deleteBackup()<1) new MyToast(this,"Error in deleting data").show();
    }

    public void addOneRow(int position, int value) {
        String key = IDs.get(position);
        if (!data.containsKey(key)) {
            data.put(key, "" + value);
            //add this row to the sqlite
            long num = dbHelper.append(key, value);
            if (num == -1) new MyToast(FacultyAttendanceActivity.this, "Error inserting data offline!", false).show();
            else{
                ++counter;
                if (counter==size) {
                    pollStatus.setText(counter+"/"+size+" Marked. Upload Now");
                    pollStatus.setEnabled(true);
                }
                else pollStatus.setText(counter+"/"+size+" Marked");
            }
        } else {
            data.remove(key);
            data.put(key, "" + value);
            //update this value in sqlite
            int num = dbHelper.update(key, value);
            if (num == 0) new MyToast(FacultyAttendanceActivity.this, "Error in updating offline!", false).show();
        }
    }

    public void allPresent(View view){
        for (int i=0;i<size;i++){
            addOneRow(i,0);
        }
    }

    public void allAbsent(View view){
        for (int i=0;i<size;i++){
            addOneRow(i,1);
        }
    }

    public void findBackup(){
        data=dbHelper.readAll();
        int dataSize=data.size();
        counter=dataSize;
        Log.e("attend","Reading backup");
        Log.e("attend",data.toString());
        //todo update the adapter

        //update the status text view

        if (dataSize==size){
            pollStatus.setText(counter+"/"+size+" Marked. Upload Now");
            pollStatus.setEnabled(true);
        }else pollStatus.setText(counter+"/"+size+" Marked.");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
