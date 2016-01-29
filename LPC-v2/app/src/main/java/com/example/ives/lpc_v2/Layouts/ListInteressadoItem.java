package com.example.ives.lpc_v2.Layouts;

/**
 * Created by Ives on 09/10/2015.
 */
public class ListInteressadoItem
{
    private String nomeInteressado;
    private String tipoInteressado;

    public ListInteressadoItem(String nomeInteressado, String tipoInteressado)
    {
        this.nomeInteressado = nomeInteressado;
        this.tipoInteressado = tipoInteressado;
    }

    public String getNomeInteressado() {
        return nomeInteressado;
    }

    public String getTipoInteressado() {
        return tipoInteressado;
    }
}
