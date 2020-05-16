//package com.example.people.net;
//
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Class description:
// *
// * @author zp
// * @version 1.0
// * @see ApiRetrofit
// * @since 2019-06-28
// */
//public class ApiRetrofit {
//
//    private static String URL = "http://47.106.84.158:80/funchat/";
//    private static Object sLock = new Object();
//    private static ApiRetrofit sApiRetrofit;
//    private Retrofit mRetrofit;
//
//    public static ApiRetrofit getInstance() {
//        if(sApiRetrofit == null) {
//            synchronized (sLock) {
//                if(null == sApiRetrofit) {
//                    sApiRetrofit = new ApiRetrofit();
//                }
//            }
//        }
//        return sApiRetrofit;
//    }
//
//    public ApiRetrofit() {
//        Retrofit.Builder builder = new Retrofit.Builder();
//        builder.baseUrl(URL);
//        builder.addConverterFactory(GsonConverterFactory.create());
//        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync());
//        mRetrofit = builder.build();
//    }
//
//    public <T> T getService(Class<T> clz) {
//        return mRetrofit.create(clz);
//    }
//}
