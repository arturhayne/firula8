package br.com.htech.firula8.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.htech.firula8.Adapter.ShotAdapter;
import br.com.htech.firula8.Login.LoginActivity;
import br.com.htech.firula8.Modelo.Shot;
import br.com.htech.firula8.R;
import br.com.htech.firula8.ShotDetail.ShotDetailActivity;
import br.com.htech.firula8.util.SessionManager;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        SwipeRefreshLayout.OnRefreshListener{

    private MainContract.UserAction mUserActionsListener;

    private TextView tv_empty;
    private ProgressBar progress_drib;
    private ShotAdapter adapter;
    private SwipeRefreshLayout swipe_shots;
    private RecyclerView list_shots;

    public final static String SHOT_OBJECT= "shot_obj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserActionsListener = new MainPresenter(this,this);
        iniciarViews();
    }

    private void iniciarViews(){
        tv_empty = findViewById(R.id.tv_empty);
        progress_drib = findViewById(R.id.progress_shot);
        swipe_shots = findViewById(R.id.swipe_shots);
        swipe_shots.setOnRefreshListener(this);
        list_shots = findViewById(R.id.recycler_list_shots);
        mUserActionsListener.carregarShots();
        list_shots.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShotAdapter(this, new ArrayList<Shot>(), (MainPresenter)mUserActionsListener);
        list_shots.setAdapter(adapter);
        mUserActionsListener.carregarShots();
    }

    @Override
    public void showProgressBar(Boolean show) {
        if (show){
            progress_drib.setVisibility(View.VISIBLE);
        }else {
            progress_drib.setVisibility(View.INVISIBLE);
           if (swipe_shots.isRefreshing()){
               swipe_shots.setRefreshing(false);
            }
        }
    }

    @Override
    public void showShots(List<Shot> lista) {

        adapter.replaceData(lista);
    }

    @Override
    public void showEmpty(boolean show) {
        if (show){
            tv_empty.setVisibility(View.VISIBLE);
        }else {
           tv_empty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showItem(Shot shot) {
        Intent intent = new Intent(this, ShotDetailActivity.class);
        intent.putExtra(SHOT_OBJECT,shot);
        startActivity(intent);
    }

    @Override
    public void onRefresh()
    {
        mUserActionsListener.carregarShots();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:{
                SessionManager.saveToken(this,null);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
