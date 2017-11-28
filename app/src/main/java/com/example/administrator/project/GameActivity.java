package com.example.administrator.project;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.PrintWriter;
import java.net.Socket;

public class GameActivity extends AppCompatActivity implements Runnable{
    Button bt,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    TextView tvCountDown, tvServerRecv;

    String sendMsg = null;
    Thread thread;

    private boolean wifiConnected = false;

    private static final int COUNT_DOWN_INTERVAL = 1000;                   //Tick시간
    private static final int MILLISINFUTURE = 6 * COUNT_DOWN_INTERVAL;      //총 시간

    private int count = 5;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        bt = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);
        bt3 = (Button)findViewById(R.id.button3);
        bt4 = (Button)findViewById(R.id.button4);
        bt5 = (Button)findViewById(R.id.button5);
        bt6 = (Button)findViewById(R.id.button6);
        bt7 = (Button)findViewById(R.id.button7);
        bt8 = (Button)findViewById(R.id.button8);
        bt9 = (Button)findViewById(R.id.button9);

        tvCountDown = (TextView) findViewById(R.id.tvCountDown);
        tvServerRecv = (TextView)findViewById(R.id.tvServerRecv);

        countDownTimer();
        countDownTimer.start();

    }

    //countdown
    public void countDownTimer(){ //30초 타이머 지정 설정

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                tvCountDown.setText(String.valueOf(count));
                count --;
            }
            public void onFinish() {
                tvCountDown.setText(String.valueOf("30s Finish ."));
                AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                        count = 5;
                        countDownTimer.start();
                    }
                });
                alert.setMessage("30초가 초과되었습니다.");
                alert.show();

                //game 실패처리
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            countDownTimer.cancel();    //countdown끝났을때
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownTimer = null;
    }

    public void OnButtonCliecked(View view)
    {
        checkNetworkConnection();
        if(wifiConnected == true){
            switch (view.getId()) {
                case R.id.button:
                    sendMsg = "1";
                    newThread();
                    break;
                case R.id.button2:
                    sendMsg = "2";
                    newThread();
                    break;
                case R.id.button3:
                    sendMsg = "3";
                    newThread();
                    break;
                case R.id.button4:
                    sendMsg = "4";
                    newThread();
                    break;
                case R.id.button5:
                    sendMsg = "5";
                    newThread();
                    break;
                case R.id.button6:
                    sendMsg = "6";
                    newThread();
                    break;
                case R.id.button7:
                    sendMsg = "7";
                    newThread();
                    break;
                case R.id.button8:
                    sendMsg = "8";
                    newThread();
                    break;
                case R.id.button9:
                    sendMsg = "9";
                    newThread();
                    break;
                case R.id.btGG:
                    finish();
                    break;
            }
        }
    }

    void checkNetworkConnection(){
        //systemManager받기
        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        //network 상태 값 얻기위한 network
        NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        boolean isWiFiAval = networkInfo.isAvailable();
        wifiConnected = networkInfo.isConnected();

        // textView.append("\nwifi " + isWiFiAval + "  wifi connect "+ wifiConnected);
    }

    @Override
    public void run() {
        try {
            //socket을 만든다
            Socket socket = new Socket("192.168.100.138", 5011);

            //출력용 스트림 생성
            PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
            //데이터 전송
            outputStream.write(sendMsg);
            //outstream 버퍼 비우기
            //Log.e("")
            outputStream.flush();
/*
            //데이터 받기용 입력슽트림 생성
            BufferedReader inputStream = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "utf-8"));

            //데이터 받기
            recvMsg = (String)inputStream.readLine();

            Log.i("MYLOG", "read " + recvMsg);
*/
            //소켓종료
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("MYLOG", e.getMessage());
        }
    }

    void newThread(){
        countDownTimer.cancel();
        thread = new Thread(this);
        if (thread.getState() == Thread.State.NEW)
        {
            thread.start();
        }
    }
}