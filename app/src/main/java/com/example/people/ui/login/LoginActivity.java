package com.example.people.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.job.JobResultEntity;
import com.example.people.Entity.RegisterEntity;
import com.example.people.JobViewModel;
import com.example.people.R;

import com.example.people.UserViewModel;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.tableDo.CpnViewModel;
import com.example.people.tableDo.UuidViewMoldel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG ="LoginActivity" ;
    final String REMEMBER_PWD_PREF = "rememberPwd";
    final String ACCOUNT_PREF = "account";
    final String PASSWORD_PREF = "password";
private JobViewModel jobViewModel;
private UserViewModel userViewModel;
    private Button buttonConfirm,buttonRegister;
    private TextView textViewAccount,textViewPassword,forgetpsw;
    private List<JobResultEntity.DataBean> data=new ArrayList<>();
    private UuidViewMoldel uuidViewMoldel;
    CpnViewModel cpnViewModel;


    LiveData<List<LoginUser>>loginUserLiveData;
    List<CpnMessage>listcpn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonConfirm=findViewById(R.id.button_login);
        buttonRegister=findViewById(R.id.button_register);
        textViewAccount=findViewById(R.id.text_account_login);
        textViewPassword=findViewById(R.id.text_password_login);
        forgetpsw=findViewById(R.id.text_login_forget);
        doview();
        userViewModel=ViewModelProviders.of(this).get(UserViewModel.class);
        jobViewModel=ViewModelProviders.of(this).get(JobViewModel.class);
        cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
        //记住密码
        final SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = preference.getBoolean(REMEMBER_PWD_PREF, false);
        final CheckBox rememberPwd = (CheckBox) findViewById(R.id.checkBox_login);














        if (isRemember) {//设置【账号】与【密码】到文本框，并勾选【记住密码】
            textViewAccount.setText(preference.getString(ACCOUNT_PREF, ""));
            textViewPassword.setText( preference.getString(PASSWORD_PREF, "")  );
            rememberPwd.setChecked(true);
        }



        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RestarPswActivity.class);
                startActivity(intent);
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             final    String account=textViewAccount.getText().toString().trim();
             final    String password=textViewPassword.getText().toString().trim();
                LoginUser loginUser=new LoginUser(account,password);
//启动socket
                Call<RegisterEntity> task4=Common.apicommont.socketstart();
                task4.enqueue(new Callback<RegisterEntity>() {
                    @Override
                    public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {

                    }

                    @Override
                    public void onFailure(Call<RegisterEntity> call, Throwable t) {

                    }
                });



                cpnViewModel.deleteall();

            //寻找公司
                CpnMessage c=new CpnMessage();
                c.setWorkin(account);
                Gson gson2=new Gson();
                String json=gson2.toJson(c);
                final RequestBody requestBody3=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
Call<List<CpnMessage>>task1=Common.apicommont.findcpn(requestBody3);
task1.enqueue(new Callback<List<CpnMessage>>() {
    @Override
    public void onResponse(Call<List<CpnMessage>> call, Response<List<CpnMessage>> response) {
        listcpn=response.body();


            for (CpnMessage cpnMessage:listcpn){
                cpnViewModel.insert(cpnMessage);



        }

    }

    @Override
    public void onFailure(Call<List<CpnMessage>> call, Throwable t) {

    }
});











//登陆操作
//            Api api= ApiRetrofit.getInstance().getService(Api.class);
           final      RegisterEntity registerEntity=new RegisterEntity(account,password);

                Gson gson=new Gson();
                String jsonstr=gson.toJson(registerEntity);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonstr);

             Call<RegisterEntity>task= Common.apicommont.LoginResult(requestBody);
             task.enqueue(new Callback<RegisterEntity>() {
                 @Override
                 public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {

                     int a=response.code();
                     if (response.body().getUsername().equals("1")){
                     Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                     userViewModel.deleteAllUsers();

                         userViewModel.insertUsers(loginUser);


                         SharedPreferences.Editor editor = preference.edit();
                         if (rememberPwd.isChecked()) {//记住账号与密码
                             editor.putBoolean(REMEMBER_PWD_PREF, true);
                             editor.putString(ACCOUNT_PREF, account);
                             editor.putString(PASSWORD_PREF, password);
                         } else {//清空数据
                             editor.clear();
                         }
                         editor.apply();

                         Intent intent=new Intent(LoginActivity.this, ButtomActivity.class);
                         startActivity(intent);

                     }else if (response.body().getUsername().equals("0")){
                         Toast.makeText(getApplicationContext(),"账号或密码不正确",Toast.LENGTH_SHORT).show();

                     }else {
                         Toast.makeText(getApplicationContext(),"后台异常请联系管理员",Toast.LENGTH_LONG).show();
                     }
                 }
                 @Override
                 public void onFailure(Call<RegisterEntity> call, Throwable t) {
                     Log.d(TAG,"失败"+t.toString());
                     Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
                 }
             });

//..................................................................................................
            }
        });
    }

    private void doview() {
        forgetpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RestarPswActivity.class);
                startActivity(intent);
            }
        });

    }
}
