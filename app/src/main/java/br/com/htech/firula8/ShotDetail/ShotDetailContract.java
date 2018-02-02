package br.com.htech.firula8.ShotDetail;

import br.com.htech.firula8.Modelo.Shot;

/**
 * Created by arturhayne on 27/01/2018.
 */

public interface ShotDetailContract {

    interface View {
         void showProgressBar(Boolean show);
         void showEmpty(boolean show);
         void showShot(Shot shot);
    }

    interface UserAction{
        void loadShot(String id);
    }
}
