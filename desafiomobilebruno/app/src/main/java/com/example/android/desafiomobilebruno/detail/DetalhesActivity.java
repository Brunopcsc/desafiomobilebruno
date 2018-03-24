package com.example.android.desafiomobilebruno.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.desafiomobilebruno.R;
import com.example.android.desafiomobilebruno.entity.EntidadeSocial;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesActivity extends AppCompatActivity implements DetalhesView{

    @BindView(R.id.imagem_entidade)
    ImageView imagemEntidade;

    @BindView(R.id.descricao_entidade)
    TextView descricaoEntidade;

    @BindView(R.id.link_site)
    TextView siteEntidade;

    DetalhesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //COMANDO INSERIDO REFERENTE AO ERRO 1
        ButterKnife.bind(this);

        //insere opção Up Action na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new DetalhesPresenter(this);

        //confirma se a entidade passada não é nula
        presenter.verificaEntidade((EntidadeSocial) getIntent().getSerializableExtra("entidade_social"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //configura opção Up Action na ActionBar
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void carregaDados(EntidadeSocial entidadeSocial) {
        descricaoEntidade.setText(entidadeSocial.getDescricao());

        siteEntidade.setText(entidadeSocial.getSite());

        Picasso.with(this)
                .load(entidadeSocial.getImagem())
                .fit()
                .centerCrop()
                .into(imagemEntidade);

        //seta o título da ActionBar
        this.setTitle(entidadeSocial.getNome());

    }
}
