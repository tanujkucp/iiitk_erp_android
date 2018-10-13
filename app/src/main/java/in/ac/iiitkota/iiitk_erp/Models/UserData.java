package in.ac.iiitkota.iiitk_erp.Models;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import in.ac.iiitkota.iiitk_erp.Utilities.SharedPreferenceSingleton;

public class UserData {

    private static UserData ourInstance = new UserData();
    private String _id;
    private String firstname,middlename,lastname,fathername,mothername;
    private String permanent_address;
    private String role;
    private String phone;
    private String email;
    private String created_at;
    private Context context;

    private UserData() { }

    public static UserData getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new UserData();
            ourInstance.getUserData(context);
        }
        try {
            String id= ourInstance.get_id();
            if(id==null)throw new Exception();
        } catch (Exception e) {
            ourInstance = null;
            ourInstance = new UserData();
            ourInstance.getUserData(context);
        }
        return ourInstance;
    }

    public void initUserData(User user,Context context)  {
        this.context = context;
        set_id(user.get_id());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
        setPermanent_address(user.getPermanent_address());
        setCreated_at(user.getCreated_at());
        setRole(user.getRole());
        setMothername(user.getMothername());
        setFathername(user.getFathername());
        setMiddlename(user.getMiddlename());
        Log.e("login","SAVE USER");
    }

    private boolean getUserData(Context context) {
        this.context = context;
        try {
            this._id = SharedPreferenceSingleton.getInstance(context).getString("_id");
            this.firstname = SharedPreferenceSingleton.getInstance(context).getString("firstname");
            this.middlename = SharedPreferenceSingleton.getInstance(context).getString("middlename");
            this.lastname = SharedPreferenceSingleton.getInstance(context).getString("lastname");
            this.fathername = SharedPreferenceSingleton.getInstance(context).getString("fathername");
            this.mothername = SharedPreferenceSingleton.getInstance(context).getString("mothername");
            this.phone = SharedPreferenceSingleton.getInstance(context).getString("phone");
            this.email = SharedPreferenceSingleton.getInstance(context).getString("email");
            this.permanent_address=SharedPreferenceSingleton.getInstance(context).getString("permanent_address");
            this.created_at = SharedPreferenceSingleton.getInstance(context).getString("created_at");
            this.role = SharedPreferenceSingleton.getInstance(context).getString("role");
            Log.e("login","GET USER");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public User getUser() throws JSONException {
        User user=new User();
        user.setEmail(this.email);
        user.set_id(this._id);
        user.setFirstname(this.firstname);
        user.setMiddlename(this.middlename);
        user.setLastname(this.lastname);
        user.setFathername(this.fathername);
        user.setMothername(this.mothername);
        user.setPhone(this.phone);
        user.setPermanent_address(this.permanent_address);
        user.setRole(this.role);
        user.setCreated_at(this.created_at);
        return user;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
        SharedPreferenceSingleton.getInstance(context).put("_id", _id);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        SharedPreferenceSingleton.getInstance(context).put("firstname", firstname);
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
        SharedPreferenceSingleton.getInstance(context).put("middlename", middlename);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        SharedPreferenceSingleton.getInstance(context).put("lastname", lastname);
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
        SharedPreferenceSingleton.getInstance(context).put("fathername", fathername);
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
        SharedPreferenceSingleton.getInstance(context).put("mothername", mothername);
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
        SharedPreferenceSingleton.getInstance(context).put("permanent_address", permanent_address);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        SharedPreferenceSingleton.getInstance(context).put("role", role);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        SharedPreferenceSingleton.getInstance(context).put("phone", phone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        SharedPreferenceSingleton.getInstance(context).put("email", email);
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
        SharedPreferenceSingleton.getInstance(context).put("created_at", created_at);
    }
}
