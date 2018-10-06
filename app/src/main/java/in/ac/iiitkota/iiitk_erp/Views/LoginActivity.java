package in.ac.iiitkota.iiitk_erp.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import in.ac.iiitkota.iiitk_erp.R;

public class LoginActivity extends AppCompatActivity {

    String username,password;
    boolean isStudent=true;
    EditText viewUsername,viewPassword;
    RadioGroup viewRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewUsername=findViewById(R.id.username);
        viewPassword=findViewById(R.id.password);
        viewRadioGroup=findViewById(R.id.radios);
    }

    public void login(View view){
        //get all the values from the views
        getValues();
        //todo call authentication api with the values to login
        this.startActivity(new Intent(this,MainActivity.class));
        //resolve the result returned
    }

    public void forgotPassword(View view){
        //get all the values from the views
        getValues();

        //todo call an api for reset password functionality
    }

    public void getValues(){
        username=viewUsername.getText().toString().trim();
        password=viewPassword.getText().toString().trim();
        int selectedID=viewRadioGroup.getCheckedRadioButtonId();
        if (selectedID==R.id.radio_faculty) isStudent=false;
    }


}
