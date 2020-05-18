package com.example.people.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.people.R;

public class RusumeActivity extends AppCompatActivity {
private EditText edName,edBurth,edpolitics,edemail,edphone,edaddress,edmary;
private EditText edqwer,deteached,edworkming,edshowyouself;
private Button confirm;

 private SharedPreferences sp;
    private static final String TEMP_INFO="temp_info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rusume);
        initView();
        initDo();
    }

    private void initDo() {

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
                editor.putString("edName", edName.getText().toString());
                editor.putString("edBurth", edBurth.getText().toString());
                editor.putString("edpolitics", edpolitics.getText().toString());
                editor.putString("edemail", edemail.getText().toString());
                editor.putString("edphone", edphone.getText().toString());
                editor.putString("edaddress", edaddress.getText().toString());

                editor.putString("edmary", edmary.getText().toString());
                editor.putString("edqwer", edqwer.getText().toString());
                editor.putString("deteached", deteached.getText().toString());
                editor.putString("edworkming", edworkming.getText().toString());
                editor.putString("edshowyouself", edshowyouself.getText().toString());

                Toast.makeText(getApplicationContext(),"已保存",Toast.LENGTH_SHORT).show();
                editor.commit();


            }
        });

    }

    private void initView() {
        edName=findViewById(R.id.editText_res_name);
        edBurth = findViewById(R.id.editText_res_burth);
        edpolitics=findViewById(R.id.editText_res_politics);
        edemail=findViewById(R.id.editText_res_e_mail);
        edphone=findViewById(R.id.editText_res_telphone);

        edaddress=findViewById(R.id.editText_res_address);
        edmary  =findViewById(R.id.editText_res_if_mary);
        edqwer=findViewById(R.id.editText_res_qwer);
        deteached=findViewById(R.id.editText_res_teached);
        edworkming=findViewById(R.id.editText_find_work_dear);

        edshowyouself=findViewById(R.id.editText_show_youself);

        confirm=findViewById(R.id.button_res_confirmof);



        sp = getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE);
        String content1 = sp.getString("edName", "");
        String content2 = sp.getString("edBurth", "");
        String content3 = sp.getString("edpolitics", "");
        String content4 = sp.getString("edemail", "");
        String content5 = sp.getString("edphone", "");
        String content6 = sp.getString("edaddress", "");
        String content7 = sp.getString("edmary", "");
        String content8 = sp.getString("edqwer", "");
        String content9 = sp.getString("deteached", "");
        String content10 = sp.getString("edworkming", "");
        String content11 = sp.getString("edshowyouself", "");
        edName.setText(content1);
        edBurth.setText(content2);
        edpolitics.setText(content3);
        edemail.setText(content4);
        edphone.setText(content5);

        edaddress.setText(content6);
        edmary.setText(content7);
        edqwer.setText(content8);
        deteached.setText(content9);
        edworkming.setText(content10);
        edshowyouself.setText(content11);
    }
}
