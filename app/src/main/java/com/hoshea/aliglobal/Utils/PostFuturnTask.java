package com.hoshea.aliglobal.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import okhttp3.FormBody;

/**
 * 对okHttp的二次封装
 *
 * 把HttpUtil加入并发
 *
 * Created by Hoshea on 2016/5/20.
 */
public class PostFuturnTask extends FutureTask<String> {

    public PostFuturnTask(String url) {
        super(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
    }

    /**
     * 封装json请求
     * @param url
     * @param json
     */
    public PostFuturnTask(final String url, final String json) {
        super(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return HttpUtil.JsonPost(url,json);
            }
        });
    }

    /**
     *封装form请求
     * @param url
     * @param formBody
     */
    public PostFuturnTask(final String url, final FormBody formBody) {
        super(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return HttpUtil.post(url,formBody);
            }
        });
    }
}
