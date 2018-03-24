package com.example.android.desafiomobilebruno.entidades_sociais;

import com.example.android.desafiomobilebruno.entity.EntidadeSocial;

import java.util.List;

public interface EntidadesSociaisView {
    void atualizaLista(List<EntidadeSocial> list);
    void mostraMsg(String msg);
}
