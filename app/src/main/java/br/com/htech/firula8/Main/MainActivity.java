package br.com.htech.firula8.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.htech.firula8.Adapter.ShotAdapter;
import br.com.htech.firula8.R;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainContract.UserAction mUserActionsListener;

    private RecyclerView list_moradores;
    private TextView tv_empty;
    private ProgressBar progress_drib;
    private ShotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserActionsListener = new MainPresenter(this,this);
        iniciarViews();
    }

    private void iniciarViews(){

    }
}
