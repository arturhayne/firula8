package br.com.htech.firula8.Main;

import java.util.List;

import br.com.htech.firula8.Modelo.Shot;

/**
 * Created by arturhayne on 27/01/2018.
 */

public interface MainContract {

    interface View{
        void showProgressBar(Boolean show);
        void showShots(List<Shot> lista);
        void showEmpty(boolean show);
    }

    interface UserAction {

        void carregarShots();
    }

}
