package br.com.htech.firula8.Login;

import android.content.Context;

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
}
