package br.com.htech.firula8.ShotDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
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
            tv_comment_count;
    private ImageView image;
    private Shot shot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_detail);
        mUserActionsListener = new ShotDetailPresenter(this,this);
        shot = (Shot) getIntent().getSerializableExtra(MainActivity.SHOT_OBJECT);
        iniciarView();
        if(shot!=null) {
            inserindoValores();
        }
    }

    private void iniciarView(){
        tv_descricao = findViewById(R.id.detail_description);
        tv_comment_count = findViewById(R.id.detail_comments_count);
        tv_views_count = findViewById(R.id.detail_views_count);
        tv_created = findViewById(R.id.detail_created_at);
        image = findViewById(R.id.shot_image);
    }
    private void inserindoValores(){
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


}
