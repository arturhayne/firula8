package br.com.htech.firula8.ShotDetail;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.htech.firula8.Main.MainActivity;
import br.com.htech.firula8.Modelo.Shot;
import br.com.htech.firula8.R;

public class ShotDetailActivity extends AppCompatActivity implements ShotDetailContract.View{

    private ShotDetailContract.UserAction mUserActionsListener;
    private TextView tv_descricao,
            tv_views_count,
            tv_created,
            tv_comment_count,
            tv_empty;
    private ImageView image;
    private ProgressBar progress;
    private CoordinatorLayout coord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_detail);
        mUserActionsListener = new ShotDetailPresenter(this,this);
        iniciarView();
        Shot shot = (Shot) getIntent().getSerializableExtra(MainActivity.SHOT_OBJECT);

        if(shot!=null) {
           // inserindoValores(shot);
            mUserActionsListener.loadShot(Long.toString(shot.getId()));
        }
    }

    private void iniciarView(){
        tv_descricao = findViewById(R.id.detail_description);
        tv_comment_count = findViewById(R.id.detail_comments_count);
        tv_views_count = findViewById(R.id.detail_views_count);
        tv_created = findViewById(R.id.detail_created_at);
        image = findViewById(R.id.shot_image);
        progress = findViewById(R.id.progress);
        coord = findViewById(R.id.coord);
        tv_empty = findViewById(R.id.empty);


    }
    private void inserindoValores(Shot shot){
        setTitle(shot.getTitle());
        tv_descricao.setText(shot.getDescription().replace("<p>","").replace("</p>",""));
        tv_created.setText(shot.getPublished_at().toString());
        tv_comment_count.setText(Integer.toString(shot.getComments_count()));
        tv_views_count.setText(Integer.toString(shot.getViews_count()));

        Glide.with(this).
                load(shot.getImages().getNormal()).
                centerCrop().
                crossFade().
                into(image);
    }

    @Override
    public void showProgressBar(Boolean show) {
        if (show){
            progress.setVisibility(View.VISIBLE);
            coord.setVisibility(View.INVISIBLE);
        }else {
            progress.setVisibility(View.INVISIBLE);
            coord.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showEmpty(boolean show) {
        if (show){
            tv_empty.setVisibility(View.VISIBLE);
            progress.setVisibility(View.INVISIBLE);
            coord.setVisibility(View.INVISIBLE);
        }else {
            tv_empty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showShot(Shot shot) {
        inserindoValores(shot);
    }


}
