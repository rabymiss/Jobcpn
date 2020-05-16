package com.example.people.ui.login.cpnmsg;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.RegisterEntity;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.cpn.ImguEntity;
import com.example.people.Fragmen.ui.MyFragment;
import com.example.people.R;
import com.example.people.UserViewModel;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.img.ImagEntity;
import com.example.people.img.PhotoActivity;
import com.example.people.tableDo.CpnViewModel;
import com.example.people.tableDo.ImgViewmodel;
import com.example.people.tool.Validator;
import com.example.people.ui.login.ButtomActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CpnMsgActivity extends AppCompatActivity {
    private static final String TAG ="PersonMessageActivity" ;
    private EditText edName, edBurth, edemail, edphone, edaddress, edwork;
    private EditText edshowyouself;
    private ImageView imageViewIcon;
    private Button confirm;
    private ImgViewmodel imgViewmodel;
    private UserViewModel userViewmodel;
    private CpnViewModel cpnViewModel;
    List<String>listall=new ArrayList<>();
    LiveData<List<LoginUser>>liveData;
    CpnMessage cpnMessage=new CpnMessage();
    private static final String TEMP_INFO = "temp_info";
    private SharedPreferences sp;
    private String url;
    Common common;
    List<String>list=new ArrayList<>();
    List<String>listnet=new ArrayList<>();
    List<String>listcpnimg=new ArrayList<>();
    List<CpnMessage>cpnMessageList;
LiveData<List<ImguEntity>>liveDataimg;
    private String urlint;

    List<CpnMessage>listcpn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpn_msg);
        imgViewmodel=ViewModelProviders.of(this).get(ImgViewmodel.class);
        userViewmodel= ViewModelProviders.of(this).get(UserViewModel.class);
        cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
        inoitView();
        initDo();
        livedata();
        list.clear();
        listcpnimg.clear();
        listnet.clear();
        getintent();
        findself();
        adaptercpn();
    }

    private void adaptercpn() {



    }

    private void findself() {

        cpnMessageList=cpnViewModel.list();
if (cpnMessageList.size()!=0){
        for (CpnMessage cpnMessage:cpnMessageList){

            edName.setText(cpnMessage.getNickname());

            SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.putString("usn",cpnMessage.getWorkin());

            editor.apply();
            edemail.setText(cpnMessage.getEmail());
            edphone.setText(cpnMessage.getPhonenumber());

            edaddress.setText(cpnMessage.getAddressp());
            edshowyouself.setText(cpnMessage.getShowyou());
            if (cpnMessage.getIcon()!=null){
                listcpnimg.add(cpnMessage.getIcon());
            }
            Picasso.get().load(ApiRetrofit.URL+cpnMessage.getIcon()).into(imageViewIcon);
        }}
    }

    private void getintent() {

liveDataimg=imgViewmodel.list();
liveDataimg.observe(this, new Observer<List<ImguEntity>>() {
    @Override
    public void onChanged(List<ImguEntity> imguEntities) {
        for (ImguEntity imguEntity:imguEntities){
            if (imguEntity.getUsername()!=null){
                SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.putString("urlint",imguEntity.getUsername());
                if (imguEntity.getUsername()!=null){
                    listnet.add(imguEntity.getUsername());
                }
                editor.apply();

    Picasso.get().load(ApiRetrofit.URL+imguEntity.getUsername()).into(imageViewIcon);


            }

        }
    }
});


    }

    //编辑头像
    public void onclickedimg(View view){
        Intent intent=new Intent(CpnMsgActivity.this, PhotoActivity.class);

        startActivity(intent);
CpnMsgActivity.this.finish();

    }
    private void livedata() {









        liveData=userViewmodel.getAllUsersLive();
        liveData.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser loginUser:loginUsers){

                    SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
                    editor.clear();
                    list.add(loginUser.getUserId());

                    editor.putString("username",loginUser.getUserId());
                    editor.apply();

                }
            }
        });
    }

    private void inoitView() {
        edName = findViewById(R.id.edit_res_name);
        edaddress = findViewById(R.id.edit_res_burth);

        edemail = findViewById(R.id.edit_res_e_mail);
        edphone = findViewById(R.id.edit_res_telphone);


        edshowyouself = findViewById(R.id.edit_show_you);


        confirm = findViewById(R.id.button_res_confirm);
        imageViewIcon=findViewById(R.id.imageView_pco);




    }

    private void initDo() {

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
sp=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE);
String uu=sp.getString("urlint","");
                if (url==null&&uu==null){
                    Toast.makeText(getApplicationContext(), "请上传头像", Toast.LENGTH_SHORT).show();

                }else {
                SharedPreferences.Editor editor = getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.putString("edName", edName.getText().toString());
if (Validator.isEMAIN(edemail.getText().toString())){
//    editor.putString("edemail", edemail.getText().toString());

}else {
    edemail.setError("格式不正确");
    edemail.requestFocus();
    return;
}

                    if (Validator.isMobileNumber(edphone.getText().toString())){
//                        editor.putString("edphone", edphone.getText().toString());

                    }else {
                        edphone.setError("号码格式不正确");
                        edphone.requestFocus();
                        return;
                    }



                cpnMessage.setAddressp(edaddress.getText().toString());
//
                    if (listnet.size()!=0){
                        cpnMessage.setIcon(listnet.get(0));
                    }else {
                        cpnMessage.setIcon(listcpnimg.get(0));
                    }

                cpnMessage.setEmail( edemail.getText().toString());
                cpnMessage.setNickname(edName.getText().toString());
                cpnMessage.setPhonenumber(edphone.getText().toString());
                cpnMessage.setShowyou(edshowyouself.getText().toString());
                cpnMessage.setWorkin(list.get(0));
                cpnMessage.setUsername(list.get(0));


             //广播
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.ACTION_FASONG");//用隐式意图来启动广播
                    intent.putExtra("username", cpnMessage.getNickname());
                    intent.putExtra("url", cpnMessage.getIcon());
                    intent.putExtra("phone",cpnMessage.getPhonenumber());
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);





                    cpnViewModel.deleteall();

                    cpnViewModel.insert(cpnMessage);
                Gson gson = new Gson();
                String jsonstr = gson.toJson(cpnMessage);
                final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr);
                Call<CpnMessage>task=Common.apicommont.addcpn(requestBody);
                task.enqueue(new Callback<CpnMessage>() {
                    @Override
                    public void onResponse(Call<CpnMessage> call, Response<CpnMessage> response) {

                      System.out.println("插入-------------"+cpnViewModel.list());

                        Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();

                            CpnMsgActivity.this.finish();
                    }

                    @Override
                    public void onFailure(Call<CpnMessage> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });






            }}
        });
    }
}

