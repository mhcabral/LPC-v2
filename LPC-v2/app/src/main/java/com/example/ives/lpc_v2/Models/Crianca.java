package com.example.ives.lpc_v2.Models;

import java.util.Date;

/**
 * Created by mhcabral on 18/12/15.
 */
public class Crianca {

    private String nome;
    private Date data_nascimento;
    private String nome_escola_crianca;
    private String serie_da_crianca;

    public Crianca(String nome) {
        this.nome = nome;
        this.data_nascimento = null;
        this.nome_escola_crianca = null;
        this.serie_da_crianca = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getNome_escola_crianca() {
        return nome_escola_crianca;
    }

    public void setNome_escola_crianca(String nome_escola_crianca) {
        this.nome_escola_crianca = nome_escola_crianca;
    }

    public String getSerie_da_crianca() {
        return serie_da_crianca;
    }

    public void setSerie_da_crianca(String serie_da_crianca) {
        this.serie_da_crianca = serie_da_crianca;
    }

    @Override
    public String toString() {
        return "Crianca{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public String toString2() {
        return "Crianca{" +
                "nome='" + nome + '\'' +
                ", data_nascimento=" + data_nascimento +
                ", nome_escola_crianca='" + nome_escola_crianca + '\'' +
                ", serie_da_crianca='" + serie_da_crianca + '\'' +
                '}';
    }
}
