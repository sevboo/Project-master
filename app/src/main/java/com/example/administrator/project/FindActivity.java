package com.example.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindActivity extends AppCompatActivity {
    EditText phoneEdit, mailEdit, idEdit, mailEditPW, phoneEditPW;
    Button btnFindId, btnFindPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        mailEdit = (EditText) findViewById(R.id.mailEdit);
        idEdit = (EditText) findViewById(R.id.idEdit);
        mailEditPW = (EditText) findViewById(R.id.mailEditPW);
        phoneEditPW = (EditText) findViewById(R.id.phoneEditPW);

        btnFindId = (Button) findViewById(R.id.btnFindId);
        btnFindPW = (Button) findViewById(R.id.btnFindPW);

        btnFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnFindPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
