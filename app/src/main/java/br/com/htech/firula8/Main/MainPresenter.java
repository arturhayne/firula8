package br.com.htech.firula8.Main;

import android.content.Context;

import java.util.List;

import br.com.htech.firula8.Adapter.ShotAdapter;
import br.com.htech.firula8.Modelo.Shot;

/**
 * Created by arturhayne on 27/01/2018.
 */

public class MainPresenter implements MainContract.UserAction,ShotAdapter.ShotOnClick {

    private MainContract.View view;
    private Context context;
    private List<Shot> mList;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void abrirListaShot(Integer position) {

    }
}
