package br.com.htech.firula8.Login;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import br.com.htech.firula8.API.ApiConstants;
import br.com.htech.firula8.API.RetrofitConection;
import br.com.htech.firula8.Modelo.Token;
import br.com.htech.firula8.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by artur.oliveira on 30/01/2018.
 */

public class LoginPresenter implements LoginContract.UserAction{

    Context context;
    LoginContract.View mView;

    public LoginPresenter(Context context, LoginContract.View mView) {
        this.context = context;
        this.mView = mView;
    }


    /**
     * Requisição POST para pegar o token utilizando client_id, secret e code
     * ...Exchange this for an access token: POST https://dribbble.com/oauth/token
     * @param code
     */
    @Override
    public void getAcessToken(String code) {
        RetrofitConection
                .conectionToken(context)
                .getBaseAPI()
                .getTokenClient(ApiConstants.DRIBBBLE_CLIENT_ID,
                        ApiConstants.DRIBBBLE_CLIENT_SECRET,
                        code).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                Log.i("LOG", response.toString());
                if (response.isSuccessful()&&response.code()==200) {

                    Token token = new Gson()
                            .fromJson(response.body(), Token.class);
                    SessionManager.saveToken(context,token);
                    mView.callMain();

                } else {
                        mView.callDialog("Problemas!","Problemas ao logar");
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.i("LOG", "onFailure: " + t.getLocalizedMessage());
                mView.callDialog("Problemas!","Problemas ao logar");
            }
        });
    }
}
