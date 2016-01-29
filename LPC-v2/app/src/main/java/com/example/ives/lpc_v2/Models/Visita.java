package com.example.ives.lpc_v2.Models;

import java.util.Date;

/**
 * Created by mhcabral on 18/12/15.
 */
public class Visita {

    private String descricao;
    private String local;
    private Date data;
    private String status;

    public Visita(Date data, String local) {
        this.descricao = "Visita domiciliar";
        this.local = local;
        this.data = data;
        this.status = "aberta";
    }

    public Visita(Date data, String local, String descricao) {
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.status = "aberta";
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Visita{" +
                "descricao='" + descricao + '\'' +
                ", local='" + local + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
