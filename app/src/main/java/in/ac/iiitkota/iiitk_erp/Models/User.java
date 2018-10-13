package in.ac.iiitkota.iiitk_erp.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

public class User {

    private String _id;
    private String firstname,middlename,lastname,fathername,mothername;
    private String permanent_address;
    private String role;
    private String phone;
    private String email;
    private String created_at;

    public User(JSONObject data) throws JSONException {
        Log.e("login","USER :"+data.toString());
        if(data.has("_id"))this._id=data.getString("_id");
        if(data.has("firstname"))this.firstname=data.getString("firstname");
        if(data.has("middlename"))this.middlename=data.getString("middlename");
        if(data.has("lastname"))this.lastname=data.getString("lastname");
        if(data.has("phone"))this.phone=data.getString("phone");
        if(data.has("email"))this.email=data.getString("email");
        if(data.has("permanent_address"))this.permanent_address=data.getString("permanent_address");
        if(data.has("fathername"))this.fathername=data.getString("fathername");
        if(data.has("mothername"))this.mothername=data.getString("mothername");
        if (data.has("role")) this.role= data.getString("role");
        if (data.has("created_at")) this.created_at= data.getString("created_at");
    }

    public User() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  _id.equals(user.get_id());
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
