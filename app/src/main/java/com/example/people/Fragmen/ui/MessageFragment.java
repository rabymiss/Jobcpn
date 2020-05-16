package com.example.people.Fragmen.ui;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.example.people.Api.entity.Resume;
import com.example.people.DashboardViewModel;
import com.example.people.Entity.RegisterEntity;
import com.example.people.Entity.job.ResumeEntity;
import com.example.people.Entity.job.ReturnResume;
import com.example.people.R;
import com.example.people.ResumeViewModel;
import com.example.people.UserViewModel;
import com.example.people.adapter.ChatAdapter;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.service.ChatService;
import com.example.people.service.ICommunication;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.BIND_AUTO_CREATE;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class MessageFragment extends Fragment   {
private ChatAdapter chatAdapter;
private RecyclerView recyclerView;
private Socket socket;
private DataInputStream in;
private ChatAdapter.MyViewHolder myViewHolder;
private ResumeViewModel resumeViewModel;
private  ChatService chatService;
    private LiveData<List<ResumeEntity>> filerRes;
    private   List<ResumeEntity> allmsg=new ArrayList<>();
    private boolean isServiceBinded;
    private ICommunication iCommunication;
    private boolean mIsServiceBind;
private Context context;
private SwipeRefreshLayout refreshLayout;
    private UserViewModel userViewmodel;
    LiveData<List<LoginUser>>liveData;
    private String username;
    private String username1;

    public MessageFragment(){

        setHasOptionsMenu(true);
    }

    private DashboardViewModel dashboardViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) { dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
       return inflater.inflate(R.layout.fragment_dashboard, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

refreshLayout=requireActivity().findViewById(R.id.refresh_message);

userViewmodel=ViewModelProviders.of(this).get(UserViewModel.class);






//       service();
//        init();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
//         broadcast();
adapter();
        adapter1();
    }

    private void adapter() {
        liveData=userViewmodel.getAllUsersLive();
        liveData.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser loginUser:loginUsers){
                    username1 = loginUser.getUserId();
                }
            }
        });


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RegisterEntity registerEntity=new RegisterEntity(username1,null);
                String msg=JSON.toJSONString(registerEntity);
                final RequestBody requestBody3=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),msg);
               Call<Resume>task2= Common.apicommont.findresunme(requestBody3);
               task2.enqueue(new Callback<Resume>() {
                   @Override
                   public void onResponse(Call<Resume> call, Response<Resume> response) {
                       Resume resume=response.body();
                       List<Resume.DataBean>data=new ArrayList<>();
                       data.addAll(resume.getData());

                       for (Resume.DataBean dataBean:data){
                           ResumeEntity resumeEntity1=new ResumeEntity();
                           resumeEntity1.setEmail(dataBean.getEmail());
                        resumeEntity1.setAddressWork(dataBean.getAddresswork());
                        resumeEntity1.setBirthday(dataBean.getBirthday());
                        resumeEntity1.setIfMary(dataBean.getIfmary());
                        resumeEntity1.setPhone(dataBean.getPhone());
                        resumeEntity1.setQwer(dataBean.getQwer());
                        resumeEntity1.setPolitics(dataBean.getPolitics());
                        resumeEntity1.setTeached(dataBean.getTeached());
                        resumeEntity1.setShowbyshelf(dataBean.getShowbyshelf());
                        resumeEntity1.setWorkming(dataBean.getWorkming());
                        resumeEntity1.setYouname(dataBean.getYouname());
                        resumeViewModel.insertRess(resumeEntity1);

                       }
                       refreshLayout.setRefreshing(false);
                   }

                   @Override
                   public void onFailure(Call<Resume> call, Throwable t) {

                   }
               });

                refreshLayout.setRefreshing(false);
            }
        });



    }

    private void adapter1() {
        recyclerView=requireActivity().findViewById(R.id.chat_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        chatAdapter=new ChatAdapter();
        resumeViewModel= ViewModelProviders.of(requireActivity()).get(ResumeViewModel.class);
          filerRes=resumeViewModel.getAllResLive();
           Log.d("all", String.valueOf(filerRes));
        filerRes.observe(requireActivity(), new Observer<List<ResumeEntity>>() {
            @Override
            public void onChanged(List<ResumeEntity> resumeEntities) {

                chatAdapter.setMessage(resumeEntities);
              chatAdapter.notifyDataSetChanged();
            }
        });

recyclerView.setAdapter(chatAdapter);

    }

//    private void broadcast() {
//
//
//
//
//
//        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.intent.action.ACTION_FASONG");//建议把它写一个公共的变量，这里方便阅读就不写了
//        BroadcastReceiver Receive =new BroadcastReceiver() {
//
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String msg=intent.getStringExtra("msg");
//
//                ReturnResume returnResume= JSON.parseObject(msg,ReturnResume.class);
//                        ReturnResume.DataBean dataBean=returnResume.getData();
//                        ResumeEntity resumeEntity1=new ResumeEntity();
//                        resumeEntity1.setEmail(dataBean.getEmail());
//                        resumeEntity1.setAddressWork(dataBean.getAddressWork());
//                        resumeEntity1.setBirthday(dataBean.getBirthday());
//                        resumeEntity1.setIfMary(dataBean.getIfMary());
//                        resumeEntity1.setPhone(dataBean.getPhone());
//                        resumeEntity1.setQwer(dataBean.getQwer());
//                        resumeEntity1.setPolitics(dataBean.getPolitics());
//                        resumeEntity1.setTeached(dataBean.getTeached());
//                        resumeEntity1.setShowbyshelf(dataBean.getShowbyshelf());
//                        resumeEntity1.setWorkming(dataBean.getWorkming());
//                        resumeEntity1.setYouname(dataBean.getYouname());
//                        resumeViewModel.insertRess(resumeEntity1);
//
//
//
//
//
//
//            }
//        };
//        broadcastManager.registerReceiver(Receive, intentFilter);
//
//
//
//
//
//    }




//    private void service() {
//  Intent intent=new Intent(requireActivity(),ChatService.class);
//        mIsServiceBind = requireActivity().bindService(intent,mConnection,BIND_AUTO_CREATE);
//
//
//
//
//}
//private  ServiceConnection mConnection=new ServiceConnection() {
//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
//        Log.d(TAG,"连接");
//           iCommunication = (ICommunication) service;
//
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//        Log.d(TAG,"断开");
//        iCommunication=null;
//    }
//};


//    //分割线
//    private void init() {
//
//
//
//
//    }


}