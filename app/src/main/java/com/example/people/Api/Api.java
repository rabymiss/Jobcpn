package com.example.people.Api;

import com.example.people.Api.entity.Resume;
import com.example.people.Entity.cpn.CpnMessage;
import com.example.people.Entity.cpn.ImguEntity;
import com.example.people.Entity.job.JobMessage;
import com.example.people.Entity.job.JobMessageAll;
import com.example.people.Entity.job.JobResultEntity;
import com.example.people.Entity.RegisterEntity;
import com.example.people.Entity.job.ReturnJobMessageAll;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface Api {
    @POST("user/register")
    Call<RegisterEntity>posterRegister(@Body RequestBody requestBody);

    @POST("add/job")
    Call<JobMessage>postJobRegister(@Body RequestBody requestBody);
    //删除
    @POST("delete/job")
    Call<JobMessage>deletejob(@Body RequestBody requestBody);
    @GET("find/job")
    Call<JobResultEntity> getJson();
    @GET("doing/socket")
    Call<RegisterEntity> socketstart();
    //匹配公司
    @POST("find/job/byid")
    Call<JobResultEntity> getjob(@Body RequestBody requestBody);
    @POST("user/login")
    Call<RegisterEntity>LoginResult(@Body RequestBody requestBody);

    //加载全部公司信息
    @GET("find/all/message")
    Call<ReturnJobMessageAll>findMsgsAll();
    @POST("find/cpn")
    Call<List<CpnMessage>>findcpn(@Body RequestBody requestBody);
    //上传所有信息
    @POST("add/job/message")
    Call<JobMessageAll>addMsgsAll(@Body RequestBody requestBody);
    //上传公司所有信息
    @POST("add/cpn")
    Call<CpnMessage>addcpn(@Body RequestBody requestBody);
    @Multipart
    @POST("updown12")
    Call<ImguEntity>load(@Part MultipartBody.Part part, @PartMap Map<String,String> params);
    @Multipart
    @POST("updown/cook")
    Call<RegisterEntity>loadcook(@Part MultipartBody.Part part, @PartMap Map<String,String> params);
    @POST("user/update")
    Call<RegisterEntity>updateuserr(@Body RequestBody requestBody);
        //忘记密码
        @POST("user/login/forget")
        Call<RegisterEntity> loginphfor(@Body RequestBody requestBody);
    @POST("user/login/for")
    Call<RegisterEntity> LoginResultfor(@Body RequestBody requestBody);
    @POST("find/mesume/byid")
    Call<Resume> findresunme(@Body RequestBody requestBody);
}
