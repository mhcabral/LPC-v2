package com.example.ives.lpc_v2.Models;

import java.util.Date;

/**
 * Created by mhcabral on 18/12/15.
 */
public class Atendimento {
    private String tipo;
    private Date data;
    private String horario;
    private String tempo;
    private String status;
    private Interessado interessado;
    private Crianca crianca;

    public Atendimento(String tipo, Date data, String horario, String tempo,Interessado interessado) {
        this.tipo = tipo;
        this.data = data;
        this.horario = horario;
        this.tempo = tempo;
        this.status = "aberta";
        this.interessado = interessado;
        this.crianca = null;
    }

    public Atendimento(String tipo, Date data, String horario, String tempo, Crianca crianca) {
        this.tipo = tipo;
        this.data = data;
        this.horario = horario;
        this.tempo = tempo;
        this.status = "aberta";
        this.crianca = crianca;
        this.interessado = null;
    }

    public Atendimento(String tipo, Date data, String horario, String tempo) {
        this.tipo = tipo;
        this.data = data;
        this.horario = horario;
        this.tempo = tempo;
        this.status = "aberta";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Interessado getInteressado() {
        return interessado;
    }

    public void setInteressado(Interessado interessado) {
        this.interessado = interessado;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "tipo='" + tipo + '\'' +
                ", data=" + data +
                ", horario='" + horario + '\'' +
                ", tempo='" + tempo + '\'' +
                ", status='" + status + '\'' +
                ", interessado=" + interessado +
                '}';
    }
}
