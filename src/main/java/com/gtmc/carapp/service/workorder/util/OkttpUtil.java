package com.gtmc.carapp.service.workorder.util;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkttpUtil {
	
    private  static final OkHttpClient client = new OkHttpClient.Builder().
            connectionPool(new ConnectionPool(100,10, TimeUnit.MINUTES))
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build();

    public static String postFormBody(String url, FormBody body){
        Request request = new Request.Builder().url(url)
                .post(body).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
