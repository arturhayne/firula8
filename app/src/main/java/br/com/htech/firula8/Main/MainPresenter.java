package br.com.htech.firula8.Main;

import android.app.Activity;
import android.content.Context;

import java.io.EOFException;
import java.util.List;

import br.com.htech.firula8.API.RetrofitConection;
import br.com.htech.firula8.Adapter.ShotAdapter;
import br.com.htech.firula8.Modelo.Shot;
import br.com.htech.firula8.util.TestarConexao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arturhayne on 27/01/2018.
 */

public class MainPresenter implements MainContract.UserAction,ShotAdapter.ShotOnClick, TestarConexao.TentarNovamente {

    private MainContract.View view;
    private Context context;
    private List<Shot> mList;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void abrirListaShot(Integer position) {
        view.showItem(mList.get(position));
    }

    /**
     * Função que chama o serviço para carregar os shots
     */
    @Override
    public void carregarShots() {

        if (TestarConexao.VerificaConexao((Activity) context,this,"load_shots")) {

            view.showProgressBar(true);

            RetrofitConection
                    .getInstance(context)
                    .getBaseAPI()
                    .getShots()
                    .enqueue(new Callback<List<Shot>>() {
                        @Override
                        public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {

                            if (response.isSuccessful()){

                                mList = response.body();
                                if (mList == null || mList.size() == 0) {
                                    view.showEmpty(true);
                                } else {
                                    view.showEmpty(false);
                                    view.showShots(mList);
                                }

                            }else {
                                view.showEmpty(true);
                            }

                            view.showProgressBar(false);
                        }

                        @Override
                        public void onFailure(Call<List<Shot>> call, Throwable t) {
                            view.showEmpty(true);
                            view.showProgressBar(false);
                            if (!(t instanceof EOFException))
                                TestarConexao.calldialog((Activity) context,MainPresenter.this,"load_shots");
                        }
                    });

        }
    }

    @Override
    public void tentarNovamente(String tag) {

    }
}
