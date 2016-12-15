package com.hoshea.aliglobal.Utils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Hoshea on 2016/5/20.
 */
public class HttpUtil {

    /**
     * 全局使用一个okHttpClient
     */
    public static OkHttpClient okHttpClient =new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 用form请求数据
     * @param url
     * @param formBody
     * @return String
     */
    public static String post(String url, FormBody formBody) {


        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = null;
        try {

            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }
            else{
                throw new IOException("unexpexted code :"+response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用实体转成的json请求服务器
     * @param url
     * @param json
     * @return String
     */
    public static String JsonPost(String  url, String json)  {

        RequestBody requestBody = FormBody.create(HttpUtil.JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }
            else{
                throw new IOException("unexpexted code :"+response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
