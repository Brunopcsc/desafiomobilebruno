package com.example.android.desafiomobilebruno.entidades_sociais;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.android.desafiomobilebruno.R;
import com.example.android.desafiomobilebruno.detail.DetalhesActivity;
import com.example.android.desafiomobilebruno.entity.EntidadeSocial;
import com.example.android.desafiomobilebruno.entity.EntidadeSocialList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntidadesSociaisActivity extends AppCompatActivity implements EntidadesSociaisView {

    @BindView(R.id.lista_entidades)
    RecyclerView recyclerView;

    EntidadesSociaisPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new EntidadesSociaisPresenter(this);


        //Verifica se há entidades para serem exibidas
        presenter.getEntidadesSociais((EntidadeSocialList) getIntent().getSerializableExtra("entidade_social_list"));

    }

    @Override
    public void atualizaLista(final List<EntidadeSocial> list) {
        EntidadeSocialAdapter adapter = new EntidadeSocialAdapter(this, list);

        //Seta o clique para abrir a tela de detalhes
        adapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClickItem(View view, int position) {
                Intent intent = new Intent(EntidadesSociaisActivity.this, DetalhesActivity.class);
                intent.putExtra("entidade_social", list.get(position));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        // CÓDIGOS MOVIDOS REFERENTES AO ERRO 2
        //Configura RecyclerView
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void mostraMsg(String msg) {
        Toast.makeText(EntidadesSociaisActivity.this, msg, Toast.LENGTH_LONG).show();
    }

}
