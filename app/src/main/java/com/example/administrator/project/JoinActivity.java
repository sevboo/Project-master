package com.example.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinActivity extends AppCompatActivity {
    EditText ID, NICKNAME, Password, PHONENUM, EMAIL;
    Button btnIDCheck, btnNickCheck, btnJoin, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ID = (EditText) findViewById(R.id.idEdit);
        NICKNAME = (EditText) findViewById(R.id.nickEdit);
        Password = (EditText) findViewById(R.id.pwEdit);
        PHONENUM = (EditText) findViewById(R.id.phoneEdit);
        EMAIL = (EditText) findViewById(R.id.mailEdit);

        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnIDCheck = (Button) findViewById(R.id.btnIDCheck);
        btnNickCheck = (Button) findViewById(R.id.btnNickCheck);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btnIDCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnNickCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
