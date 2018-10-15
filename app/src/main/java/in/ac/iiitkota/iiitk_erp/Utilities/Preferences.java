package in.ac.iiitkota.iiitk_erp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import in.ac.iiitkota.iiitk_erp.Models.User;

public class Preferences {
    private static String PREF_NAME = "default_settings";

    public static void initUser(Context context,User user){
        //store the user credentials in shared preferences
        SharedPreferences pref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString("_id",user.get_id());
        editor.putString("firstname",user.getFirstname());
        editor.putString("middlename",user.getMiddlename());
        editor.putString("lastname",user.getLastname());
        editor.putString("fathername",user.getFathername());
        editor.putString("mothername",user.getMothername());
        editor.putString("permanent_address",user.getPermanent_address());
        editor.putString("role",user.getRole());
        editor.putString("phone",user.getPhone());
        editor.putString("email",user.getEmail());
        editor.putString("created_at",user.getCreated_at());
        editor.apply();
    }

    public static void deleteUser(Context context){
        SharedPreferences pref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean isUserLoggedIn(Context context){
        SharedPreferences pref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        String _id=pref.getString("_id",null);
        return _id != null;
    }

    public static User getLoggedInUser(Context context){
        SharedPreferences pref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        User user=new User();
        user.set_id(pref.getString("_id",null));
        user.setFirstname(pref.getString("firstname",null));
        user.setMiddlename(pref.getString("middlename",null));
        user.setLastname(pref.getString("lastname",null));
        user.setFathername(pref.getString("fathername",null));
        user.setMothername(pref.getString("mothername",null));
        user.setPermanent_address(pref.getString("permanent_address",null));
        user.setRole(pref.getString("role",null));
        user.setPhone(pref.getString("phone",null));
        user.setEmail(pref.getString("email",null));
        user.setCreated_at(pref.getString("created_at",null));
        return user;
    }

}
