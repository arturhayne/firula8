package br.com.htech.firula8.Login;

/**
 * Created by artur.oliveira on 30/01/2018.
 */

public interface LoginContract {

    interface View{
        void callDialog(String titulo, String mensagem);
        void callMain();
    }

    interface UserAction{
        void getAcessToken(String code);
    }
}
