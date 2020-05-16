package com.example.people.ui.login;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.RegisterEntity;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.R;
import com.example.people.UserViewModel;
import com.example.people.data.model.LoginUser;
import com.example.people.tableDo.CpnViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PasswordActivity extends AppCompatActivity {
    private static final String TAG ="PasswordActivity" ;
    private EditText newpsw,oldpsw,confirmpsw;
    private TextView textViewusername;
    private Button confim;



    private LiveData<List<RegisterEntity>> findmsg;
    private static final String TEMP_INFO = "temp_info";
    private SharedPreferences sp;
    private CpnViewModel cpnViewModel;
   private List<CpnMessage> list;
    List<String>listall=new ArrayList<>();

private UserViewModel userViewModel;

LiveData<List<LoginUser>>listLiveDatalog;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        cpnViewModel= ViewModelProviders.of(this).get(CpnViewModel.class);
        userViewModel=ViewModelProviders.of(this).get(UserViewModel.class);
        oldpsw=findViewById(R.id.edpsw_oldpsw);
        newpsw=findViewById(R.id.text_new_psw);
        confirmpsw=findViewById(R.id.text_psw_confirm_psw);
        confim=findViewById(R.id.button7);
        textViewusername=findViewById(R.id.text_psw_username);

        initview();
        confirmpsw();
        findusername();
    }

    private void findusername() {



        listLiveDatalog=userViewModel.getAllUsersLive();
        listLiveDatalog.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser gt:loginUsers){
                    SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
                    editor.clear();
                    editor.putString("username",gt.getUserId());
                    editor.apply();
                    textViewusername.setText(gt.getUserId());

                }
            }
        });
//        list=cpnViewModel.list();
//
//
//
//                for (CpnMessage gt:list){
//
//                    SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
//                    editor.putString("username",gt.getUsername());
//                    editor.apply();
//                    textViewusername.setText(gt.getUsername());
//                }





    }

    private void initview() {

    }

    private void confirmpsw(){

        confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String old=oldpsw.getText().toString().trim();
                final String newpw=newpsw.getText().toString().trim();
                final String conf=confirmpsw.getText().toString().trim();
                Api api= ApiRetrofit.getInstance().getService(Api.class);
                sp=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE);
                final RegisterEntity registerEntity = new RegisterEntity(sp.getString("username",""),old);





                Gson gson = new Gson();
                String jsonstr = gson.toJson(registerEntity);
                final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr);

                Call<RegisterEntity> task = api.LoginResult(requestBody);
                task.enqueue(new Callback<RegisterEntity>() {
                    @Override
                    public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {


                        if (response.body().getUsername().equals("1")) {

                            if (newpw.equals(conf)){
                                final RegisterEntity registerEntity1= new RegisterEntity(sp.getString("username",""),conf);

                                Api api1= ApiRetrofit.getInstance().getService(Api.class);



                                Gson gson = new Gson();
                                String jsonstr1 = gson.toJson(registerEntity1);
                                final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr1);

                                Call<RegisterEntity> task = api1.updateuserr(requestBody);
                                task.enqueue(new Callback<RegisterEntity>() {
                                    @Override
                                    public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<RegisterEntity> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(PasswordActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });



                            }else {

                                Toast.makeText(getApplicationContext(), "新密码不一致", Toast.LENGTH_LONG).show();


                            }




















                        } else if (response.body().getUsername().equals("0")) {
                            Toast.makeText(getApplicationContext(), "旧密码不正确", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "后台异常请联系管理员", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterEntity> call, Throwable t) {
                        Log.d(TAG, "失败" + t.toString());
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });








            }
        });
    }
}

