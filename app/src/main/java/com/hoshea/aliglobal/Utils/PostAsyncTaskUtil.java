package com.hoshea.aliglobal.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import okhttp3.FormBody;

/**
 * Created by Hoshea on 2016/5/20.
 */
public class PostAsyncTaskUtil extends AsyncTask<String, Integer, String> {

    ProgressDialog pdialog;
    Context ctx;
    FormBody formBody;
    static String result;
    String title;
    String message;

    public static String getResult() {
        return result;
    }


    public PostAsyncTaskUtil(Context ctx, FormBody formBody, String title, String message) {
        this.ctx = ctx;
        this.formBody = formBody;
        this.title = title;
        this.message= message;
    }



    @Override
    protected String doInBackground(String... params) {

        String json = HttpUtil.post(params[0], formBody);

        publishProgress();

        return json;
    }

    @Override
    protected void onPostExecute(String s) {
        result=s;
        pdialog.dismiss();
    }

    @Override
    protected void onPreExecute() {

        pdialog = new ProgressDialog(ctx);

        pdialog.setTitle(title);

        pdialog.setMessage(message);
        pdialog.setCancelable(false);

        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        pdialog.setIndeterminate(true);

        pdialog.show();
    }
}
