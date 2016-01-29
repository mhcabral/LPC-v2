package com.example.ives.lpc_v2.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.ives.lpc_v2.Layouts.BtnRemoveOnClickedListener;
import com.example.ives.lpc_v2.Layouts.ListInteressadoAdapter;
import com.example.ives.lpc_v2.Layouts.ListInteressadoItem;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Crianca;
import com.example.ives.lpc_v2.Models.Interessado;
import com.example.ives.lpc_v2.R;

import java.util.ArrayList;
import java.util.List;

public class NovoProcessoActivity extends AppCompatActivity
{

    private Toolbar mtbNovoCaso;
    private Toolbar mtbAgendar;
    private Spinner spnAssunto;
    private EditText edtNumero;
    private EditText edtInteressado;
    private RadioButton rdbInteressado;
    private RadioButton rdbCrianca;
    private RadioGroup rdg;
    private List<ListInteressadoItem> itens;
    private ListView lstInteressado;
    private List<BtnRemoveOnClickedListener> itens_l;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_processo);

        initializeComponents();
    }

    /**
     *  Procedimento que configura valores iniciais
     *  para os componentes da activity
     */
    private void initializeComponents()
    {
        /**/
        itens = new ArrayList<ListInteressadoItem>();
        itens_l = new ArrayList<BtnRemoveOnClickedListener>();
        lstInteressado = (ListView)findViewById(R.id.lstInteressados);

        /* Radio Group */
        rdg = (RadioGroup)findViewById(R.id.rdg);
        rdbCrianca = (RadioButton)findViewById(R.id.rdbCrianca);
        rdbInteressado = (RadioButton)findViewById(R.id.rdbInteressado);

        /* Toolbar */
        mtbNovoCaso = (Toolbar)findViewById(R.id.incTlbNovoProcesso);
        mtbNovoCaso.setTitle(R.string.title_activity_novo_processo);
        mtbNovoCaso.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        setSupportActionBar(mtbNovoCaso);

        mtbAgendar = (Toolbar)findViewById(R.id.incTlbAgendar);
        mtbAgendar.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        mtbAgendar.findViewById(R.id.imgAgendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itens.size()!= 0){
                    for(int i=0;i < itens.size();i++){
                        if(itens.get(i).getTipoInteressado().toLowerCase().equals("interessado")) {
                            Interessado interessado = new Interessado(itens.get(i).getNomeInteressado());
                            BD.addList_interessados(interessado);
                        }
                        else {
                            Crianca crianca = new Crianca(itens.get(i).getNomeInteressado());
                            BD.addList_criancas(crianca);
                        }
                    }
                    BD.setAssunto(spnAssunto.getSelectedItem().toString());
                    BD.setNumero(edtNumero.getText().toString());
                }
                Intent intent = new Intent(getApplicationContext(), AgendarActivity.class);
                startActivity(intent);
            }
        });

        /* Spinner Assunto */
        spnAssunto = (Spinner)findViewById(R.id.spnAssunto);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itens_assunto, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAssunto.setAdapter(adapter);
        //spnAssunto.setSelection(-1);

        /* Edit Add */
        edtInteressado = (EditText)findViewById(R.id.edtInteressado);

        /* Edit Numero */
        edtNumero = (EditText)findViewById(R.id.edtNumProcesso);

        // Implementa método de mudança de focus;
        // Ao receber o focus, automaticamente seleciona o radioButton de Interessado
        edtInteressado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    rdbInteressado.setChecked(true);
            }
        });

        //Implementa o "botão" de add interessado no edit
        edtInteressado.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edtInteressado.getRight() - edtInteressado.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()))
                    {
                        if(!edtInteressado.getText().toString().equals(""))
                        {
                            String tipo = (rdbCrianca.isChecked())?"Crianca":"Interessado";

                            addInteressado(edtInteressado.getText().toString(), tipo);
                        }
                    }
                }

                return false;
            }
        });
    }

    /**
     *
     * @param interessado
     * @param tipoInteressado
     */
    public void addInteressado(final String interessado, final String tipoInteressado)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                itens.add(0, new ListInteressadoItem(interessado, tipoInteressado));
                itens_l.add(new BtnRemoveOnClickedListener(0) {
                    @Override
                    public void onClick(View v) {
                        removeInteressado(getPosition());
                    }
                });

                updateListenner();
                lstInteressado.setAdapter(new ListInteressadoAdapter(getApplicationContext(), itens, itens_l));
                edtInteressado.setText("");
            }
        });
    }

    public void removeInteressado(final int position)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                itens.remove(position);
                updateListenner();
                lstInteressado.setAdapter(new ListInteressadoAdapter(getApplicationContext(), itens, itens_l));
            }
        });
    }

    private void updateListenner()
    {
        for(int i = 0; i < itens_l.size(); i++)
        {
            itens_l.get(i).setPosition(i);
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novo_caso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
