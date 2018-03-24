package com.example.android.desafiomobilebruno.splash_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.android.desafiomobilebruno.R;
import com.example.android.desafiomobilebruno.entity.EntidadeSocialList;
import com.example.android.desafiomobilebruno.entidades_sociais.EntidadesSociaisActivity;
import com.example.android.desafiomobilebruno.network.api.AppApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String ENTIDADES_SOCIAIS = "entidades_sociais";
    private static final String ENTIDADES_SOCIAIS_JSON = "entidades_sociais_json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //gera o atraso de 2 segundos para exibição da splash screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Utiliza o Retrofit para conectar-se ao servidor web
                //Caso haja dados eles são salvos e a lista é exibida
                //Caso contrário é verificado se há dados armazenados no dispostivo
                AppApi api = AppApi.getInstance();

                api.getSociais().enqueue(new Callback<EntidadeSocialList>() {
                    @Override
                    public void onResponse(Call<EntidadeSocialList> call, Response<EntidadeSocialList> response) {
                        EntidadeSocialList list = response.body();

                        if(list != null && list.getEntidadeSocialList() != null) {
                            salvaDadosOffline(new Gson().toJson(list));
                            exibeEntidadesSociais(list);
                        }else {
                            trabalhaOffline();
                        }
                    }

                    @Override
                    public void onFailure(Call<EntidadeSocialList> call, Throwable t) {
                        trabalhaOffline();
                        Log.e("network", t.toString());

                    }
                });
            }
        }, 2000);

    }

    //cada vez que os dados forem baixados corretamente eles são salvos em formatado json para acesso offline
    public void salvaDadosOffline(String jsonEntidadesSociais) {
        SharedPreferences.Editor editor =
                getSharedPreferences(ENTIDADES_SOCIAIS, MODE_PRIVATE).edit();

        editor.putString(ENTIDADES_SOCIAIS_JSON, jsonEntidadesSociais);
        editor.apply();

    }

    //exibe as entidades
    public void exibeEntidadesSociais(EntidadeSocialList entidadeSocialList) {
        Intent abreEntidadesSociais = new Intent(SplashScreenActivity.this, EntidadesSociaisActivity.class);
        abreEntidadesSociais.putExtra("entidade_social_list", entidadeSocialList);
        startActivity(abreEntidadesSociais);
        //COMANDO INSERIDO REFERENTE AO ERRO 3
        finish();
    }

    //caso haja falha no acesso aos dados do sercidor verifica se há informações salvas offline
    public void trabalhaOffline() {
        Toast.makeText(SplashScreenActivity.this, "Trabalhando offline", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences =
                getSharedPreferences(ENTIDADES_SOCIAIS, MODE_PRIVATE);

        String jsonEntidadesSociais = sharedPreferences.getString(ENTIDADES_SOCIAIS_JSON, null);

        if(jsonEntidadesSociais != null && !jsonEntidadesSociais.equals("")){
            exibeEntidadesSociais(new Gson().fromJson(jsonEntidadesSociais, EntidadeSocialList.class));
        }else {
            exibeEntidadesSociais(null);
        }

    }
}
