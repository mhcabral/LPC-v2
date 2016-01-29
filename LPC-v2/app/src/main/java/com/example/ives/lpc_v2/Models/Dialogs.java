package com.example.ives.lpc_v2.Models;

/**
 * Created by mhcabral on 28/12/15.
 */
public class Dialogs {
    private static Dialogs instance;
    private static String interessado_dialog_title;
    private static int tipo_atividade;

    public static void initInstance() {
        if (instance == null) {
            // Create the instance
            instance = new Dialogs();
            interessado_dialog_title = null;
            tipo_atividade = -1;
        }
    }

    public static String getInteressado_dialog_title() {
        return interessado_dialog_title;
    }

    public static void setInteressado_dialog_title(String interessado_dialog_title) {
        Dialogs.interessado_dialog_title = interessado_dialog_title;
    }

    public static int getTipo_atividade() {
        return tipo_atividade;
    }

    public static void setTipo_atividade(int tipo_atividade) {
        Dialogs.tipo_atividade = tipo_atividade;
    }
}
