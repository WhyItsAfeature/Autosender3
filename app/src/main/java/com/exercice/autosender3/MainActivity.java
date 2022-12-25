package com.exercice.autosender3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
private Button newapproach;
private Button button;
private RadioButton radio_birthday;
private RadioButton radio_Termins;
private ListView lv_saves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS,Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);//permission request for send and read sms and contacts

        //declare GUI objects
        newapproach=findViewById(R.id.btn_newapproach);
        button=findViewById(R.id.btn_Button);
        radio_birthday=findViewById(R.id.radio_birthday);
        radio_Termins=findViewById(R.id.radio_Termins);
        lv_saves=findViewById(R.id.lv_saves);

        //Listeners
        newapproach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newapproach.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        radio_birthday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(radio_Termins.isChecked()){
                    radio_Termins.setChecked(false);
                }

            }
        });
        radio_Termins.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(radio_birthday.isChecked()){
                    radio_birthday.setChecked(false);
                }

            }
        });



    }
}