package com.example.ives.lpc_v2.Layouts;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ives.lpc_v2.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ives on 09/10/2015.
 */
public class ListInteressadoAdapter extends BaseAdapter
{

    private Context context;
    private List<ListInteressadoItem> list;
    private List<BtnRemoveOnClickedListener> itens_l;
    private boolean flag_visualizacao = false;

    public ListInteressadoAdapter(Context context, List<ListInteressadoItem> list, List<BtnRemoveOnClickedListener> itens_l)
    {
        this.context = context;
        this.list = list;
        this.itens_l = itens_l;
    }

    public ListInteressadoAdapter(Context context, List<ListInteressadoItem> list)
    {
        this(context, list, null);
        flag_visualizacao = true;
    }

    @Override
    public int getCount()
    {
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ListInteressadoItem item = list.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list_interessados, null);

        TextView txtNomeInteressado = (TextView)view.findViewById(R.id.txtNomeInteressado);
        txtNomeInteressado.setText(item.getNomeInteressado());

        TextView txtTipoInteressado = (TextView)view.findViewById(R.id.txtTipoInteressado);
        txtTipoInteressado.setText(item.getTipoInteressado());

        Button btnRemove = (Button) view.findViewById(R.id.btnRemove);
        if(!flag_visualizacao)
        {
            btnRemove.setOnClickListener(itens_l.get(position));
        }
        else
        {
            btnRemove.setVisibility(View.INVISIBLE);
        }

        return view;

    }
}
