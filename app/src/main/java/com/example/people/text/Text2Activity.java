package com.example.people.text;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.people.Entity.ChatEntity;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.R;
import com.example.people.adapter.ChatAdapter;
import com.example.people.tableDo.CpnViewModel;
import com.example.people.tableDo.CpnRe;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Text2Activity extends AppCompatActivity {
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerView;
    private Socket socket;
    private DataInputStream in;
    private List<ChatEntity> allmsg=new ArrayList<>();
    private CpnViewModel cpnViewModel;
    private CpnRe cpnRe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2);


        cpnViewModel=ViewModelProviders.of(Text2Activity.this).get(CpnViewModel.class);



    }

    public void addaa(View view){

        CpnMessage cpnMessage=new CpnMessage();
        cpnMessage.setNickname("asdasdd");
        cpnMessage.setWorkin("asddddddd");
        cpnViewModel.insert(cpnMessage);
    }
    public void de(View view){
        CpnMessage cpnMessage=new CpnMessage();
        cpnMessage.setId(0);
        cpnViewModel.deleteall();
    }
    public void find(View view){
        List<CpnMessage>list=cpnViewModel.list();
        System.out.println("f----------"+list);
    }
    public void up(View view){

    }
}