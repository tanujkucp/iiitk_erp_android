package in.ac.iiitkota.iiitk_erp.Others;

//Made by Tanuj
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.ac.iiitkota.iiitk_erp.R;


public class MyToast {
    private Activity context;
    private String message;
    private int length;
    private Boolean isPositive=true;

    public MyToast(Activity cont, String message, int length, Boolean isPositive){
        this.context=cont;
        this.message=message;
        this.length=length;
        this.isPositive=isPositive;
    }

    public MyToast(Activity cont,String message,int length){
        this(cont,message,length,true);
    }

    public MyToast(Activity cont,String message){
        this(cont,message, Toast.LENGTH_SHORT,true);
    }

    public MyToast(Activity cont,String message,Boolean isPositive){
        this(cont,message,Toast.LENGTH_SHORT,isPositive);
    }

    public void show(){
        Toast toast=Toast.makeText(context,message,length);
        View toastView = toast.getView();
        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(16);
        toastMessage.setTextColor(Color.WHITE);
        toastMessage.setPadding(20,5,20,5);
        if (Build.VERSION.SDK_INT >20) {
            if (isPositive) toastView.setBackground(context.getDrawable(R.drawable.toast_green));
            else toastView.setBackground(context.getDrawable(R.drawable.toast_red));
        }else{
            if (isPositive) toastView.setBackgroundColor(Color.GREEN);
            else toastView.setBackgroundColor(Color.RED);
        }
        toast.setDuration(length);
        toast.show();
    }

}
