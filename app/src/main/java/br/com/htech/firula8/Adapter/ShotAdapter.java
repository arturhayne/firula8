package br.com.htech.firula8.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.com.htech.firula8.Modelo.Shot;
import br.com.htech.firula8.R;
import br.com.htech.firula8.util.Funcoes;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by arturhayne on 27/01/2018.
 */

public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Shot> mlista;
    private Context c;
    private ShotAdapter.ShotOnClick shotOnClick;

    public ShotAdapter(Context c, List<Shot> mlista,  ShotOnClick shotOnClick) {
        this.mlista = mlista;
        this.c = c;
        this.shotOnClick = shotOnClick;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addListaItem(Shot m, int position) {
        mlista.add(m);
        notifyItemInserted(position);
    }

    @Override
    public ShotAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_shot, parent, false);
        ShotAdapter.MyViewHolder mvh = new ShotAdapter.MyViewHolder(v , viewType);

        return mvh;
    }

    @Override
    public void onBindViewHolder(ShotAdapter.MyViewHolder holder, final int position) {
        Shot shot = mlista.get(position);
        holder.title.setText(shot.getTitle());
        holder.views_count.setText(Integer.toString(shot.getViews_count()));

        holder.created_at.setText(Funcoes.getFormatedDate(shot.getPublished_at()));

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotOnClick.abrirListaShot(position);
            }
        });

        Glide.with(c).
                load(shot.getImages().getNormal()).
                centerCrop().
                crossFade().
                into(holder.shot_image);
    }

    @Override
    public int getItemCount() {
        return mlista.size();
    }

    public Shot getItem(int position){
        return mlista.get(position);
    }

    public interface ShotOnClick{
        void abrirListaShot(Integer position);
    }

    public void replaceData(List<Shot> shots) {
        setList(shots);
        notifyDataSetChanged();
    }

    private void setList(List<Shot> shots) {
        mlista = checkNotNull(shots);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title,created_at,views_count;
        public RelativeLayout ll_item;
        public ImageView shot_image;


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);

            title = itemView.findViewById(R.id.item_shot_title);
            created_at = itemView.findViewById(R.id.item_shot_created_at);
            views_count = itemView.findViewById(R.id.item_shot_views);
            shot_image = itemView.findViewById(R.id.item_shot_image);
            ll_item = itemView.findViewById(R.id.ll_item);

        }
    }

}
