package com.example.people.ui.login.cpnmsg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.people.Entity.RegisterEntity;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.cpn.Officesend;
import com.example.people.Entity.job.ResumeEntity;
import com.example.people.R;
import com.example.people.ResumeViewModel;
import com.example.people.UserViewModel;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.tableDo.CpnViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffiActivity extends AppCompatActivity {
    private EditText username,time,lianxiren,cpnname,dizhi,emain,telphone;
    private  TextView ofshow;
private Button  confirm;
    private LiveData<List<ResumeEntity>> cpnNameList;
    private ResumeViewModel resumeViewModel;
    private final String TAG1="你好！你已通过";
    private final String TAG2="面试前的初步审核，现正式通知你到公司参加面试，请带好个人证件。面试时间：";
    private final String TAG3="";
    List<CpnMessage> list;
    CpnViewModel cpnViewModel;
    List<String>liststr=new ArrayList<>();
    private String uuid;
    private UserViewModel userViewModel;
    LiveData<List<LoginUser>>listLiveDatauser;
    private String userid;
    private String img;
    private String jobname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offi);
        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel.class);
        cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
        userViewModel=ViewModelProviders.of(this).get(UserViewModel.class);
        liststr.clear();
        initview();

    }

    private void initview() {
        confirm=findViewById(R.id.button_res_confirm2);
//        confirm.setEnabled(false);
        username=findViewById(R.id.of_text_rencai);
        time=findViewById(R.id.of_ed_time);
        lianxiren=findViewById(R.id.of_ed_phone);//联系人
        cpnname=findViewById(R.id.of_ed_cpnname);
        dizhi=findViewById(R.id.of_ed_address);
        emain=findViewById(R.id.of_ed_e_mail);
        ofshow=findViewById(R.id.of_text_showof_111);
        telphone=findViewById(R.id.of_ed_telphone);
        Intent intent=getIntent();
        uuid = intent.getStringExtra("uuid");
        userid = intent.getStringExtra("userid");
        jobname = intent.getStringExtra("jobname");
        cpnNameList=resumeViewModel.findMsgsWithPattern(uuid);
        cpnNameList.observe(this, new Observer<List<ResumeEntity>>() {
    @Override
    public void onChanged(List<ResumeEntity> resumeEntities) {
        for (ResumeEntity gt:resumeEntities){
     username.setText(gt.getYouname().toString());


        }
    }
});


        list=cpnViewModel.list();
        for (CpnMessage gf:list){
            cpnname.setText(gf.getNickname());
            dizhi.setText(gf.getAddressp());
            emain.setText(gf.getEmail());

            img = gf.getIcon();

        }
        listLiveDatauser=userViewModel.getAllUsersLive();
        listLiveDatauser.observe(this, new Observer<List<LoginUser>>() {

            private String cpmid;

            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser gt:loginUsers){
                    liststr.add(gt.getUserId()) ;
                }
            }
        });

//        匹配



    }
    public void queren(View view){







//        confirm.setEnabled(true);


    }
    public void sendof(View view){
        ofshow.append("                       面试通知"+"\n"
                +username.getText().toString()+TAG1+cpnname.getText().toString()+TAG2+time.getText().toString()+"\n"+
                "地址："+dizhi.getText().toString()+"\n"+
                "联系人："+lianxiren.getText().toString()+"  联系电话："+telphone.getText().toString());


        Officesend officesend=new Officesend();
//        officesend.setCpnid();
        officesend.setOfuuid(uuid);
        officesend.setEmail(emain.getText().toString());
        officesend.setOftime(time.getText().toString());
        officesend.setCpnid(liststr.get(0));
        officesend.setLianxiren(lianxiren.getText().toString());
        officesend.setOfcontent(ofshow.getText().toString());
        officesend.setLxphone(telphone.getText().toString());
        officesend.setCpnname(cpnname.getText().toString());
        officesend.setRencainame(username.getText().toString());
        officesend.setDozhi(dizhi.getText().toString());
        officesend.setT1(userid);
        officesend.setT2(img);
        officesend.setJobname(jobname);
    String gt= JSON.toJSONString(officesend);

        final RequestBody requestBody3=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gt);
        Call<RegisterEntity>task= Common.apicommont.sendoffi(requestBody3);
        task.enqueue(new Callback<RegisterEntity>() {
            @Override
            public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {
                OffiActivity.this.finish();
                Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RegisterEntity> call, Throwable t) {

            }
        });
    }
}
