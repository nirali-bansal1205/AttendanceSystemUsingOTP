package com.example.hp_.attendance_system;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    EditText e1,e2,e3;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_second, container, false);
        e1= (EditText) v.findViewById(R.id.editText3);
        e2= (EditText) v.findViewById(R.id.editText4);
        e3= (EditText) v.findViewById(R.id.editText5);
        textView= (TextView) v.findViewById(R.id.textView6);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdFragment third=new ThirdFragment();
                String name=e1.getText().toString();
                String username=e2.getText().toString();
                String pass=e3.getText().toString();

                Bundle b=new Bundle();
                b.putString("name_key",name);
                b.putString("user_key",username);
                b.putString("pass_key",pass);
                third.setArguments(b);

                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.main_page,third);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        return v;
    }
}
