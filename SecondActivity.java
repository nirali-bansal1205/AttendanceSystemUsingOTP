package com.example.hp_.attendance_system;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.String.format;

public class SecondActivity extends AppCompatActivity {

    TabHost tb;
    EditText e1,e2,e3,e4,e5,e6;
    Spinner sp1,sp2;
    Button bt1,bt2;
    String values[];
    RequestQueue requestQueue;
    String url,url1,course,course1,m,d,y;
    int dd,mm,yy,hour,min;
    int number,num;
    Random random=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        e1= (EditText) findViewById(R.id.editText7);
        e2= (EditText) findViewById(R.id.editText8);
        e3= (EditText) findViewById(R.id.editText9);
        e4= (EditText) findViewById(R.id.editText10);
        e6= (EditText) findViewById(R.id.editText13);
        e5= (EditText) findViewById(R.id.editText14);
        sp1= (Spinner) findViewById(R.id.spinner1);
        sp2= (Spinner) findViewById(R.id.spinner2);
        bt1= (Button) findViewById(R.id.button4);
        bt2= (Button) findViewById(R.id.button5);
        tb= (TabHost) findViewById(R.id.tabHost);
        Calendar ref=Calendar.getInstance();
        final int dd=ref.get(Calendar.DAY_OF_MONTH);
        final int mm=ref.get(Calendar.MONTH);
        final int yy=ref.get(Calendar.YEAR);
        hour=ref.get(Calendar.HOUR_OF_DAY);
        min=ref.get(Calendar.MINUTE);

        url="https://app-1532501733.000webhostapp.com/studentsetdata.php";
        url1="https://app-1532501733.000webhostapp.com/otpstorage.php";
        requestQueue = Volley.newRequestQueue(SecondActivity.this);


        tb.setup();
        TabHost.TabSpec tabSpec=tb.newTabSpec("");
        tabSpec.setIndicator("Add Student");
        tabSpec.setContent(R.id.tab1);
        tb.addTab(tabSpec);

        tabSpec=tb.newTabSpec("");
        tabSpec.setIndicator("Take Attendance");
        tabSpec.setContent(R.id.tab2);
        tb.addTab(tabSpec);


        values=getResources().getStringArray(R.array.courses);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);

        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SecondActivity.this,date,yy,mm,dd).show();
            }
        });

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(SecondActivity.this,time,hour,min,false).show();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendrequest();
                Toast.makeText(SecondActivity.this, "data added", Toast.LENGTH_SHORT).show();

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                number=random.nextInt(5000)+1000;

                final Dialog dialog=new Dialog(SecondActivity.this);
                dialog.setContentView(R.layout.otp_dialog);
                dialog.setCanceledOnTouchOutside(false);

                final TextView textView= (TextView) dialog.findViewById(R.id.textView9);
                Button btn= (Button) dialog.findViewById(R.id.button6);
               // EditText et= (EditText) dialog.findViewById(R.id.editText11);
                textView.setText(String.valueOf(number));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        //Toast.makeText(MainActivity.this, ""+otp, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                sendrequest1();
                Toast.makeText(SecondActivity.this, "data added", Toast.LENGTH_SHORT).show();

            }
        });
    }
    DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            m=String.valueOf(month);
            d=String.valueOf(dayOfMonth);
            y=String.valueOf(year);
            if(month<10){
                m="0"+m;

            }
            if(dayOfMonth<10){
                d="0"+d;
            }
            e4.setText(d+"/"+m+"/"+y);
            dd=dayOfMonth;
            mm=month;
            yy=year;
        }
    };

    TimePickerDialog.OnTimeSetListener time=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            e5.setText(hourOfDay+":"+minute);
            hour=hourOfDay;
            min=minute;
        }
    };

    public void sendrequest(){
        StringRequest req=new StringRequest(Request.Method.POST, url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> list=new HashMap<String, String>();
                course=sp1.getSelectedItem().toString();
                list.put("student_name",e1.getText().toString());
                list.put("student_username",e2.getText().toString());
                list.put("student_password",e3.getText().toString());
                list.put("student_course",course);
                return list;
            }
        };
        requestQueue.add(req);
    }

    public void sendrequest1(){
        StringRequest req=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> list=new HashMap<String, String>();
                course1=sp2.getSelectedItem().toString();
                num=number;
                list.put("course",course1);
                list.put("subject",e6.getText().toString());
                list.put("date",e4.getText().toString());
                list.put("time",e5.getText().toString());
                list.put("otp",String.valueOf(num));
                return list;
            }
        };
        requestQueue.add(req);

    }
}