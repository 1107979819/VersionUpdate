package com.example.wyl.versionupdate.Utils;

import android.util.Log;

import com.example.wyl.versionupdate.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WYL on 2016/5/8.
 */
public class VersionUpdateUtils {

    //连接超时时间
    public final  int  CONNECTIONTIMEOUT = 5000;

    //请求超时
    public final int REQUESTTIMEOUT = 5000;

    public final String URL = "http://wenyl.top/android/update/updateinfo.json";

    public void getNewVersion()
    {

        try {
            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(CONNECTIONTIMEOUT, TimeUnit.MILLISECONDS)   //设置连接超时
                    .readTimeout(REQUESTTIMEOUT, TimeUnit.MILLISECONDS)         //设置读操作超时
                    .writeTimeout(REQUESTTIMEOUT, TimeUnit.MILLISECONDS)        //设置写操作超时
                    .build();

            Response response = client.newCall(request).execute();

            if(response.isSuccessful())
            {
                String json =response.body().string();
                Log.i("Test","re:"+json);
                Gson gson = new Gson();
                VersionEntity  versionEntity = gson.fromJson(json,VersionEntity.class);
                Log.i("Test","code:"+versionEntity.getCode()+" des:"+versionEntity.getDes()+" url:"+versionEntity.getApkurl());


            }else
            {
                //请求失败
            }


        }catch (IOException e)
        {
            //这里进行异常处理

        }


    }
    class VersionEntity{
        private String code;
        private String des;
        private String apkurl;

        public VersionEntity(String apkurl, String code, String des) {
            this.apkurl = apkurl;
            this.code = code;
            this.des = des;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getApkurl() {
            return apkurl;
        }

        public void setApkurl(String apkurl) {
            this.apkurl = apkurl;
        }
    }//end VesionEntity
}
