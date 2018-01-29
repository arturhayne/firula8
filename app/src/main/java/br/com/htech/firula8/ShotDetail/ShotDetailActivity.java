package br.com.htech.firula8.ShotDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.htech.firula8.Main.MainContract;
import br.com.htech.firula8.R;

public class ShotDetailActivity extends AppCompatActivity implements ShotDetailContract.View{

    private ShotDetailContract.UserAction mUserActionsListener;
    private TextView tv_descricao,
            tv_views_count,
            tv_created,
            tv_comment_count,
            tv_title;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_detail);
        mUserActionsListener = new ShotDetailPresenter(this,this);
        iniciarView();
    }

    private void iniciarView(){

    }
}
