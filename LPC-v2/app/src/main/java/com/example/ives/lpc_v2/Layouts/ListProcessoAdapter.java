package com.example.ives.lpc_v2.Layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ives.lpc_v2.R;

import java.util.List;

/**
 * Created by Ives on 09/10/2015.
 */
public class ListProcessoAdapter extends BaseAdapter
{

    private Context context;

    private List<ListProcessoItem> list;

    public ListProcessoAdapter(Context context, List<ListProcessoItem> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListProcessoItem item = list.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list_processos, null);

        TextView txtIdentProcess = (TextView)view.findViewById(R.id.txtIdentProcess);
        txtIdentProcess.setText("Processo "+item.getNumeroProcesso());

        TextView txtTipoAtividade = (TextView)view.findViewById(R.id.txtTipoAtividade);
        txtTipoAtividade.setText(item.getTipoAtividade());

        TextView txtDataHora = (TextView)view.findViewById(R.id.txtDataHora);
        txtDataHora.setText(item.getDataHoraAtividade());

        return view;
    }
}
