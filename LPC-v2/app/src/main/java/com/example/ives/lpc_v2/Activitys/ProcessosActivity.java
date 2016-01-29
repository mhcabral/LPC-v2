package com.example.ives.lpc_v2.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Layouts.ListProcessoAdapter;
import com.example.ives.lpc_v2.Layouts.ListProcessoItem;
import com.example.ives.lpc_v2.Models.Atendimento;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Visita;
import com.example.ives.lpc_v2.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProcessosActivity extends AppCompatActivity
{
    private Toolbar mtbProcessos;
    private Drawer drawer;
    private AccountHeader header;
    private ListView lstProcessos;
    private Context context;
    private List<ListProcessoItem> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processos);

        context = this;
        initializeComponents();
    }

    /**
     *  Procedimento que configura valores iniciais
     *  para os componentes da activity
     */
    private void initializeComponents()
    {
        /* Toolbar */
        mtbProcessos = (Toolbar)findViewById(R.id.incTlbProcessos);
        mtbProcessos.setTitle(R.string.title_activity_processos);
        mtbProcessos.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        setSupportActionBar(mtbProcessos);

        /* Header */
        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_background)
                .build();

        /* Drawer */
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mtbProcessos)
                .withDisplayBelowStatusBar(true)
                .withAccountHeader(header)
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem)
                    {
                        switch(i)
                        {
                            case 1:
                                startActivityNovoProcesso();
                                break;
                            case 2:
                                if(context != ProcessosActivity.this) {
                                    startActivityProcessos();
                                }
                                else{
                                    Toast.makeText(ProcessosActivity.this,"Você já está nessa tela", Toast.LENGTH_SHORT);
                                }
                                break;
                            case 3:
                                startActivityLogin();
                                break;
                        }

                        return false;
                    }
                })
                .build();

        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        drawer.getActionBarDrawerToggle().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        drawer.addItem(new PrimaryDrawerItem().withName(R.string.item_drawer_new_process).withIcon(R.drawable.ic_calendar_plus_black_24dp));
        drawer.addItem(new PrimaryDrawerItem().withName(R.string.item_drawer_processes).withIcon(R.drawable.ic_calendar_multiple_black_24dp));
        drawer.addItem(new PrimaryDrawerItem().withName(R.string.item_drawer_exit).withIcon(R.drawable.ic_power_black_24dp));


        /* List Processos */
        lstProcessos = (ListView)findViewById(R.id.lstProcessos);
        itens = new ArrayList<ListProcessoItem>();

        verificaCasoFinalizado();

        if(BD.getList_casos_buscados() != null) {
            for (int i = 0; i < BD.getList_casos_buscados().size(); i++) {
                if(BD.getList_casos_buscados().get(i).getStatus().equals("aberta")) {
                    if (BD.getList_casos_buscados().get(i).getList_atendimentos() != null) {
                        if ((BD.getList_casos_buscados().get(i).getList_atendimentos().size() == 0) && (BD.getList_casos_buscados().get(i).isFlag_inter_breve() == false)) {
                            itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_buscados().get(i).getNumero()), "Atendimento Não Cadastrado", "Horario Não definido"));
                        } else {
                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
                            Atendimento atend = buscaCBAtendimentoAberto(i);
                            Visita visit = buscaCBVisitaAberta(i);
                            if(atend != null) {
                                String date = sdf3.format(atend.getData());
                                itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_buscados().get(i).getNumero()), "Atendimento " + atend.getTipo(), date + " - " + atend.getHorario()));
                            }
                            else if(visit != null){
                                String date = sdf3.format(visit.getData());
                                itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_buscados().get(i).getNumero()),"Visita Domiciliar ",date));
                            }
                            else{
                                itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_buscados().get(i).getNumero()),"Finalizada",""));
                            }
                        }
                    } else {
                        itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_buscados().get(i).getNumero()), "Atendimento Não Cadastrado", "Horario Não definido"));
                    }
                }
                else{
                    itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_buscados().get(i).getNumero()),"Finalizada",""));
                }
            }
        }

        if(BD.getList_casos_salvos() != null) {
            for (int i = 0; i < BD.getList_casos_salvos().size(); i++) {
                if (BD.getList_casos_salvos().get(i).getStatus().equals("aberta")) {
                    if (BD.getList_casos_salvos().get(i).getList_atendimentos() != null) {
                        if ((BD.getList_casos_salvos().get(i).getList_atendimentos().size() == 0) && (BD.getList_casos_salvos().get(i).isFlag_inter_breve() == false)) {
                            itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_salvos().get(i).getNumero()), "Atendimento Não Cadastrado", "Horario Não definido"));

                        } else if ((BD.getList_casos_salvos().get(i).getList_atendimentos().size() == 0) && (BD.getList_casos_salvos().get(i).isFlag_inter_breve() == true)) {
                            itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_salvos().get(i).getNumero()), "Intervencao Breve", ""));

                        } else {
                            SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
                            String date = sdf3.format(BD.getList_casos_salvos().get(i).getList_atendimentos().get(0).getData());
                            itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_salvos().get(i).getNumero()), "Atendimento " + BD.getList_casos_salvos().get(i).getList_atendimentos().get(0).getTipo(), date + " - " + BD.getList_casos_salvos().get(i).getList_atendimentos().get(0).getHorario()));
                        }

                    } else {
                        itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_salvos().get(i).getNumero()), "Atendimento Não Cadastrado", "Horario Não definido"));
                    }
                }
                else{
                    itens.add(new ListProcessoItem(String.valueOf(BD.getList_casos_salvos().get(i).getNumero()),"Finalizada",""));
                }
            }
        }
        /*
        itens.add(new ListProcessoItem("123123123", "Atendimento Infantil", "12/10/2015 - 10:00"));
        itens.add(new ListProcessoItem("434235324", "Atendimento Infantil", "12/10/2015 - 10:00"));
        itens.add(new ListProcessoItem("495749574", "Atendimento Infantil", "12/10/2015 - 10:00"));
        itens.add(new ListProcessoItem("979686824", "Atendimento Infantil", "12/10/2015 - 10:00"));
        */
        lstProcessos.setAdapter(new ListProcessoAdapter(getApplicationContext(), itens));
        lstProcessos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!itens.get(position).getTipoAtividade().equals("Intervencao Breve")) {
                    Intent intent = new Intent(getApplicationContext(), ProcessoActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("Processo", itens.get(position).getNumeroProcesso());
                    startActivity(intent);
                }
            }
        });

    }

    private void startActivityProcessos() {
        Intent intent = new Intent(getApplicationContext(), ProcessosActivity.class);
        startActivity(intent);
    }

    /**
     * Abre activity de Novo Processo
     */
    private void startActivityNovoProcesso()
    {
        Intent intent = new Intent(getApplicationContext(), NovoProcessoActivity.class);
        startActivity(intent);
    }

    private void startActivityLogin()
    {
        salvaArquivo();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(drawer != null)
        {
            drawer.setSelection(-1);
        }
    }

    private void verificaCasoFinalizado(){
        int cont = 0,cont2 = 0;
        for(int i = 0; i < BD.getList_casos_buscados().size();i++){
            for(int j = 0; j < BD.getList_casos_buscados().get(i).getList_atendimentos().size(); j++){
                if(BD.getList_casos_buscados().get(i).getList_atendimentos().get(j).getStatus().equals("aberta")){
                    cont++;
                }
            }
            for(int j = 0; j < BD.getList_casos_buscados().get(i).getList_visitas().size(); j++){
                if(BD.getList_casos_buscados().get(i).getList_visitas().get(j).getStatus().equals("aberta")){
                    cont++;
                }
            }
            if((cont == 0)&&(BD.getList_casos_buscados().get(i).getList_atendimentos().size() != 0)){
                BD.getList_casos_buscados().get(i).setStatus("finalizada");
                cont = 0;
            }
            else{
                BD.getList_casos_buscados().get(i).setStatus("aberta");
            }
            Log.i("ScriptVerificaCaso", BD.getList_casos_buscados().get(i).toString());
        }
        for(int i = 0; i < BD.getList_casos_salvos().size();i++){
            for(int j = 0; j < BD.getList_casos_salvos().get(i).getList_atendimentos().size(); j++){
                if (BD.getList_casos_salvos().get(i).getList_atendimentos().get(j).getStatus().equals("aberta")){
                    cont2++;
                }
            }
            for(int j = 0; j < BD.getList_casos_salvos().get(i).getList_visitas().size(); j++){
                if (BD.getList_casos_salvos().get(i).getList_visitas().get(j).getStatus().equals("aberta")){
                    cont2++;
                }
            }
            if((cont2 == 0)&&(BD.getList_casos_salvos().get(i).getList_atendimentos().size() != 0)){
                BD.getList_casos_salvos().get(i).setStatus("finalizada");
                cont2 = 0;
            }
            else {
                BD.getList_casos_salvos().get(i).setStatus("aberta");
            }
            Log.i("ScriptVerificaCaso",BD.getList_casos_salvos().get(i).toString());
        }
    }

    public Atendimento buscaCBAtendimentoAberto(int position){
        for(int i = 0; i < BD.getList_casos_buscados().get(position).getList_atendimentos().size(); i++){
            if(BD.getList_casos_buscados().get(position).getList_atendimentos().get(i).getStatus().equals("aberta")){
                return BD.getList_casos_buscados().get(position).getList_atendimentos().get(i);
            }
        }
        return null;
    }

    public Visita buscaCBVisitaAberta(int position){
        for(int i = 0; i < BD.getList_casos_buscados().get(position).getList_visitas().size(); i++){
            if(BD.getList_casos_buscados().get(position).getList_visitas().get(i).getStatus().equals("aberta")){
                return BD.getList_casos_buscados().get(position).getList_visitas().get(i);
            }
        }
        return null;
    }

    public void salvaArquivo(){
        Log.i("ScriptSave", "Tentando salvar arquivo");
        try {
            FileOutputStream fileos = new FileOutputStream("/storage/extSdCard/teste/LPC-BD1.txt");
            for (int i = 0; i < BD.getList_casos_buscados().size(); i++) {
                Log.i("ScriptSave",BD.getList_casos_buscados().get(i).toString());
                fileos.write(BD.getList_casos_buscados().get(i).toString().getBytes());
                fileos.write("\n\n".getBytes());
            }
            for (int i = 0; i < BD.getList_casos_salvos().size(); i++) {
                Log.i("ScriptSave",BD.getList_casos_salvos().get(i).toString());
                fileos.write(BD.getList_casos_salvos().get(i).toString().getBytes());
                fileos.write("\n\n".getBytes());
            }
            fileos.close();
            Log.i("ScriptSave", "Arquivo salvo");
            Toast.makeText(this, "Arquivo salvo no SDCard na pasta teste", Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            e.printStackTrace();
            Log.i("ScriptSave","Arquivo não pode ser salvo");
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_casos, menu);
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
