package com.example.people.Fragmen.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.fastjson.JSON;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.job.ResumeEntity;
import com.example.people.Entity.job.ReturnResume;
import com.example.people.NotificationsViewModel;
import com.example.people.R;
import com.example.people.UserViewModel;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.tableDo.CpnViewModel;
import com.example.people.ui.login.LoginActivity;
import com.example.people.ui.login.CpnMyMessageActivity;
import com.example.people.ui.login.PasswordActivity;
import com.example.people.ui.login.cpnmsg.CpnMsgActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFragment extends Fragment {
private Button buttonQuite;
private ConstraintLayout constraintLayout,editpsw;
    private NotificationsViewModel notificationsViewModel;
    private List<CpnMessage> listcpn;
    private CpnViewModel cpnViewModel;
private ImageView imageshead;
private TextView nckname,tnumber;
private UserViewModel userViewModel;
LiveData<List<LoginUser>>liveData;
    private static final String TEMP_INFO = "temp_info";
    private SharedPreferences sp;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        return inflater.inflate(R.layout.fragment_my, container, false);
}



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cpnViewModel=ViewModelProviders.of(requireActivity()).get(CpnViewModel.class);
        userViewModel=ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        initview();
        findcpn();



    listcpn = cpnViewModel.list();

    for (CpnMessage cpnMessage : listcpn) {

        Picasso.get().load(ApiRetrofit.URL + cpnMessage.getIcon()).into(imageshead);
        nckname.setText(cpnMessage.getNickname());
        tnumber.setText(cpnMessage.getPhonenumber());

    }

        constraintLayout=requireActivity().findViewById(R.id.constrain_my_message);
        constraintLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), CpnMsgActivity.class);
                startActivity(intent);
            }
        });



        buttonQuite=requireActivity().findViewById(R.id.btn_quit_my);
        buttonQuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }


    public void updatepawor(View view){


    }

    private void initview() {
        nckname=requireActivity().findViewById(R.id.t_name);
        imageshead=requireActivity().findViewById(R.id.images_head);
        tnumber=requireActivity().findViewById(R.id.t_number);
        //修改密码
        editpsw=requireActivity().findViewById(R.id.con_myfrg_editpsw);
        editpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(requireActivity(),PasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    //寻找公司 intent.putExtra("username", cpnMessage.getNickname());
    //                    intent.putExtra("url", cpnMessage.getIcon());
    //                    intent.putExtra("phone",cpnMessage.getPhonenumber());
    private void findcpn() {

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_FASONG");//建议把它写一个公共的变量，这里方便阅读就不写了
        BroadcastReceiver Receive =new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
               intent.getStringExtra("msg");


                Picasso.get().load(ApiRetrofit.URL + intent.getStringExtra("url")).into(imageshead);
                nckname.setText(intent.getStringExtra("username"));
                tnumber.setText(intent.getStringExtra("phone"));





            }
        };
        broadcastManager.registerReceiver(Receive, intentFilter);



//        liveData=userViewModel.getAllUsersLive();
//        liveData.observe(this, new Observer<List<LoginUser>>() {
//            @Override
//            public void onChanged(List<LoginUser> loginUsers) {
//                for (LoginUser loginUser:loginUsers){
//
//                    SharedPreferences.Editor editor= requireActivity().getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
//                    editor.clear();
//                    editor.putString("username",loginUser.getUserId());
//                    editor.apply();
//
//                }
//            }
//        });
//        sp=requireActivity().getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE);
//        CpnMessage c=new CpnMessage();
//        c.setWorkin(sp.getString("username",""));
//        Gson gson2=new Gson();
//        String json=gson2.toJson(c);
//        final RequestBody requestBody3=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
//        Call<List<CpnMessage>> task1= Common.apicommont.findcpn(requestBody3);
//        task1.enqueue(new Callback<List<CpnMessage>>() {
//            @Override
//            public void onResponse(Call<List<CpnMessage>> call, Response<List<CpnMessage>> response) {
//                listcpn=response.body();
//
//
//                for (CpnMessage cpnMessage:listcpn){
//
//                    Picasso.get().load(ApiRetrofit.URL + cpnMessage.getIcon()).into(imageshead);
//        nckname.setText(cpnMessage.getNickname());
//        tnumber.setText(cpnMessage.getPhonenumber());
//                }
//            }
//            @Override
//            public void onFailure(Call<List<CpnMessage>> call, Throwable t) {
//
//            }
//        });



    }
}