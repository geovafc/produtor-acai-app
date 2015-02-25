package com.br.amazongold.webservice;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance=null;
    private RequestQueue requestQueue;

    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }

    private  VolleySingleton(Context context){
       requestQueue= Volley.newRequestQueue(context);


    }

    public static VolleySingleton getInstance(Context context){
        if (mInstance==null){
            mInstance=new VolleySingleton(context);
        }
        return mInstance;
    }



}
