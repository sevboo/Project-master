package com.example.administrator.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ID, Password;
    Button btnJoin, btnLogin, btnFind;
    CheckBox autoLogin;

    //자동로그인 관련 변수
    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //자동로그인 관련
        setting = getSharedPreferences("setting", 0);
        editor= setting.edit();

        ID = (EditText) findViewById(R.id.idEdit);
        Password = (EditText) findViewById(R.id.pwEdit);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnFind = (Button) findViewById(R.id.btnFind);
        autoLogin = (CheckBox)findViewById(R.id.autoLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    String Id = ID.getText().toString();
                    String PW = Password.getText().toString();

                    editor.putString("Id", Id);
                    editor.putString("PW", PW);
                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });


        //자동로그인 체크여부-yes일때
        if(setting.getBoolean("Auto_Login_enabled", false)){
            Intent getintent = getIntent();
            //String button_onoff_div = "0";

            //Toast.makeText(getApplicationContext(), getintent.getExtras().getString("login_button_div"), Toast.LENGTH_LONG).show();
            if(getintent.getExtras()==null){
                ID.setText(setting.getString("Id", ""));
                Password.setText(setting.getString("PW", ""));
                autoLogin.setChecked(true);
                btnLogin.performClick();
            }else{
                ID.setText(setting.getString("Id", ""));
                Password.setText(setting.getString("PW", ""));
                autoLogin.setChecked(true);
                //btnLogin.performClick();
            }
        }

    }

}
