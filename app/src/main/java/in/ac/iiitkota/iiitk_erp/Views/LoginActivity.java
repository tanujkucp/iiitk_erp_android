package in.ac.iiitkota.iiitk_erp.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import in.ac.iiitkota.iiitk_erp.Models.User;
import in.ac.iiitkota.iiitk_erp.R;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.Utilities.Preferences;
import in.ac.iiitkota.iiitk_erp.Utilities.Server;

public class LoginActivity extends AppCompatActivity {

    String username,password;
    TextInputEditText viewUsername,viewPassword;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewUsername=findViewById(R.id.username);
        viewPassword=findViewById(R.id.password);
        progress=new ProgressDialog(LoginActivity.this);
        progress.setCancelable(false);
        progress.setIndeterminate(true);
    }

    public void login(View view){
        //get all the values from the views
        username=viewUsername.getText().toString().trim();
        password=viewPassword.getText().toString().trim();
        if (areCredentialsValid(username,password)) new UserLoginTask(username,password).execute();
        else{
            viewPassword.setText("");
            new MyToast(this,getString(R.string.invalid_credentials),false).show();
        }
    }

    public void forgotPassword(View view){
        //get all the values from the views
        //todo call an api for reset password functionality
    }

    public boolean areCredentialsValid(String email,String password){
        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return VALID_EMAIL_ADDRESS_REGEX .matcher(email).matches() && password.length()>4;
    }

    class UserLoginTask extends AsyncTask<String, String, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setMessage("Logging In User..");
            progress.show();
        }

        HashMap<String,String> map = new HashMap<>();
        UserLoginTask(String email, String password) {
            map.put("email",email);
            map.put("password",password);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            // attempt authentication against a network service.
            String result="";
            try {
                // network access.
                Gson gson = new Gson();
                String json = gson.toJson(map);
                result = new Server(LoginActivity.this).post(getResources().getString(R.string.url_login),json);
                JSONObject obj=new JSONObject(result);
                Log.e("result",result);
                if (obj.getBoolean("success")){
                    JSONArray array=obj.getJSONArray("data");
                    JSONObject data = array.getJSONObject(0);
                    Preferences.initUser(LoginActivity.this,new User(data));
                    return true;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            progress.dismiss();
            super.onPostExecute(success);
            if (success) {
                new MyToast(LoginActivity.this,"Login Successful").show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {
                new MyToast(LoginActivity.this,getString(R.string.error),false).show();
                viewPassword.setText("");
            }
        }
    }

}
