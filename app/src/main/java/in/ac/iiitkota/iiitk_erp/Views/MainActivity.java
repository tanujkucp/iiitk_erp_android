package in.ac.iiitkota.iiitk_erp.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import in.ac.iiitkota.iiitk_erp.Models.User;
import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.Utilities.Preferences;
import in.ac.iiitkota.iiitk_erp.Adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User loggedInUser;
    NavigationTabBar navigationTabBar;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loggedInUser = Preferences.getLoggedInUser(MainActivity.this);
        Log.e("MainActivity", loggedInUser.toString());
//
//        Fragment fragment=new EventsFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.base,fragment).commit();
        initBottomNavigation();

    }

    public void initBottomNavigation() {
        //initialize the view pager adapter with fragments
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (loggedInUser.getRole().equals("Faculty")) {
            adapter.addFrag(new FacultyDashboardFragment(),"Dashboard");
        } else if (loggedInUser.getRole().equals("Student")) {
            adapter.addFrag(new StudentDashboardFragment(),"Student");
        }
        adapter.addFrag(new EventsFragment(),"Events");
        adapter.addFrag(new NewsFragment(),"News");
        viewPager.setAdapter(adapter);

        //initialize buttons on the bottom navigation
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_menu_camera),
                Color.parseColor("#000000"))
                .title("Dashboard")
                .badgeTitle("Faculty")
                .build())
        ;
        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_gallery),
                        Color.parseColor("#000000"))
                        .title("Events")
                        .badgeTitle("New!")
                        .build()
        );
        models.add(new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_menu_manage),
                        Color.parseColor("#000000"))
                        .title("News")
                        .badgeTitle("New!")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager,0);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setBadgeSize(23);
        navigationTabBar.setTitleSize(28);
        navigationTabBar.setBehaviorEnabled(true);
       // add listener for page changed
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
                toolbar.setTitle(navigationTabBar.getModels().get(position).getTitle());
            }
            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
        //to show the badges with a smooth animation
        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                 final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
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
//            getSupportFragmentManager().beginTransaction().replace(R.id.base, new NewsFragment()).commit();
//            item.setChecked(true);
        } else if (id == R.id.nav_attendance) {
//            // Handle the news action (open news fragment)
//            if (loggedInUser.getRole().equals("Faculty")) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.base, new FacultyDashboardFragment()).commit();
//                item.setChecked(true);
//            } else if (loggedInUser.getRole().equals("Student")) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.base, new StudentDashboardFragment()).commit();
//                item.setChecked(true);
//            } else new MyToast(MainActivity.this, "No valid role found for user.", false).show();
        } else if (id == R.id.nav_events) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.iiitkota.ac.in"));
            MainActivity.this.startActivity(i);

//            getSupportFragmentManager().beginTransaction().replace(R.id.base, new EventsFragment()).commit();
//            item.setChecked(true);
        } else if (id == R.id.nav_logout) {
            //logout the user by removing his stored credentials from shared preferences
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to logout now?");
            builder.setTitle("Logout");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //delete user data
                    Preferences.deleteUser(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                    dialog.dismiss();
                    new MyToast(MainActivity.this, "Logged out successfully!").show();
                }
            });
            builder.setNegativeButton("Cancel", null);
            AlertDialog dialog = builder.create();
            dialog.show();

        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
