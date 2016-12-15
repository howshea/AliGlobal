package com.hoshea.aliglobal.Application;

import android.app.Application;

import com.hoshea.aliglobal.model.User;

/**
 * Created by Hoshea on 2016/5/20.
 */
public class MyApplication extends Application {
    private static final String SIGNINURL="http://192.168.56.1:8080/shopping/servlet/LoginServlet";

    private static final String CARTURL="http://192.168.56.1:8080/shopping/servlet/ShoppingCartServlet";

    public static String getGOODSURL() {
        return GOODSURL;
    }

    private static final String GOODSURL="http://192.168.56.1:8080/shopping/servlet/GoodsServlet";

    public static String getCATEGORYURL() {
        return CATEGORYURL;
    }

    private static final String CATEGORYURL="http://192.168.56.1:8080/shopping/servlet/CategoryServlet";

    public static String getURL() {
        return URL;
    }

    private static final String URL="http://192.168.56.1:8080/shopping";

    private  User user;

    public static String getADDTOCARTSERVLET() {
        return ADDTOCARTSERVLET;
    }

    public static String getPAYURL() {
        return PAYURL;
    }

    private static final String PAYURL="http://192.168.56.1:8080/shopping/servlet/PayServlet";

    private static final String ADDTOCARTSERVLET="http://192.168.56.1:8080/shopping/servlet/AddtoCartServlet";

    public static String getORDERSURL() {
        return ORDERSURL;
    }

    private static final String ORDERSURL="http://192.168.56.1:8080/shopping/servlet/OrdersServlet";
    @Override
    public void onCreate() {

        super.onCreate();
    }

    public static String getSIGNINURL() {
        return SIGNINURL;
    }

    public static String getCARTURL() {
        return CARTURL;
    }

    public  User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
