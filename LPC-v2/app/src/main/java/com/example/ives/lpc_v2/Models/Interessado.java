package com.example.ives.lpc_v2.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mhcabral on 15/12/15.
 */
public class Interessado {

    private String nome;
    private Date data_nascimento;
    private String endereco;
    private String telefone;

    public Interessado(String nome) {
        this.nome = nome;
        this.data_nascimento = null;
        this.endereco = null;
        this.telefone = null;

    }

    public Interessado(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.data_nascimento = null;
        this.telefone = null;
    }

    public Interessado(String nome, String endereco, String telefone,Date data_nascimento) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Interessado{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public String toString2() {
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf4.format(data_nascimento);
        return "Interessado{" +
                "nome='" + nome + '\'' +
                ", data_nascimento='" + date + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
