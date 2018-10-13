package in.ac.iiitkota.iiitk_erp.Views;

import android.content.SharedPreferences;
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
import in.ac.iiitkota.iiitk_erp.Models.PollStudent;
import in.ac.iiitkota.iiitk_erp.Utilities.DBHelper;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.R;

public class FacultyAttendanceActivity extends AppCompatActivity {

    RecyclerView recyclerStudents;
    TextView pollStatus;
    DBHelper dbHelper;
    ArrayList<PollStudent> studentList = new ArrayList<>();
    HashMap<String, String> data = new HashMap<>();
    int counter = 0, size;
    FacultyPollAdapter adapter;
    SharedPreferences backupPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        recyclerStudents = findViewById(R.id.students);
        pollStatus = findViewById(R.id.poll_status);
        pollStatus.setEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //todo get data of selected course from intent

        //todo fetch values from API using this data
        fetchStudentsList();

        setAdapter();

        //initialize sqlite
        dbHelper = new DBHelper(this, null, 1);
        //find backup of this batch if exists and update the adapter
        findBackup();

        backupPref = getSharedPreferences("Backup", MODE_PRIVATE);
    }

    public void setAdapter() {
        recyclerStudents.setLayoutManager(new LinearLayoutManager(this));
        // resolve the result and populate adapter using FacultyPollAdapter
        adapter = new FacultyPollAdapter(studentList, new FacultyPollAdapter.FacultyPollAdapterListener() {
            @Override
            public void onClickPresent(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this, (position + 1) + " Present").show();
                //add this to the map
                addOneRow(position, 0);
            }

            @Override
            public void onClickAbsent(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this, (position + 1) + " Absent", false).show();
                addOneRow(position, 1);
            }

            @Override
            public void onClickLeave(View v, int position) {
                new MyToast(FacultyAttendanceActivity.this, (position + 1) + " Leave").show();
                addOneRow(position, 2);
            }
        });
        recyclerStudents.setAdapter(adapter);
    }

    public void fetchStudentsList() {
        //todo call an API
        //randomly fill the lists
        for (int i = 0; i < 5; i++) {
            studentList.add(new PollStudent("2017kucp101" + i, "Name " + i, -1));
        }
        size = studentList.size();
        pollStatus.setText("0/" + size + " Marked.");
    }

    public void uploadAttendance(View view) {
        pollStatus.setEnabled(false);
        //call an API
        new MyToast(this, "Uploading").show();
        Log.e("attend", data.toString());
        HashMap<String, String> database = dbHelper.readAll();
        Log.e("attend", database.toString());

        //todo once uploaded successfully , delete all data backup
        if (dbHelper.deleteBackup() < 1) new MyToast(this, "Error in deleting data", false).show();
        pollStatus.setText("Uploaded Successfully.");
        allClear();
        data.clear();
        counter=0;
//        SharedPreferences.Editor editor=backupPref.edit();
//        editor.remove("course");
//        editor.remove("faculty");
//        editor.apply();
    }

    public void addOneRow(int position, int value) {
        String key = studentList.get(position).getId();
        if (!data.containsKey(key)) {
            data.put(key, "" + value);
            //add this row to the sqlite
            long num = dbHelper.append(key, value);
            if (num == -1)
                new MyToast(FacultyAttendanceActivity.this, "Error inserting data offline!", false).show();
            else {
                ++counter;
                if (counter == size) {
                    pollStatus.setText(counter + "/" + size + " Marked. Upload Now");
                    pollStatus.setEnabled(true);
                } else pollStatus.setText(counter + "/" + size + " Marked");
            }
        } else {
            data.remove(key);
            data.put(key, "" + value);
            //update this value in sqlite
            int num = dbHelper.update(key, value);
            if (num == 0)
                new MyToast(FacultyAttendanceActivity.this, "Error in updating offline!", false).show();
        }
    }

    public void allPresent(View view) {
        for (int i = 0; i < size; i++) {
            addOneRow(i, 0);
            PollStudent singleObject = studentList.get(i);
            singleObject.setStatus(0);
            studentList.set(i, singleObject);
        }
        recyclerStudents.setAdapter(null);
        setAdapter();
    }

    //to update the adapter and store values in sqlite
    public void allAbsent(View view) {
        for (int i = 0; i < size; i++) {
            addOneRow(i, 1);
            PollStudent singleObject = studentList.get(i);
            singleObject.setStatus(1);
            studentList.set(i, singleObject);
        }
        recyclerStudents.setAdapter(null);
        setAdapter();
    }

    //to clear the markings from adapter
    public void allClear() {
        for (int i = 0; i < size; i++) {
            PollStudent singleObject = studentList.get(i);
            singleObject.setStatus(-1);
            studentList.set(i, singleObject);
        }
        recyclerStudents.setAdapter(null);
        setAdapter();
    }


    public void findBackup() {
        data = dbHelper.readAll();
        int dataSize = data.size();
        if (dataSize < 1) return;

        counter = dataSize;
        Log.e("attend", "Reading backup");
        Log.e("attend", data.toString());
        ArrayList<PollStudent> updatedList = new ArrayList<>();
        // update the students list with status values
        for (int i = 0; i < studentList.size(); i++) {
            PollStudent singleEntry = studentList.get(i);
            String key = singleEntry.getId();
            if (data.containsKey(key)) singleEntry.setStatus(Integer.parseInt(data.get(key)));
            updatedList.add(singleEntry);
        }
        //update the adapter
        adapter.updateStudentEntries(updatedList);
        //update the status text view
        if (dataSize == size) {
            pollStatus.setText(counter + "/" + size + " Marked. Upload Now");
            pollStatus.setEnabled(true);
        } else pollStatus.setText(counter + "/" + size + " Marked.");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int dataSize = data.size();
        if (dataSize > 0) {
            //store the details of the course and faculty username for which backup is present
//            SharedPreferences.Editor editor=backupPref.edit();
//            editor.putString("course","something here......");
//            editor.putString("faculty","faculty username here ....");
//            editor.apply();
            new MyToast(this, dataSize + " entries backed up.").show();
        }
        this.finish();
    }
}
