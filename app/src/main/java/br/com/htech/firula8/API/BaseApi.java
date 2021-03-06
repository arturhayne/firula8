package br.com.htech.firula8.API;

import android.database.Observable;

import com.google.gson.JsonElement;

import java.util.List;

import br.com.htech.firula8.Modelo.Shot;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by artur.oliveira on 29/01/2018.
 */

public interface BaseApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<JsonElement> getTokenClient(@Field("client_id") String client_id,
                                     @Field("client_secret") String client_secret,
                                     @Field("code") String code);

    //Esse serviço infelizmente não existe mais na v2
    @GET("/v2/shots")
    Call<List<Shot>> getAllShots();

    @GET("/v2/user/shots")
    Call<List<Shot>> getShots();

    @GET("/v2/user/shots")
    Call<List<Shot>> getShotsPerPage(@Query("page") String page,@Query("per_page") String per_page);

    @GET("/v2/shots/{id}")
    Call<Shot> getShot(@Path("id") String id);


}
