package in.ac.iiitkota.iiitk_erp.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.Preferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                //check whether user is logged in or not
                if (Preferences.isUserLoggedIn(SplashActivity.this)){
                    //this means user is logged in
                    intent=new Intent(SplashActivity.this,MainActivity.class);
                }else intent=new Intent(SplashActivity.this,LoginActivity.class);

                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        },2000);

    }
}
