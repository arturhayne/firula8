package br.com.htech.firula8.API;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(getInterceptorAuthorization(context))
                .build();


        retrofit = new Retrofit.Builder().baseUrl("https://dribbble.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
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


    private static Interceptor getInterceptorAuthorization(final Context context){

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original;



                return chain.proceed(request);

              
            }
        };

        return interceptor;
    }

}
