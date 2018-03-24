package com.example.android.desafiomobilebruno.entidades_sociais;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.desafiomobilebruno.R;
import com.example.android.desafiomobilebruno.entity.EntidadeSocial;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntidadeSocialAdapter extends RecyclerView.Adapter<EntidadeSocialAdapter.ViewHolder>{

    private OnRecyclerViewSelected onRecyclerViewSelected;

    private Context context;
    private List<EntidadeSocial> entidadeSocialList;

    EntidadeSocialAdapter(Context context, List<EntidadeSocial> entidadeSocialList) {
        this.context = context;
        this.entidadeSocialList = entidadeSocialList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entidade_social_item_lista, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EntidadeSocial entidadeSocial = entidadeSocialList.get(position);
        holder.tvNomeEntidade.setText(entidadeSocial.getNome());
        Picasso.with(context)
                .load(entidadeSocial.getImagem())
                .centerCrop()
                .fit()
                .into(holder.ivImagemEntidade);

    }

    @Override
    public int getItemCount() {
        return entidadeSocialList.size();
    }

    void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nome_entidade)
        TextView tvNomeEntidade;
        @BindView(R.id.img_entidade)
        ImageView ivImagemEntidade;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void itemClicado(View view){
            onRecyclerViewSelected.onClickItem(view, getAdapterPosition());
        }

        @OnClick(R.id.site_entidade)
        void siteClicado(View view){
            EntidadeSocial entidadeSocial = entidadeSocialList.get(getAdapterPosition());
            Intent abreSite = new Intent(Intent.ACTION_VIEW);
            abreSite.setData(Uri.parse(entidadeSocial.getSite()));
            context.startActivity(abreSite);
        }

    }
}
