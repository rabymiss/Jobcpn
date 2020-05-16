package com.example.people.common;

import com.example.people.Api.Api;
import com.example.people.Api.ApiRetrofit;
import com.google.gson.Gson;

public class Common {
   public static final String API = "192.168.0.104";
  public   static Api apicommont=ApiRetrofit.getInstance().getService(Api.class);
  Gson gson=new Gson();
  public void cdjson(){



  }

}
