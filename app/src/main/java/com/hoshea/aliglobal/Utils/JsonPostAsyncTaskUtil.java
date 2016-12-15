package com.hoshea.aliglobal.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Hoshea on 2016/5/20.
 */
public class JsonPostAsyncTaskUtil extends AsyncTask<String, Integer, String> {

    ProgressDialog pdialog;
    Context ctx;
    String json;
    static String result;
    String title;
    String message;
    public JsonPostAsyncTaskUtil(Context ctx, String json, String title, String message) {
        this.ctx = ctx;
        this.json= json;
        this.title = title;
        this.message= message;
    }



    @Override
    protected String doInBackground(String... params) {

        String jsonResult = HttpUtil.JsonPost(params[0], json);

        publishProgress();

        return jsonResult;
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
