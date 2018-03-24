package com.example.android.desafiomobilebruno.network.api;

import com.example.android.desafiomobilebruno.entity.EntidadeSocialList;
import com.example.android.desafiomobilebruno.network.service.AppService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApi {

    private static AppApi instance;
    private AppService appService;

    //garante uma única instância de conexão com a web
    public static AppApi getInstance(){
        if(instance == null){
            instance = new AppApi();
        }

        return instance;
    }

    //configura o retrofit
    private AppApi(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://dl.dropboxusercontent.com/s/f39meuzbspdlrhl/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.appService = retrofit.create(AppService.class);

    }

    //configura o Gson para conversão de arquivos json
    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public Call<EntidadeSocialList> getSociais(){
        return appService.getSociais();
    }

}
