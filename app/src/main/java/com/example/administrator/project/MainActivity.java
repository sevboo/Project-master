package com.example.administrator.project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Runnable {
    Button btnGameStart;
    Thread thread;
    String sendDate = "HI Server";
    String recvDate = "";
    //int a
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnGameStart = (Button)findViewById(R.id.btStart);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnGameStart.setOnClickListener(new View.OnClickListener() { //게임 스타트 버튼 눌렀을때
            @Override
            public void onClick(View view) {
                timeThread();
                if(recvDate == "44") //방이 가득찼을때
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                        }
                    });
                    alert.setMessage("게임 자리가 다찼습니다. 다음게임 이용해주세요!!");
                    alert.show();
                }
                else { // 게임 시작을 할때
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void run() { // 소켓통신과 통신 방에 자리가 있는지 여부 확인.
        try{
                //socket을 만든다
                Socket socket = new Socket("192.168.100.138", 5011);
                //출력용 스트림 생성
                PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
                //데이터 전송
                outputStream.write(sendDate);
                //outstream 버퍼 비우기
                outputStream.flush();
                    //데이터 받기용 입력슽트림 생성
                    BufferedReader inputStream = new BufferedReader(
                                new InputStreamReader(socket.getInputStream(), "utf-8"));
                    //데이터 받기
                    recvDate = (String)inputStream.readLine();
                    Log.i("MYLOG", "read " + recvDate);

                //소켓종료
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
                Log.e("MYLOG", e.getMessage());
            }
    }
    public void timeThread() {

        dialog = new ProgressDialog(this);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Wait...");
        dialog.setMessage("Please wait while loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
        new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                try {
                    thread = new Thread(this);
                    thread.start();
                    Thread.sleep(5000);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
                dialog.dismiss();
            }
        }).start();
    }
}


