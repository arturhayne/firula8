package br.com.htech.firula8.API;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by artur.oliveira on 29/01/2018.
 */

public class RetrofitConection {

    private BaseApi baseAPI;
    private Context context;
    private Gson gson;
    private Retrofit retrofit;
    private static RetrofitConection instance;

    private static String lastToken;

    public RetrofitConection(Context c){
        this.context = c;
        gson = new GsonBuilder().setLenient().create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor(){
              @Override
              public Response intercept(Chain chain) throws IOException{
                  Request original = chain.request();

                  Request.Builder requestBuilder = original.newBuilder()
                          .header("Accept", "application/json")
                          .header("Authorization", "Bearer" + " " + ApiConstants.DRIBBBLE_CLIENT_ACCESS_TOKEN)
                          .method(original.method(), original.body());

                  Request request = requestBuilder.build();
                  return chain.proceed(request);
              }
          });

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.DRIBBBLE_V1_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        baseAPI = retrofit.create(BaseApi.class);
    }

    public static RetrofitConection getInstance(Context c){

        if (instance == null) {
            instance = new RetrofitConection(c);
        }
        return instance;
    }

    public BaseApi getBaseAPI() {
        return baseAPI;
    }

    public void clear() {
        instance = null;
    }

}
