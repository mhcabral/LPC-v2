package com.example.ives.lpc_v2.Layouts;

/**
 * Created by Ives on 15/10/2015.
 */
public class ListAtividadeItem
{
    private String tipoAtividade;
    private String dataHoraAtividade;
    private String statusAtividade;

    public ListAtividadeItem(String tipoAtividade, String dataHoraAtividade, String statusAtividade)
    {
        this.tipoAtividade = tipoAtividade;
        this.dataHoraAtividade = dataHoraAtividade;
        this.statusAtividade = statusAtividade;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public String getDataHoraAtividade() {
        return dataHoraAtividade;
    }

    public String getStatusAtividade() {
        return statusAtividade;
    }
}
