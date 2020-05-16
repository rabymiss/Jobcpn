package com.example.people.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.RegisterEntity;
import com.example.people.R;
import com.example.people.tool.Validator;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestarPswActivity extends AppCompatActivity {
private EditText phone,code;
private TextView textViewqd;
private Button button;
private TimeCount mTimeCount;
private boolean timeFlag = true;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_restarpsw);
phone=findViewById(R.id.text_account_login);
code=findViewById(R.id.text_password_login);
textViewqd=findViewById(R.id.text_fasongyzm);
button=findViewById(R.id.button_login);
button.setEnabled(false);

}
public void setCode(View view){
//
String phone1=phone.getText().toString();
if (!Validator.isMobileNumber(phone1)){
phone.setError("此号码格式不正确");
phone.requestFocus();
return;
}else {
mTimeCount = null;
mTimeCount = new TimeCount(60 * 1000, 1000);
mTimeCount.start();
button.setEnabled(true);
RegisterEntity registerEntity=new RegisterEntity(phone1,null);

Api api= ApiRetrofit.getInstance().getService(Api.class);



Gson gson = new Gson();
String jsonstr = gson.toJson(registerEntity);
final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr);

Call<RegisterEntity> task=api.loginphfor(requestBody);
task.enqueue(new Callback<RegisterEntity>() {
@Override
public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {

}

@Override
public void onFailure(Call<RegisterEntity> call, Throwable t) {

}
});




}

}

public void comf(View view){

String p2=phone.getText().toString();
String code1=code.getText().toString();
if (code1.length()!=6){
code.setError("验证码不为六位数");
code.requestFocus();
return;
}else
{

RegisterEntity registerEntity=new RegisterEntity(p2,code1);

Api  api=ApiRetrofit.getInstance().getService(Api.class);

Gson gson = new Gson();
String jsonstr = gson.toJson(registerEntity);
final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr);


Call<RegisterEntity> task = api.LoginResultfor(requestBody);
task.enqueue(new Callback<RegisterEntity>() {
@Override
public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {

int a = response.code();
if (response.body().getUsername().equals("1")) {
String p3=phone.getText().toString();

Intent intent = new Intent(RestarPswActivity.this, RegisterActivity.class);
intent.putExtra("account",p3);
startActivity(intent);
    RestarPswActivity.this.fileList();

} else if (response.body().getUsername().equals("0")) {
Toast.makeText(getApplicationContext(), "验证码不正确", Toast.LENGTH_SHORT).show();

}
}

@Override
public void onFailure(Call<RegisterEntity> call, Throwable t) {

Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
}
});


}


}

@Override
protected void onDestroy() {
super.onDestroy();

mTimeCount = null;
timeFlag = false;
}
/**
 * 实现倒计时的内部类
 */
private class TimeCount extends CountDownTimer {

/**
 * 两个参数分别是（倒计时开始时间，间隔时间）
 */
public TimeCount(long millisInFuture, long countDownInterval) {
super(millisInFuture, countDownInterval);
}

/**
 * 计时过程显示 按钮不可用 设置为灰色
 *
 * @param millisUntilFinished
 */
@Override
public void onTick(long millisUntilFinished) {
textViewqd.setClickable(false);
textViewqd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
textViewqd.setText("(" + millisUntilFinished / 1000 + ")秒后重试");
}

/**
 * 计时结束调用, 计时结束后执行的事件可以在onFinish()中做
 * 另外一个方法是onTick(现在还剩的时间)，。
 */
@Override
public void onFinish() {
textViewqd.setClickable(true);
textViewqd.setText("获取验证码");
textViewqd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
}
}



}

