package br.com.htech.firula8.API;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import br.com.htech.firula8.Modelo.Token;
import br.com.htech.firula8.util.SessionManager;
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

    public RetrofitConection(Context c,String acesso){
        this.context = c;

        gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(getInterceptorAuthorization(context))
                .build();


        retrofit = new Retrofit.Builder().baseUrl(acesso)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        baseAPI = retrofit.create(BaseApi.class);
    }


    public RetrofitConection(Context c){
        this.context = c;

        gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(getInterceptorAuthorization(context))
                .build();


        retrofit = new Retrofit.Builder().baseUrl(ApiConstants.DRIBBBLE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        baseAPI = retrofit.create(BaseApi.class);
    }

    public static RetrofitConection conectionToken(Context c){
        return new RetrofitConection(c,ApiConstants.DRIBBBLE_GET_ACCESS_TOKEN_URL);
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

                Token token = SessionManager.getToken(context);

                if(token!=null&&token.getAccess_token()!=null){
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", token.getToken_type() + " "+token.getAccess_token());

                    request = requestBuilder.build();
                }

                return chain.proceed(request);
            }
        };

        return interceptor;
    }

}
