package com.example.people.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.job.JobMessageAll;
import com.example.people.R;
import com.example.people.tableDo.MsgViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CpnMyMessageActivity extends AppCompatActivity {
    private static final String TSG ="CpnMyMessageActivity" ;
    private Button buttonAddAllMessage;
private EditText jobname,condition1,condition2,jobpay,cpnmae;
private EditText address,workXondition,editworkshow;
private ImageView icin;
private List<String>list=new ArrayList<>();
    private String uuid;
    private String uuid1;
    private String img;
    MsgViewModel msgViewModel;
    private String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        msgViewModel= ViewModelProviders.of(this).get(MsgViewModel.class);
        initView();
        doView();

findcpn();


    }

    private void findcpn() {

        Intent intent=getIntent();
        p = intent.getStringExtra("p");
        uuid1 = intent.getStringExtra("uuid");
        String cpn=intent.getStringExtra("Cpn");
        String job=intent.getStringExtra("jobname");
        jobpay.setText(intent.getStringExtra("jobpay"));
        condition1.setText(intent.getStringExtra("con1"));
        condition2.setText(intent.getStringExtra("con2"));
        img = intent.getStringExtra("img");
        System.out.println("接收到的图片------------"+img);
        Picasso.get().load(ApiRetrofit.URL+img).into(icin);
        cpnmae.setText(cpn);
        jobname.setText(job);
    }


    private void initView() {

        jobname=findViewById(R.id.edit_add_job_name);
        condition1=findViewById(R.id.editText_condition_one);
        condition2=findViewById(R.id.editText_condition_two);
        jobpay=findViewById(R.id.edit_add_job_pay);
        editworkshow=findViewById(R.id.edit_work_show);
        cpnmae=findViewById(R.id.editText_cpn_name);
        address=findViewById(R.id.edit_cpn_address);
        icin=findViewById(R.id.imageView_cpnmesage_icom);

        workXondition=findViewById(R.id.editText_condition_three);
        //.button

        buttonAddAllMessage=findViewById(R.id.button_add_all_confirm);

    }
    private void doView() {

        buttonAddAllMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String jobname1=jobname.getText().toString().trim();
                String condition11=condition1.getText().toString().trim();
                String condition22=condition2.getText().toString().trim();
                String jobpay1=jobpay.getText().toString().trim();
                String workshow1=editworkshow.getText().toString().trim();
                String cpnname=cpnmae.getText().toString().trim();
                String address1=address.getText().toString().trim();
                 String workYoqiu=workXondition.getText().toString().trim();

                System.out.println("上传的网络图片------------"+img);
           Api api=ApiRetrofit.getInstance().getService(Api.class);
                JobMessageAll jobMessageAll=new JobMessageAll(jobname1
                ,null
                        ,uuid1
                        ,p
                        ,null
                        ,null
                        ,jobpay1
                        ,condition11
                        ,condition22
                        , workYoqiu
                        ,null
                        ,workshow1
                , null
                ,img,cpnname,address1);

                Gson gson =new Gson();
                String jsontr=gson.toJson(jobMessageAll);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsontr);
                Call<JobMessageAll> task=api.addMsgsAll(requestBody);
                task.enqueue(new Callback<JobMessageAll>() {
                    @Override
                    public void onResponse(Call<JobMessageAll> call, Response<JobMessageAll> response) {
                        int code=response.code();

                        if (code== HttpURLConnection.HTTP_OK){
                             msgViewModel.insertMsgs(jobMessageAll);

                        }

                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CpnMyMessageActivity.this, ButtomActivity.class);
                        startActivity(intent);
                     CpnMyMessageActivity.this.finish();
                        //返回 主界面

                    }

                    @Override
                    public void onFailure(Call<JobMessageAll> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_LONG).show();
                    }
                });




            }
        });


    }
}
