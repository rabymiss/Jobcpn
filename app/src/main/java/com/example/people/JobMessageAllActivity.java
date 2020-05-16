package com.example.people;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.people.Api.Api;
import com.example.people.Entity.job.JobMessageAll;
import com.example.people.Entity.job.JobResultEntity;
import com.example.people.Entity.job.ReturnJobMessageAll;
import com.example.people.tableDo.MsgViewModel;
import com.example.people.ui.login.RusumeActivity;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobMessageAllActivity extends AppCompatActivity {
private JobViewModel jobViewModel;
private TextView textViewjobname,condition1,condition2,conditon3,jobpay,workshow,cpnname,cpnaddress;
    private Button buttonAddAMsg,buttonchact;
    private final   String   TAG="加载Msgs";

   private  LiveData<List<JobMessageAll> >cpnNameList;
    private MsgViewModel msgViewModel;

//发送简历
private Button button,button2;

    private DataOutputStream out;
    private Socket socket;


    private List<ReturnJobMessageAll.DataBean> data=new ArrayList<>();
    private List<JobResultEntity.DataBean> data2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_job_messagek);

        //发送部件


        jobViewModel=ViewModelProviders.of(this).get(JobViewModel.class);
      msgViewModel=ViewModelProviders.of(this).get(MsgViewModel.class);

      findCpn();





        buttonchact=findViewById(R.id.button_wchact_add_message);
        buttonchact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //...........................下载Amsgs


                            Retrofit retrofit  =new Retrofit.Builder()
                    .baseUrl("http:/172.20.10.3:8080")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Api api  =  retrofit.create(Api.class);
            Call<ReturnJobMessageAll> task=api.findMsgsAll();
            task.enqueue(new Callback<ReturnJobMessageAll>() {
                @Override
                public void onResponse(Call<ReturnJobMessageAll> call, Response<ReturnJobMessageAll> response) {
                 Log.d(TAG,"Json-->"+response.code());


                    if (response.code()== HttpsURLConnection.HTTP_OK){
                        Log.d(TAG,"成功"+response.body());

                        ReturnJobMessageAll returnJobMessageAll=response.body();
                   data.addAll(returnJobMessageAll.getData());


                    for (  ReturnJobMessageAll.DataBean dataBean:data   ){


                        JobMessageAll jobMessageAll=new JobMessageAll(dataBean.getJobName()
                        ,dataBean.getCpnAddress()
                        ,dataBean.getGood1()
                        ,dataBean.getGood2()
                        ,dataBean.getGood3()
                        ,dataBean.getGood4()
                        ,dataBean.getJobPay()
                        ,dataBean.getConditionOne()
                        ,dataBean.getConditionTwo()
                        ,dataBean.getCondition3()
                        ,dataBean.getWorkContent()
                        ,dataBean.getWorkContentShow()
                        ,dataBean.getWorkAddress()
                        ,dataBean.getCpnImage()
                        ,dataBean.getCpnName1()
                        ,dataBean.getDizhi());
//
                  // Log.d(TAG,"公司名字------------------------------"+dataBean.getCpnName());
                                   msgViewModel.insertMsgs(jobMessageAll);
                        Log.d(TAG,"公司名字------------------------------"+jobMessageAll.getCpnName1());
                    }

                        Log.d(TAG,"data-------------"+data);

                    }
                }

                @Override
                public void onFailure(Call<ReturnJobMessageAll> call, Throwable t) {
                    Log.d(TAG,"失败-->"+t.toString());
                }
            });




            }
        });
                   //投递简历
        buttonAddAMsg=findViewById(R.id.button_send_office);
//
        buttonAddAMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            socket = new Socket("172.20.10.3", 8081);
//                            out=new DataOutputStream(socket.getOutputStream());
//                           // out.writeUTF(editText.getText().toString());
//                            //  in=new DataInputStream(socket.getInputStream());
//                            Log.d("输出",".................."+out);
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }).start();





                Intent intent = new Intent(JobMessageAllActivity.this, RusumeActivity.class);
                startActivity(intent);

















                //.....................................................................................
