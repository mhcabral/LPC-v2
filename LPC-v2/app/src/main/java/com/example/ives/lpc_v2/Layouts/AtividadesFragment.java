package com.example.ives.lpc_v2.Layouts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ives.lpc_v2.Activitys.NovaAtividadeActivity;
import com.example.ives.lpc_v2.R;

import java.util.List;

/**
 * Created by Ives on 15/10/2015.
 */
public class AtividadesFragment extends Fragment
{
    private ListView lstAtividades;
    private Toolbar mtbAtendVisit;
    private List<ListAtividadeItem> itens;
    private ListView.OnItemLongClickListener l;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list_atividades, null);
        final Context context = this.getContext();

        mtbAtendVisit = (Toolbar) view.findViewById(R.id.incTlbAtendVisit);
        mtbAtendVisit.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        mtbAtendVisit.findViewById(R.id.incTlbAtendVisit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), NovaAtividadeActivity.class);
                startActivity(intent);
            }
        });

        lstAtividades = (ListView)view.findViewById(R.id.lstAtividades);

        if(itens != null)
            lstAtividades.setAdapter(new ListAtividadeAdapter(getContext(), itens));

        if(l != null)
            lstAtividades.setOnItemLongClickListener(l);

        return view;
    }

    public void setL(ListView.OnItemLongClickListener l)
    {
        this.l = l;
    }
    public void setListAtividadeItens(List<ListAtividadeItem> itens)
    {
        this.itens = itens;
    }
}
