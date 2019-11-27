package com.timeline.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Clocks extends AppCompatActivity {


    TextView myOutput;
    TextView myRec;
    TextView kind;
    Button myBtnStart;
    Button myBtnRec;
    long[] kt = {5,1,5,1,5};
    final static int Init =0;
    final static int Run =1;
    final static int Pause =2;
    int ktcount  =0;
    int cur_Status = Init; //현재의 상태를 저장할변수를 초기화함.
    int myCount=1;
    long myBaseTime;
    long myPauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks);

        myOutput = (TextView) findViewById(R.id.time_out);
        myRec = (TextView) findViewById(R.id.record);
        myBtnStart = (Button) findViewById(R.id.btn_start);
        myBtnRec = (Button) findViewById(R.id.btn_rec);
        myOutput.setText("00:50:00");
        kind = (TextView) findViewById(R.id.kind);
        Intent arg = getIntent();
        kind.setText(arg.getStringExtra("kind"));


    }

    public void myOnClick(View v){
        switch(v.getId()){
            case R.id.btn_start: //시작버튼을 클릭했을때 현재 상태값에 따라 다른 동작을 할수있게끔 구현.
                switch(cur_Status){
                    case Init:
                        myBaseTime = SystemClock.elapsedRealtime();
                        System.out.println(myBaseTime);
                        //myTimer이라는 핸들러를 빈 메세지를 보내서 호출
                        myTimer.sendEmptyMessage(0);
                        myBtnStart.setText("멈춤"); //버튼의 문자"시작"을 "멈춤"으로 변경
                        myBtnRec.setEnabled(true); //기록버튼 활성
                        cur_Status = Run; //현재상태를 런상태로 변경
                        break;
                    case Run:
                        myTimer.removeMessages(0); //핸들러 메세지 제거
                        myPauseTime = SystemClock.elapsedRealtime();
                        myBtnStart.setText("시작");
                        myBtnRec.setText("리셋");
                        cur_Status = Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now- myPauseTime);
                        myBtnStart.setText("멈춤");
                        myBtnRec.setText("기록");
                        cur_Status = Run;
                        break;


                }
                break;
            case R.id.btn_rec:
                switch(cur_Status){
                    case Run:

                        String str = myRec.getText().toString();
                        str +=  String.format("%d. %s\n",myCount,getTimeOut());
                        myRec.setText(str);
                        myCount++; //카운트 증가

                        break;
                    case Pause:
                        //핸들러를 멈춤
                        myTimer.removeMessages(0);

                        myBtnStart.setText("시작");
                        myBtnRec.setText("기록");
                        myOutput.setText("00:00:00");

                        cur_Status = Init;
                        myCount = 1;
                        myRec.setText("");
                        myBtnRec.setEnabled(false);
                        ktcount = 0;
                        break;


                }
                break;

        }
    }

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            myOutput.setText(getTimeOut());
            long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간(??)^^;
            long outTime = ((now - myBaseTime)/1000)/60;

            //sendEmptyMessage 는 비어있는 메세지를 Handler 에게 전송하는겁니다.
            Log.e("outtime",  String.valueOf(now - myBaseTime));
            Log.e("카운트",  ",,,,"+ktcount);
            Log.e("시간", " /"+getTimeOut() + ",,,,");


            myTimer.sendEmptyMessage(0);

            if(ktcount< kt.length && kt[ktcount] <= outTime){
                myBaseTime = SystemClock.elapsedRealtime();
                System.out.println(myBaseTime);
                myTimer.removeMessages(0);//핸들러 메세지 제거
                //myTimer이라는 핸들러를 빈 메세지를 보내서 호출
                myTimer.sendEmptyMessage(0);
                myBtnStart.setText("멈춤"); //버튼의 문자"시작"을 "멈춤"으로 변경
                myBtnRec.setEnabled(true); //기록버튼 활성
                cur_Status = Run; //현재상태를 런상태로 변경
                ktcount++;
            }
            else if(ktcount >= kt.length){
                myTimer.removeMessages(0); //핸들러 메세지 제거
                myPauseTime = SystemClock.elapsedRealtime();
                myBtnStart.setText("시작");
                myBtnRec.setText("리셋");
                cur_Status = Pause;
                //핸들러를 멈춤
                myTimer.removeMessages(0);//핸들러 메세지 제거
                myBtnStart.setText("시작");
                myBtnRec.setText("기록");
                myOutput.setText("00:00:00");

                cur_Status = Init;
                myCount = 1;
                myRec.setText("");
                myBtnRec.setEnabled(false);
                ktcount = 0;

            }
        }
    };
    //현재시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간(??)^^;
        long outTime = now - myBaseTime;
        String[] outt = myOutput.getText().toString().split(":");

        String easy_outTime = String.format("%02d:%02d:%02d", outTime/1000/60,outTime/1000%60,(outTime%1000)/10);
        return easy_outTime;

    }
}
