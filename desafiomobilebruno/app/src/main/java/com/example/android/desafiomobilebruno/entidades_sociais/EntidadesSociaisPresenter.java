package com.example.android.desafiomobilebruno.entidades_sociais;

import com.example.android.desafiomobilebruno.entity.EntidadeSocialList;

class EntidadesSociaisPresenter {

    private EntidadesSociaisView view;

    EntidadesSociaisPresenter(EntidadesSociaisView view) {
        this.view = view;
    }

    void getEntidadesSociais(EntidadeSocialList entidadeSocialList){
        if(null != entidadeSocialList)
            view.atualizaLista(entidadeSocialList.getEntidadeSocialList());
        else
            view.mostraMsg("Não há dados a serem exibidos. \n Verifique sua conexão com a internet.");

    }

}
