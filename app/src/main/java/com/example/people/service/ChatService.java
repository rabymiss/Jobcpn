package com.example.people.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.ChatEntity;
import com.example.people.ResumeViewModel;
import com.example.people.adapter.ChatAdapter;
import com.example.people.common.Common;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;




public class ChatService extends Service {
 private    static  final String  TAG="ChatService";
 private Socket socket;
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerView;
    private DataInputStream in;
    private List<ChatEntity> allmsg=new ArrayList<>();
    private String msg;
  private ResumeViewModel resumeViewModel;
    private Context mContext;
    private class InnBinder extends Binder implements ICommunication {
private  String msg;

    @Override
    public void callservece() {


        hellow();

    }
}
    public ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return new InnBinder();

    }

    @Override
    public void onCreate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("输入","启动socket");
                try {

                    socket=new Socket("192.168.0.104",1000);
                    for (;;){

                        in=new DataInputStream(socket.getInputStream());

                        msg=in.readUTF();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.ACTION_FASONG");//用隐式意图来启动广播
                        intent.putExtra("msg", msg);
                        System.out.println("add-----------------------------"+msg);
                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
   Log.d(TAG,"停止服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    private void    hellow(){


//return msg;
    }




}