//                // 将工作信息加入表中
//
//
//                            Retrofit retrofit  =new Retrofit.Builder()
//                    .baseUrl("http:/192.168.0.100:8080")
//                    .addConverterFactory(GsonConverterFactory.create()).build();
//            Api api  =  retrofit.create(Api.class);
//            Call<JobResultEntity> task=api.getJson();
//            task.enqueue(new Callback<JobResultEntity>() {
//                @Override
//                public void onResponse(Call<JobResultEntity> call, Response<JobResultEntity> response) {
//                 Log.d(TAG,"Json-->"+response.code());
//
//
//                    if (response.code()== HttpsURLConnection.HTTP_OK){
//                        Log.d(TAG,"成功"+response.body());
//
//                        JobResultEntity jobResultEntity=response.body();
//                      data2.addAll(jobResultEntity.getData());
//
//
//                    for (  JobResultEntity.DataBean dataBean:data2  ){
//
//                                     JobMessage jobMessage=new JobMessage(dataBean.getJobConditionOne()
//                                             ,dataBean.getJobConditionTwo()
//                                             ,dataBean.getJobName()
//                                             ,dataBean.getJobPay()
//                                             ,dataBean.getCpnName());
//                        Log.d(TAG,"data------------------------------"+jobMessage.getCpnName());
//                                     jobViewModel.insertJobs(jobMessage);
//                    }
//
//                        Log.d(TAG,"data------------------------------"+data);
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JobResultEntity> call, Throwable t) {
//                    Log.d(TAG,"失败-->"+t.toString());
//                }
//            });
                //..............................................................................
          }
       });
    }
//匹配公司
    private void findCpn() {

        textViewjobname=findViewById(R.id.show_jobname);
        condition1=findViewById(R.id.show_content1);
        condition2=findViewById(R.id.show_content2);
      jobpay  =findViewById(R.id.show_jobpay);
        workshow=findViewById(R.id.show_work_content_show);
        cpnname=findViewById(R.id.show_cpnname);
        cpnaddress=findViewById(R.id.show_address_cpn);
        conditon3=findViewById(R.id.show_content3);

        //从adp传来的参数
        Intent intent=getIntent();
        final String cpn=intent.getStringExtra("Cpn");
 //Toast.makeText(this,cpn,Toast.LENGTH_SHORT).show();
        cpnNameList=msgViewModel.findMsgsWithPattern(cpn);
        Log.d(TAG,"----------搜索------------------"+cpnNameList);
      ;//解析获得的搜索数据
        cpnNameList.observe(this, new Observer<List<JobMessageAll>>() {
            @Override
            public void onChanged(List<JobMessageAll> jobMessageAlls) {
                Log.d(TAG,"----------555555555------------------"+jobMessageAlls);
                      for (JobMessageAll jobMessageAll:jobMessageAlls){

                          JobMessageAll jobMessageAll1=new JobMessageAll(jobMessageAll.getJobName()
                                  ,jobMessageAll.getCpnAddress()
                                  ,jobMessageAll.getGood1()
                                  ,jobMessageAll.getGood2()
                                  ,jobMessageAll.getGood3()
                                  ,jobMessageAll.getGood4()
                                  ,jobMessageAll.getJobPay()
                                  ,jobMessageAll.getConditionOne()
                                  ,jobMessageAll.getConditionTwo()
                                  ,jobMessageAll.getCondition3()
                                  ,jobMessageAll.getWorkContent()
                                  ,jobMessageAll.getWorkContentShow()
                                  ,jobMessageAll.getWorkAddress()
                                  ,jobMessageAll.getCpnImage()
                                  ,jobMessageAll.getCpnName1()
                                  ,jobMessageAll.getDizhi());

                          textViewjobname.setText(jobMessageAll1.getJobName());
                          condition2.setText(jobMessageAll1.getConditionTwo());
                          jobpay.setText(jobMessageAll1.getJobPay());
                          workshow.setText(jobMessageAll1.getWorkContentShow());
                          cpnname.setText(jobMessageAll1.getCpnName1());
                          cpnaddress.setText(jobMessageAll1.getCpnAddress());
                          conditon3.setText(jobMessageAll1.getCondition3());


                      }

            }
        });

    }
}
