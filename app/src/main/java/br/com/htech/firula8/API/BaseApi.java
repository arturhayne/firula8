package br.com.htech.firula8.API;

import android.database.Observable;

import java.util.List;

import br.com.htech.firula8.Modelo.Shot;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by artur.oliveira on 29/01/2018.
 */

public interface BaseApi {

    @GET("/v1/shots")
    Call<List<Shot>> getShots();

    @GET("/v1/user/following/shots")
    Observable<Response<List<Shot>>> listFollowingShots(@Query("per_page") int perPage);


}
