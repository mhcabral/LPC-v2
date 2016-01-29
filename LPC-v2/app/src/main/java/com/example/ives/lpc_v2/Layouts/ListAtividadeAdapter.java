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
 * Created by Ives on 15/10/2015.
 */
public class ListAtividadeAdapter extends BaseAdapter
{
    private Context context;
    private List<ListAtividadeItem> itens;

    public ListAtividadeAdapter(Context context, List<ListAtividadeItem> itens)
    {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public int getCount()
    {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListAtividadeItem item = itens.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list_atividades, null);

        TextView txtIdentAtividade = (TextView)view.findViewById(R.id.txtIdentAtividade);
        txtIdentAtividade.setText(item.getTipoAtividade());

        TextView txtDataHoraAtividade = (TextView)view.findViewById(R.id.txtDataHoraAtividade);
        txtDataHoraAtividade.setText(item.getDataHoraAtividade());

        TextView txtStatusAtividade = (TextView)view.findViewById(R.id.txtStatusAtividade);
        txtStatusAtividade.setText(item.getStatusAtividade());

        return view;
    }
}
