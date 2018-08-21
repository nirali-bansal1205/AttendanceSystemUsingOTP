package com.example.hp_.attendance_system;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    EditText e1, e2;
    RadioButton r1, r2;
    String url;
    String name, user, pass,result;
    RequestQueue requestQueue;
    Button b1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        e1 = (EditText) v.findViewById(R.id.editText11);
        e2 = (EditText) v.findViewById(R.id.editText6);
        b1 = (Button) v.findViewById(R.id.button3);
        r1= (RadioButton) v.findViewById(R.id.radio1);
        r2= (RadioButton) v.findViewById(R.id.radio2);

        url = "https://app-1532501733.000webhostapp.com/teachersetdata.php";
        requestQueue = Volley.newRequestQueue(getActivity());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendrequest();
                Toast.makeText(getActivity(), "data added", Toast.LENGTH_SHORT).show();
            }
        });
        return v;

    }

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
                Bundle b=getArguments();

                name=b.getString("name_key");
                user=b.getString("user_key");
                pass=b.getString("pass_key");
                if(r1.isChecked()){
                    result=r1.getText().toString();
                }
                else{
                    result=r2.getText().toString();
                }
                list.put("teacher_name",name);
                list.put("teacher_username",user);
                list.put("teacher_password",pass);
                list.put("teacher_email",e1.getText().toString());
                list.put("teacher_phone",e2.getText().toString());
                list.put("teacher_gender",result);
                return list;
            }
        };
        requestQueue.add(req);
    }
}


