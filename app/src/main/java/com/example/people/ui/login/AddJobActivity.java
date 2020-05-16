package com.example.people.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.Uuid;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.cpn.ImguEntity;
import com.example.people.Entity.job.JobMessage;
import com.example.people.JobViewModel;
import com.example.people.R;
import com.example.people.UserViewModel;
import com.example.people.data.model.LoginUser;
import com.example.people.tableDo.CpnViewModel;
import com.example.people.tableDo.ImgViewmodel;
import com.example.people.tableDo.UuidViewMoldel;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddJobActivity extends AppCompatActivity {
    private UuidViewMoldel uuidViewMoldel;
    private List<Uuid>uuidList;
    private JobViewModel jobViewModel;
    private Button buttonConfirm;
    private EditText editTextCpn,editTextJob,editTextConditionOne,editTextConditionTwo,editTextJobPay;
    private UserViewModel userViewModel;
    LiveData<List<LoginUser>>listLiveData;
    private ImgViewmodel imgViewmodel;
    LiveData<List<ImguEntity>>listLiveimgData;
    private static final String TEMP_INFO = "temp_info";
    private SharedPreferences sp;
 private    List<CpnMessage>listcpn;
  private   CpnViewModel cpnViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        jobViewModel= ViewModelProviders.of(this).get(JobViewModel.class);
        imgViewmodel=ViewModelProviders.of(this).get(ImgViewmodel.class);
        buttonConfirm=findViewById(R.id.button_add_confirm);
        editTextCpn=findViewById(R.id.editText_cpn_add);
        editTextJob=findViewById(R.id.editText_job);
        editTextConditionOne=findViewById(R.id.editText_condition_one);
        editTextConditionTwo=findViewById(R.id.editText_condition_two);
        editTextJobPay=findViewById(R.id.editText_job_pay);
cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
finduser();

        //buttonConfirm.setEnabled(false);
        //自动调出键盘


        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE);
                String  username=sp.getString("username","");

           String    img = sp.getString("img","");

                String uuid= UUID.randomUUID()+editTextCpn.getText().toString();
                String cpn = editTextCpn.getText().toString().trim();
                String job = editTextJob.getText().toString().trim();
                final String condition1 = editTextConditionOne.getText().toString().trim();
                String condition2 = editTextConditionTwo.getText().toString().trim();
                String pay = editTextJobPay.getText().toString().trim();

                Api api = ApiRetrofit.getInstance().getService(Api.class);

                JobMessage jobMessage=new JobMessage(condition1,condition2,job,pay,cpn,uuid,username, img);
                Gson gson =new Gson();
                String jsontr=gson.toJson(jobMessage);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsontr);
                Call<JobMessage>task=api.postJobRegister(requestBody);
                task.enqueue(new Callback<JobMessage>() {
                    @Override
                    public void onResponse(Call<JobMessage> call, Response<JobMessage> response) {
                        int code=response.code();


                        if (code== HttpURLConnection.HTTP_OK){

                            Intent intent =new Intent(AddJobActivity.this,CpnMyMessageActivity.class);
                            intent.putExtra("jobname",job);
                            intent.putExtra("Cpn",cpn);
                            intent.putExtra("jobpay",pay);
                            intent.putExtra("con1",condition1);
                            intent.putExtra("con2",condition2);
                            intent.putExtra("uuid",uuid);
                            intent.putExtra("p",username);
                            intent.putExtra("img", img);
                            startActivity(intent);
                            JobMessage jobMessage1=new JobMessage(condition1,condition2,job,pay,cpn,uuid,username,img);

                            jobViewModel.insertJobs(jobMessage1);

                        }
                        //返回 主界面

                }

                    @Override
                    public void onFailure(Call<JobMessage> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_LONG).show();
                    }
                });

//..................................

            }
        });





    }

    private void finduser() {

        listcpn=cpnViewModel.list();
        for (CpnMessage gt:listcpn){

            SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
            editor.putString("img",gt.getIcon());
            editTextCpn.setText(gt.getNickname());
            editor.putString("username",gt.getPhonenumber());

System.out.println("---listcpn=========="+listcpn);

editor.apply();

        }


//   listLiveimgData=     imgViewmodel.list();
//   listLiveimgData.observe(this, new Observer<List<ImguEntity>>() {
//       @Override
//       public void onChanged(List<ImguEntity> imguEntities) {
//           for (ImguEntity imguEntity:imguEntities){
//
//               SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
//               editor.clear();
//               editor.putString("img",imguEntity.getUsername());
//
//               editor.apply();
//
//           }
//       }
//   });
//        userViewModel=ViewModelProviders.of(this).get(UserViewModel.class);
//        listLiveData=userViewModel.getAllUsersLive();
//        listLiveData.observe(this, new Observer<List<LoginUser>>() {
//            @Override
//            public void onChanged(List<LoginUser> loginUsers) {
//                for (LoginUser loginUser:loginUsers){
//
//                    SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
//                    editor.putString("username",loginUser.getUserId());
//                    editor.apply();
//
//                }
//            }
//        });
//
//
    }
}
