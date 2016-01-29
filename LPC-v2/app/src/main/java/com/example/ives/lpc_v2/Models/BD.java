package com.example.ives.lpc_v2.Models;

import java.util.ArrayList;

/**
 * Created by mhcabral on 18/12/15.
 */
public class BD {

    private static BD instance;

    private static String numero;
    private static String assunto;
    private static ArrayList<Interessado> list_interessados;
    private static ArrayList<Crianca> list_criancas;
    private static ArrayList<Caso> list_casos_salvos;
    private static ArrayList<Caso> list_casos_buscados;
    private static Caso caso_selecionado;
    private static Interessado interessado_selecionado;
    private static Crianca crianca_selecionada;
    private static Atendimento atendimento_selecionado;
    private static Visita visita_selecionada;
    private static boolean cria_ex;

    public static void initInstance() {
        if (instance == null) {
            // Create the instance
            instance = new BD();
            list_interessados = new ArrayList<Interessado>();
            list_criancas = new ArrayList<Crianca>();
            list_casos_salvos = new ArrayList<Caso>();
            list_casos_buscados = new ArrayList<Caso>();
            cria_ex = false;
            caso_selecionado = null;
            interessado_selecionado = null;
            crianca_selecionada = null;
            atendimento_selecionado = null;
            visita_selecionada = null;
        }
    }

    public static BD getInstance() {
        return instance;
    }

    public static ArrayList<Interessado> getList_interessados() {
        return list_interessados;
    }

    public static void setList_interessados(ArrayList<Interessado> list_interessados) {
        BD.list_interessados = list_interessados;
    }

    public static void addList_interessados(Interessado interessado){
        BD.list_interessados.add(interessado);
    }

    public static ArrayList<Crianca> getList_criancas() {
        return list_criancas;
    }

    public static void setList_criancas(ArrayList<Crianca> list_criancas) {
        BD.list_criancas = list_criancas;
    }

    public static void addList_criancas(Crianca crianca){
        BD.list_criancas.add(crianca);
    }

    public static String getNumero() {
        return numero;
    }

    public static void setNumero(String numero) {
        BD.numero = numero;
    }

    public static String getAssunto() {
        return assunto;
    }

    public static void setAssunto(String assunto) {
        BD.assunto = assunto;
    }

    public static ArrayList<Caso> getList_casos_salvos() {
        return list_casos_salvos;
    }

    public static void setList_casos_salvos(ArrayList<Caso> list_casos_salvos) {
        BD.list_casos_salvos = list_casos_salvos;
    }

    public static void addList_casos_salvos(Caso caso){
        BD.list_casos_salvos.add(caso);
    }

    public static ArrayList<Caso> getList_casos_buscados() {
        return list_casos_buscados;
    }

    public static void setList_casos_buscados(ArrayList<Caso> list_casos_buscados) {
        BD.list_casos_buscados = list_casos_buscados;
    }

    public static void addList_casos_buscados(Caso caso) {
        BD.list_casos_buscados.add(caso);
    }

    public static void limpaList_Interessados_Criancas(){
        BD.list_interessados = new ArrayList<Interessado>();
        BD.list_criancas = new ArrayList<Crianca>();
    }

    public static Caso getCaso_selecionado() {
        return caso_selecionado;
    }

    public static void setCaso_selecionado(Caso caso_selecionado) {
        BD.caso_selecionado = caso_selecionado;
    }

    public static void limpaCaso_selecionado(){
        BD.caso_selecionado = null;
    }

    public static Interessado getInteressado_selecionado() {
        return interessado_selecionado;
    }

    public static void setInteressado_selecionado(Interessado interessado_selecionado) {
        BD.interessado_selecionado = interessado_selecionado;
    }

    public static void limpaInteressado_selecionado(){
        BD.interessado_selecionado = null;
    }

    public static Crianca getCrianca_selecionada() {
        return crianca_selecionada;
    }

    public static void setCrianca_selecionada(Crianca crianca_selecionada) {
        BD.crianca_selecionada = crianca_selecionada;
    }

    public static void limpaCrianca_selecionada(){
        BD.crianca_selecionada = null;
    }

    public static Atendimento getAtendimento_selecionado() {
        return atendimento_selecionado;
    }

    public static void setAtendimento_selecionado(Atendimento atendimento_selecionado) {
        BD.atendimento_selecionado = atendimento_selecionado;
    }

    public static void limpaAtendimento_selecionado(){
        BD.atendimento_selecionado = null;
    }

    public static Visita getVisita_selecionada() {
        return visita_selecionada;
    }

    public static void setVisita_selecionada(Visita visita_selecionada) {
        BD.visita_selecionada = visita_selecionada;
    }

    public static void limpaVisita_selecionada() {
        BD.visita_selecionada = null;
    }

    public static boolean isCria_ex() {
        return cria_ex;
    }

    public static void setCria_ex(boolean cria_ex) {
        BD.cria_ex = cria_ex;
    }
}
