package br.com.htech.firula8.ShotDetail;

import android.content.Context;

/**
 * Created by arturhayne on 27/01/2018.
 */

public class ShotDetailPresenter implements ShotDetailContract.UserAction{

    private Context context;
    private ShotDetailContract.View mView;

    public ShotDetailPresenter(Context context, ShotDetailContract.View mView) {
        this.context = context;
        this.mView = mView;
    }
}
