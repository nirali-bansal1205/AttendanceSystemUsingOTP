package com.example.hp_.attendance_system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class FirstFragment extends Fragment {

    EditText e1,e2;
    Spinner sp;
    Button b1,b2;
    String data[],n;
    String username,password;
    String url,url1;
    RequestQueue requestQueue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_first, container, false);
        e1= (EditText) v.findViewById(R.id.editText);
        e2= (EditText) v.findViewById(R.id.editText2);
        b1= (Button) v.findViewById(R.id.button);
        b2= (Button) v.findViewById(R.id.button2);
        sp= (Spinner) v.findViewById(R.id.spinner);
        url="https://app-1532501733.000webhostapp.com/teacherlogin.php";
        url1="https://app-1532501733.000webhostapp.com/studentlogin.php";
        requestQueue = Volley.newRequestQueue(getActivity());
        data=getResources().getStringArray(R.array.login_type);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,data);
        sp.setPrompt("Select Login Type");
        sp.setAdapter(adapter);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=sp.getSelectedItem().toString();
                if(n.equalsIgnoreCase("teacher login")){
                    sendrequest();
                }
                else if(n.equalsIgnoreCase("student login")) {
                    sendrequest1();
                }

            }
        });

         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 SecondFragment second=new SecondFragment();

                 FragmentManager fm=getFragmentManager();
                 FragmentTransaction ft=fm.beginTransaction();
                 ft.replace(R.id.main_page,second);
                 ft.addToBackStack("");
                 ft.commit();
             }
         });


        return v;
    }public void sendrequest(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String code=jsonObject.getString("code");
                    if(code.equals("login_failed")){
                        Toast.makeText(getActivity(), "login failed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent=new Intent(getActivity(),SecondActivity.class);
                        startActivity(intent);
                    }

                }
                catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> list=new HashMap<String, String>();
                username=e1.getText().toString();
                password=e2.getText().toString();
                list.put("teacher_username",username);
                list.put("teacher_password",password);
                return list;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void sendrequest1(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                    String code=jsonObject.getString("code");
                    if(code.equals("login_failed")){
                        Toast.makeText(getActivity(), "login failed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent=new Intent(getActivity(),ThirdActivity.class);
                        startActivity(intent);
                    }

                }
                catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> list=new HashMap<String, String>();
                username=e1.getText().toString();
                password=e2.getText().toString();
                list.put("student_username",username);
                list.put("student_password",password);
                return list;
            }
        };
        requestQueue.add(stringRequest);

    }
}

