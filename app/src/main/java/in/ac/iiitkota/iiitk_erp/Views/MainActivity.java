package in.ac.iiitkota.iiitk_erp.Views;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import in.ac.iiitkota.iiitk_erp.Models.User;
import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.Utilities.Preferences;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create New button", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loggedInUser=Preferences.getLoggedInUser(MainActivity.this);
        Log.e("MainActivity",loggedInUser.toString());

        Fragment fragment=new NewsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.base,fragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            getSupportFragmentManager().beginTransaction().replace(R.id.base,new NewsFragment()).commit();
            item.setChecked(true);
        } else if (id == R.id.nav_attendance) {
            // Handle the news action (open news fragment)
            if (loggedInUser.getRole().equals("Faculty")){
                getSupportFragmentManager().beginTransaction().replace(R.id.base,new FacultyDashboardFragment()).commit();
                item.setChecked(true);
            }
            else if (loggedInUser.getRole().equals("Student")){
                getSupportFragmentManager().beginTransaction().replace(R.id.base,new StudentDashboardFragment()).commit();
                item.setChecked(true);
            }
            else new MyToast(MainActivity.this,"No valid role found for user.",false).show();
        } else if (id == R.id.nav_events) {
            getSupportFragmentManager().beginTransaction().replace(R.id.base,new EventsFragment()).commit();
            item.setChecked(true);
        } else if (id == R.id.nav_logout) {
            //logout the user by removing his stored credentials from shared preferences
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to logout now?");
            builder.setTitle("Logout");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //delete user data
                    Preferences.deleteUser(MainActivity.this);
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                    dialog.dismiss();
                    new MyToast(MainActivity.this,"Logged out successfully!").show();
                }
            });
            builder.setNegativeButton("Cancel", null);
            AlertDialog dialog=builder.create();
            dialog.show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
