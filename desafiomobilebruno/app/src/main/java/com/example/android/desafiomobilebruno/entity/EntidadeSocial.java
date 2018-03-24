package com.example.android.desafiomobilebruno.entity;

import java.io.Serializable;

public class EntidadeSocial implements Serializable{

    private int id;
    private String nome;
    private String imagem;
    private String descricao;
    private String site;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSite() {
        return site;
    }

    @Override
    public String toString() {
        return nome;
    }
}
