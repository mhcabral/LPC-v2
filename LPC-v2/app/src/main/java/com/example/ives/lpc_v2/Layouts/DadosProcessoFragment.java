package com.example.ives.lpc_v2.Layouts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ives on 15/10/2015.
 */
public class DadosProcessoFragment extends Fragment
{
    private TextView txtAssunto;
    private TextView txtDHAudieInicial;
    private TextView txtDHAundieFinal;
    private ListView lstInteressado;
    private List<ListInteressadoItem> itens;
    private ListView.OnItemLongClickListener l;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragments_data_processo, null);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        txtAssunto = (TextView)view.findViewById(R.id.txtAssunto);
        txtAssunto.setText("Assunto: "+BD.getCaso_selecionado().getAssunto());
        txtDHAudieInicial = (TextView)view.findViewById(R.id.txtDHAudiInicial);
        txtDHAudieInicial.setText("Data e Horario da Audiencia Inicial: "+sdf.format(BD.getCaso_selecionado().getData_inicial())+" "+BD.getCaso_selecionado().getHorario_data_inicial());
        txtDHAundieFinal = (TextView)view.findViewById(R.id.txtDHAudieFinal);
        txtDHAundieFinal.setText("Data e Horario da Audiencia Final: "+sdf.format(BD.getCaso_selecionado().getData_final())+" "+BD.getCaso_selecionado().getHorario_data_final());
        lstInteressado = (ListView)view.findViewById(R.id.lstInteressado);

        if(itens != null)
            lstInteressado.setAdapter(new ListInteressadoAdapter(getContext(), itens));

        if(l != null)
            lstInteressado.setOnItemLongClickListener(l);

        return view;
    }

    public void alterAssunto(String assunto)
    {
        //if(txtAssunto == null)
        //    txtAssunto = (TextView)getView().findViewById(R.id.txtAssunto);

        txtAssunto.setText(assunto);
    }

    public void alterDHAudiInicial(String dhAudiInicial)
    {
        //if(txtDHAudieInicial == null)
        //    txtDHAudieInicial = (TextView)getView().findViewById(R.id.txtDHAudiInicial);

        txtDHAudieInicial.setText(dhAudiInicial);
    }

    public void alterDHAudieFinal(String dhAudieFinal)
    {
        //if(txtDHAundieFinal == null)
        //    txtDHAundieFinal = (TextView)getView().findViewById(R.id.txtDHAudieFinal);

        txtDHAundieFinal.setText(dhAudieFinal);
    }

    public void setTxtAssunto(String assunto){

    }
    public void setL(ListView.OnItemLongClickListener l)
    {
        this.l = l;
    }
    public void setListInteressadoItem(List<ListInteressadoItem> itens)
    {
        this.itens = itens;
    }
}
