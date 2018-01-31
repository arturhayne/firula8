package br.com.htech.firula8.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.htech.firula8.Login.LoginFragment.LoginFragment;
import br.com.htech.firula8.Main.MainActivity;
import br.com.htech.firula8.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private Button btnLogin;
    private LoginContract.UserAction userAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userAction = new LoginPresenter(this,this);
        iniciarView();
    }


    private void iniciarView(){
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment authFragment = (LoginFragment) getSupportFragmentManager()
                        .findFragmentByTag(LoginFragment.class.getSimpleName());
                if (authFragment == null) {
                    authFragment = LoginFragment.newInstance();
                    //loginPresenter.attachFragmentView(authFragment);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.container, authFragment, LoginFragment.class.getSimpleName())
                            .commit();
                }
            }
        });
    }

    private void startWebView(){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
