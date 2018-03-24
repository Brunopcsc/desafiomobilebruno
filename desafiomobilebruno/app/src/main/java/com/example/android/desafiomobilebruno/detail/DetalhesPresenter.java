package com.example.android.desafiomobilebruno.detail;

import com.example.android.desafiomobilebruno.entity.EntidadeSocial;

public class DetalhesPresenter {

    DetalhesView view;

    public DetalhesPresenter(DetalhesView view) {
        this.view = view;
    }

    void verificaEntidade(EntidadeSocial entidadeSocial){
        if(null != entidadeSocial)
            view.carregaDados(entidadeSocial);
    }


}
