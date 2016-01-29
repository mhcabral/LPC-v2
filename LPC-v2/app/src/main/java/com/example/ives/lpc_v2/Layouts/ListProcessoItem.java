package com.example.ives.lpc_v2.Layouts;

/**
 * Created by Ives on 09/10/2015.
 */
public class ListProcessoItem
{
    private String numeroProcesso;
    private String tipoAtividade;
    private String dataHoraAtividade;

    public ListProcessoItem(String numeroProcesso, String tipoAtividade, String dataHoraAtividade)
    {
        this.dataHoraAtividade = dataHoraAtividade;
        this.numeroProcesso = numeroProcesso;
        this.tipoAtividade = tipoAtividade;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public String getDataHoraAtividade() {
        return dataHoraAtividade;
    }
}
