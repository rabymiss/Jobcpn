package com.example.people.img;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.bumptech.glide.Glide;
import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.example.people.Entity.RegisterEntity;
import com.example.people.Entity.cpn.ImguEntity;
import com.example.people.R;
import com.example.people.UserViewModel;
import com.example.people.common.Common;
import com.example.people.data.model.LoginUser;
import com.example.people.tableDo.ImgViewmodel;
import com.example.people.tool.Validator;
import com.example.people.ui.login.cpnmsg.CpnMsgActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoActivity extends BaseActivity implements View.OnClickListener {
private Uri uri01;
    //需要的权限数组 读/写/相机
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
   private List<String>list=new ArrayList<>();
    private Button Button01,buttonconfirm;
    private Button Button02;
    private ImageView ImageView01;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGES = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };private static final String TEMP_INFO = "temp_info";
    private SharedPreferences sp;
    private LiveData<List<LoginUser>> findmsg;
    private UserViewModel userViewmodel;

private ImgViewmodel imgViewmodel;
    private LiveData<List<LoginUser>> findimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        verifyStoragePermissions(this);
        userViewmodel= ViewModelProviders.of(this).get(UserViewModel.class);
        imgViewmodel=ViewModelProviders.of(this).get(ImgViewmodel.class);
        buttonconfirm=findViewById(R.id.Button034);
//        list.clear();
        if (list.size()==0){
            buttonconfirm.setEnabled(false);
        }
       findusername();
       updata();
        initView();
    }

    private void findusername() {
        findmsg=userViewmodel.getAllUsersLive();
        findmsg.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {


                    for (LoginUser registerEntity:loginUsers){
                        SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
                        editor.putString("username",registerEntity.getUserId());
                        editor.apply();

                }
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGES, REQUEST_EXTERNAL_STORAGE);
        }

    }

    private void updata() {

    }

    private void initView() {

        Button01 = findViewById(R.id.Button01);
        Button02 = findViewById(R.id.Button02);
        ImageView01 = findViewById(R.id.ImageView);

        Button01.setOnClickListener(this);
        Button02.setOnClickListener(this);
    }
public void onClick1(View view){

}
//确定
    public void onClick123(View view){



          Intent intent=new Intent(PhotoActivity.this, CpnMsgActivity.class);
          intent.putExtra("url",list.get(0));
          intent.putExtra("filename",list.get(1));
          startActivity(intent);
          PhotoActivity.this.finish();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Button01:
                toPicture();

                break;
            case R.id.Button02:
                //检查是否已经获得相机的权限
                if(verifyPermissions(PhotoActivity.this,PERMISSIONS_STORAGE[2]) == 0){

                    ActivityCompat.requestPermissions(PhotoActivity.this, PERMISSIONS_STORAGE, 3);
                }else{
                    //已经有权限
                    toCamera();  //打开相机
                }
                break;
        }
    }

    private File tempFile = null;   //新建一个 File 文件（用于相机拿数据）

    //获取 相机 或者 图库 返回的图片






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断返回码不等于0
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_CANCELED) {    //RESULT_CANCELED = 0(也可以直接写“if (requestCode != 0 )”)
            //读取返回码
            switch (requestCode) {
                case 100:   //相册返回的数据（相册的返回码）
                   list.clear();
                    uri01=data.getData();
                    list.add(uri01.toString());
                     buttonconfirm.setEnabled(true);

                    String[] arr={MediaStore.Images.Media.DATA};
                    Cursor cursor=managedQuery(uri01,arr,null,null,null);
                    int img_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    cursor.moveToFirst();
                    String pa=cursor.getString(img_index);
                    String a=arr[0];
                        String x=cursor.getString(cursor.getColumnIndex(a));
                        File tempFile =new File(x.trim());
                        String fileName=tempFile.getName();

                        list.add(fileName);


                    File file=new File(pa);
                    list.add(pa);

                    ContentResolver contentResolver=this.getContentResolver();
                    try {
                        Bitmap bitmap=BitmapFactory.decodeStream(contentResolver.openInputStream(uri01));



                        Glide.with(this).load(bitmap).into(ImageView01);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    sp=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE);
                    String  usn=sp.getString("username","");



                    File files=new File(pa);
                    Map<String,String>params12=new HashMap<>();
                    params12.put("description",usn);

//.......................................
                    RequestBody body=RequestBody.create(MediaType.parse("image/jpeg"),files);
                    MultipartBody.Part part=MultipartBody.Part.createFormData("files",fileName,body);
                    Call<ImguEntity> task= Common.apicommont.load(part,params12);
                    task.enqueue(new Callback<ImguEntity>() {
                        @Override
                        public void onResponse(Call<ImguEntity> call, Response<ImguEntity> response) {
                            ImguEntity imguEntity=response.body();
                            imgViewmodel.deleteall();
                            imgViewmodel.insert(imguEntity);
                           System.out.println("返回的图片网络---------"+imguEntity.getUsername());

                        }

                        @Override
                        public void onFailure(Call<ImguEntity> call, Throwable t) {
                            Log.d("-----------------",t.toString());
                        }
                    });
//......................




                    break;
                case 101:  //相机返回的数据（相机的返回码）
                    list.clear();
                    try {
                        tempFile = new File(Environment.getExternalStorageDirectory(), "fileImg.jpg");  //相机取图片数据文件
                        Uri uri02 = Uri.fromFile(tempFile);   //图片文件
                        list.add(uri02.toString());
                        list.add(tempFile.getName());
                        System.out.println("----"+tempFile.getName());
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri02));
                        ImageView01.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }


    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        startActivityForResult(intent,100);

    }

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //跳转到 ACTION_IMAGE_CAPTURE
        //判断内存卡是否可用，可用的话就进行存储
        //putExtra：取值，Uri.fromFile：传一个拍照所得到的文件，fileImg.jpg：文件名
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "fileImg.jpg")));
        startActivityForResult(intent, 101); // 101: 相机的返回码参数（随便一个值就行，只要不冲突就好）
    }
}
