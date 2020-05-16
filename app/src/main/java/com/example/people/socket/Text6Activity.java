//package com.example.people.socket;
//
//
//
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.people.R;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//
//public class Text6Activity extends AppCompatActivity {
//    private Button button,button2;
//    private TextView textView;
//    private EditText editText;
//    private DataOutputStream out;
//    private DataInputStream in;
//    private Socket socket;
//private  boolean  connection_state=true;z
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_text2);
//        button2 = findViewById(R.id.button4_jieshou);
//        button = findViewById(R.id.button5asd);
//        textView = findViewById(R.id.textView2asd);
//        editText = findViewById(R.id.editTextasd);
////button2.setOnClickListener(new View.OnClickListener() {
////    @Override
////    public void onClick(View v) {
////        new Thread(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    socket = new Socket("172.20.10.3", 8081);
////
////                    for (;;){
////
////                        in=new DataInputStream(socket.getInputStream());
////                        String msg=in.readUTF();
////                        Log.d("输入","...................."+msg);
////                    }
////
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////
////            }
////        }).start();
////    }
////});
//
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            socket = new Socket("172.20.10.3", 8081);
//                          out=new DataOutputStream(socket.getOutputStream());
//                          out.writeUTF(editText.getText().toString());
//                      //  in=new DataInputStream(socket.getInputStream());
//                        Log.d("输出",".................."+out);
//                            for (;;){
//
//                                in=new DataInputStream(socket.getInputStream());
//                                final String msg=in.readUTF();
//                                Log.d("输入","...................."+msg);
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        textView.setText(msg);
//                                    }
//                                });
//                            }
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }).start();
//            }
//        });
//
//
//
//
//
//    }
//
//
//public void Receive()  {
//
//    try {
//        in=new  DataInputStream(socket.getInputStream());
//for(;;){
//
//
//    String msg=in.readUTF();
//    textView.setText(msg);
//}
//
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//
//}
//public void Send()  {
//
//    try {
//        out=new DataOutputStream(socket.getOutputStream());
//         out.writeUTF(editText.getText().toString());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//}
//}
//
//
