package br.com.htech.firula8.Login.LoginFragment;

import android.content.Context;

/**
 * Created by artur.oliveira on 30/01/2018.
 */

public class LoginFragmentPresenter implements LoginFragmentContract.UserAction{

    Context context;
    LoginFragmentContract.View mView;

    public LoginFragmentPresenter(Context context, LoginFragmentContract.View mView) {
        this.context = context;
        this.mView = mView;
    }



}
