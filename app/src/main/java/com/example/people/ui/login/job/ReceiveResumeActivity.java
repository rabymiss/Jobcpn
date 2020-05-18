package com.example.people.ui.login.job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.people.Entity.job.ResumeEntity;
import com.example.people.R;
import com.example.people.ResumeViewModel;
import com.example.people.ui.login.cpnmsg.OffiActivity;

import java.util.List;

public class ReceiveResumeActivity extends AppCompatActivity {
    private static final String TAG = "ReceiveResumeActivity";
    private TextView edName, edBurth, edpolitics, edemail, edphone, edaddress, edmary;
    private TextView edqwer, deteached, edworkming, edshowyouself;
    private ResumeViewModel resumeViewModel;
private Button button_res_confirm;
    private  LiveData<List<ResumeEntity> >cpnNameList;
    private String people;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_resume);
        resumeViewModel=ViewModelProviders.of(this).get(ResumeViewModel.class);
        //从adp传来的参数


        initPeople();
        sendof();

    }

    private void sendof(){
        button_res_confirm=findViewById(R.id.button_res_confirmof);
        button_res_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReceiveResumeActivity.this, OffiActivity.class);
                intent.putExtra("uuid",people);
                intent.putExtra("userid",userid);

                intent.putExtra("jobname",edworkming.getText().toString());
                startActivity(intent);
            }
        });

    }

    private void initPeople() {
////找控件............................................
        edName=findViewById(R.id.editText_res_name1);
        edBurth = findViewById(R.id.editText_res_burth1);
        edpolitics=findViewById(R.id.editText_res_politics1);
        edemail=findViewById(R.id.editText_res_e_mail1);
        edphone=findViewById(R.id.editText_res_telphone1);

        edaddress=findViewById(R.id.editText_res_address1);
        edmary  =findViewById(R.id.editText_res_if_mary1);
        edqwer=findViewById(R.id.editText_res_qwer1);
        deteached=findViewById(R.id.editText_res_teached1);
        edworkming=findViewById(R.id.editText_find_work_dear1);

        edshowyouself=findViewById(R.id.editText_show_youself1);
        Intent intent=getIntent();
        people = intent.getStringExtra("uuid");
        userid = intent.getStringExtra("phone");

        Toast.makeText(getApplication(), people,Toast.LENGTH_SHORT).show();
      cpnNameList=resumeViewModel.findMsgsWithPattern(people);

      cpnNameList.observe(this, new Observer<List<ResumeEntity>>() {
          @Override
          public void onChanged(List<ResumeEntity> resumeEntities) {


              for (ResumeEntity resumeEntity1:resumeEntities){

          edName.setText(resumeEntity1.getYouname());
            edBurth.setText(resumeEntity1.getBirthday());
            edpolitics.setText(resumeEntity1.getPolitics());
            edemail.setText(resumeEntity1.getEmail());
            edphone.setText(resumeEntity1.getPhone());

                    edaddress.setText(resumeEntity1.getAddressWork());
                    edmary.setText(resumeEntity1.getIfMary());
                    edqwer.setText(resumeEntity1.getQwer());
                    deteached.setText(resumeEntity1.getTeached());
                    edworkming.setText(resumeEntity1.getWorkming());

                    edshowyouself.setText(resumeEntity1.getShowbyshelf());

        }


          }
      });


    }
}