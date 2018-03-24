package com.example.android.desafiomobilebruno.network.service;

import com.example.android.desafiomobilebruno.entity.EntidadeSocialList;

import retrofit2.Call;
import retrofit2.http.GET;

//Interface para o retrofit
public interface AppService {
    @GET("sociais.json")
    Call<EntidadeSocialList> getSociais();

}
