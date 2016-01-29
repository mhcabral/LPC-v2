package com.example.ives.lpc_v2.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mhcabral on 18/12/15.
 */
public class Caso {

    private String numero;
    private Date data_inicial;
    private String horario_data_inicial;
    private Date data_final;
    private String horario_data_final;
    private String assunto;
    private ArrayList<Interessado> list_interessado;
    private ArrayList<Crianca> list_crianca;
    private ArrayList<Atendimento> list_atendimentos;
    private ArrayList<Visita> list_visitas;
    private boolean flag_inter_breve;
    private String status;

    public Caso(String numero, Date data_inicial,String horario_data_inicial, Date data_final, String horario_data_final, String assunto, ArrayList<Interessado> list_interessado, ArrayList<Crianca> list_crianca) {
        this.numero = numero;
        this.data_inicial = data_inicial;
        this.horario_data_inicial = horario_data_inicial;
        this.data_final = data_final;
        this.horario_data_final = horario_data_final;
        this.assunto = assunto;
        this.list_interessado = list_interessado;
        this.list_crianca = list_crianca;
        this.list_atendimentos = new ArrayList<Atendimento>();
        this.list_visitas = new ArrayList<Visita>();
        this.flag_inter_breve = false;
        this.status = "aberta";
    }

    public Caso(String numero, Date data_inicial,String horario_data_inicial, Date data_final,String horario_data_final, String assunto, ArrayList<Interessado> list_interessado, ArrayList<Crianca> list_crianca, ArrayList<Atendimento> list_atendimentos) {
        this.numero = numero;
        this.data_inicial = data_inicial;
        this.horario_data_inicial = horario_data_inicial;
        this.data_final = data_final;
        this.horario_data_final = horario_data_final;
        this.assunto = assunto;
        this.list_interessado = list_interessado;
        this.list_crianca = list_crianca;
        this.list_atendimentos = list_atendimentos;
        this.list_visitas = new ArrayList<Visita>();
        this.flag_inter_breve = false;
        this.status = "aberta";
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getHorario_data_inicial() {
        return horario_data_inicial;
    }

    public void setHorario_data_inicial(String horario_data_inicial) {
        this.horario_data_inicial = horario_data_inicial;
    }

    public String getHorario_data_final() {
        return horario_data_final;
    }

    public void setHorario_data_final(String horario_data_final) {
        this.horario_data_final = horario_data_final;
    }

    public ArrayList<Interessado> getList_interessado() {
        return list_interessado;
    }

    public void setList_interessado(ArrayList<Interessado> list_interessado) {
        this.list_interessado = list_interessado;
    }

    public void addList_interessado(Interessado interessado){
        this.list_interessado.add(interessado);
    }

    public ArrayList<Crianca> getList_crianca() {
        return list_crianca;
    }

    public void setList_crianca(ArrayList<Crianca> list_crianca) {
        this.list_crianca = list_crianca;
    }

    public void addList_crianca(Crianca crianca){
        this.list_crianca.add(crianca);
    }

    public ArrayList<Visita> getList_visitas() {
        return list_visitas;
    }

    public void setList_visitas(ArrayList<Visita> list_visitas) {
        this.list_visitas = list_visitas;
    }

    public void addList_visitas(Visita visita){
        this.list_visitas.add(visita);
    }

    public ArrayList<Atendimento> getList_atendimentos() {
        return list_atendimentos;
    }

    public void setList_atendimentos(ArrayList<Atendimento> list_atendimentos) {
        this.list_atendimentos = list_atendimentos;
    }

    public void addList_atendimentos(Atendimento atendimento){
        this.list_atendimentos.add(atendimento);
    }

    public boolean isFlag_inter_breve() {
        return flag_inter_breve;
    }

    public void setFlag_inter_breve(boolean flag_inter_breve) {
        this.flag_inter_breve = flag_inter_breve;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Caso{" +
                "numero='" + numero + '\'' +
                ", data_inicial=" + sdf.format(data_inicial) +
                ", horario_data_inicial='" + horario_data_inicial + '\'' +
                ", data_final=" + sdf.format(data_final) +
                ", horario_data_final='" + horario_data_final + '\'' +
                ", assunto='" + assunto + '\'' +
                ", list_interessado=" + list_interessado +
                ", list_crianca=" + list_crianca +
                ", list_atendimentos=" + list_atendimentos +
                ", list_visitas=" + list_visitas +
                ", flag_inter_breve=" + flag_inter_breve +
                ", status='" + status + '\'' +
                '}';
    }
}
