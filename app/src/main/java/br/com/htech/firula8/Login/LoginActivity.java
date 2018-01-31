package br.com.htech.firula8.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.htech.firula8.API.ApiConstants;
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
        if(getIntent().getData()!=null){
            retornoAturizacao(getIntent().getData());
        }
    }

    private void iniciarView(){
        btnLogin = findViewById(R.id.btnLogin);
        eventosClick();
    }

    /**
     * 1. Redirect users to request Dribbble access.
     */
    private void eventosClick(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(ApiConstants.DRIBBBLE_AUTHORIZE_URL +
                                "?client_id="
                                + ApiConstants.DRIBBBLE_CLIENT_ID
                                + "&redirect_uri=" +
                                ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI));
                startActivity(intent);
            }
        });
    }

    /**
     * Retorno da chamada web
     * 2. Dribbble redirects back to your site
     * ...Dribbble redirects back to your site with a temporary code in a code parameter
     * @param uri
     */
    private void retornoAturizacao(Uri uri){
        if(uri.getPath().contains(ApiConstants.DRIBBBLE_AUTHORIZE_CALLBACK_URI)){
            String code = uri.getQueryParameter("code");
            userAction.getAcessToken(code);
        }
    }


}
