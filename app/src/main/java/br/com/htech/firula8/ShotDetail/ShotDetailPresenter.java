package br.com.htech.firula8.ShotDetail;

import android.app.Activity;
import android.content.Context;

import java.io.EOFException;
import java.util.List;

import br.com.htech.firula8.API.RetrofitConection;
import br.com.htech.firula8.Main.MainPresenter;
import br.com.htech.firula8.Modelo.Shot;
import br.com.htech.firula8.util.TestarConexao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arturhayne on 27/01/2018.
 */

public class ShotDetailPresenter implements ShotDetailContract.UserAction, TestarConexao.TentarNovamente {

    private Context context;
    private ShotDetailContract.View view;
    private Shot shot;

    public ShotDetailPresenter(Context context, ShotDetailContract.View mView) {
        this.context = context;
        this.view = mView;
    }

    @Override
    public void loadShot(String id){
        if (TestarConexao.VerificaConexao((Activity) context,this,"load_single_shot")) {

            view.showProgressBar(true);

            RetrofitConection
                    .getInstance(context)
                    .getBaseAPI()
                    .getShot(id)
                    .enqueue(new Callback<Shot>() {
                        @Override
                        public void onResponse(Call<Shot> call, Response<Shot> response) {
                            if (response.isSuccessful()) {

                                shot = response.body();
                                if(shot ==null){
                                    view.showEmpty(true);
                                }else{
                                    view.showShot(shot);
                                    view.showEmpty(false);
                                }

                            }else{
                                view.showEmpty(true);
                            }
                            view.showProgressBar(false);
                        }
                        @Override
                        public void onFailure(Call<Shot> call, Throwable t) {
                            view.showEmpty(true);
                            view.showProgressBar(false);
                        }
                    });

        }
    }

    @Override
    public void tentarNovamente(String tag) {

    }
}
