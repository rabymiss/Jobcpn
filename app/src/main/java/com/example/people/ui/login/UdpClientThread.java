package com.example.people.ui.login;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClientThread  implements Runnable{

    private String mAddress;
    //端口
    private int port;
    //发送内容
    private Handler mHandler;
    public UdpClientThread(Handler handler, String address, int port) {
        this.mHandler = handler;
        this.mAddress = address;
        this.port = port;
    }
//    @Override
//    public void run() {
//        super.run();
//        sendSocket();
//    }
    private void sendSocket() {
        try {
/*******************发送数据***********************/
            /*
             * 向服务器端发送数据
             */
// 1.定义服务器的地址、端口号、数据
            InetAddress address = InetAddress.getByName(mAddress);
            byte[] data = "用户名：admin;密码：123".getBytes();
// 2.创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
// 3.创建DatagramSocket对象
            DatagramSocket socket = new DatagramSocket();
// 4.向服务器端发送数据报
            socket.send(packet);
/*******************接收数据***********************/
            /*
             * 接收服务器端响应的数据
             */
// 1.创建数据报，用于接收服务器端响应的数据
            byte[] data2 = new byte[1024];
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
// 2.接收服务器响应的数据
            socket.receive(packet2);
// 3.读取数据
//                String reply = new String(data2, 0, packet2.getLength());
//                Log.i("statewow","我是客户端，服务器说：" + reply);
            sendMsg(0,new String(data2, 0, data2.length));
// 4.关闭资源
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMsg(int i, Object s) {
        Message msg = new Message();
        msg.what = i;
        msg.obj = s;
        mHandler.sendMessage(msg);
    }

    @Override
    public void run() {

      sendSocket();
    }
}
