package com.example.ives.lpc_v2.Activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Layouts.AtividadeDialogFragment;
import com.example.ives.lpc_v2.Layouts.AtividadesFragment;
import com.example.ives.lpc_v2.Layouts.CriancaDialogFragment;
import com.example.ives.lpc_v2.Layouts.DadosProcessoFragment;
import com.example.ives.lpc_v2.Layouts.InteressadoDialogFragment;
import com.example.ives.lpc_v2.Layouts.ListAtividadeItem;
import com.example.ives.lpc_v2.Layouts.ListInteressadoItem;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Caso;
import com.example.ives.lpc_v2.Models.Crianca;
import com.example.ives.lpc_v2.Models.Dialogs;
import com.example.ives.lpc_v2.Models.Interessado;
import com.example.ives.lpc_v2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProcessoActivity extends FragmentActivity
{
    private Button btnMoreInformation;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private int flagFrag = 0;
    private List<ListInteressadoItem> interessados;
    private List<ListAtividadeItem> atividades;
    private Toolbar mtbProcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processo);

        initializeComponents(getIntent().getStringExtra("Processo"));

        if(savedInstanceState == null)
        {
            AtividadesFragment atividadesFragment = new AtividadesFragment();
            atividadesFragment.setListAtividadeItens(atividades);
            atividadesFragment.setL(criarDialogAtividade());

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.lnlProcesso, atividadesFragment, "ativ_frag");
            fragmentTransaction.commit();
        }
    }

    public void initializeComponents(String title)
    {
        mtbProcesso = (Toolbar)findViewById(R.id.incTlbProcesso);
        mtbProcesso.setTitle("Processo " + title);
        mtbProcesso.setTitleTextColor(getResources().getColor(R.color.md_white_1000));


        btnMoreInformation = (Button)findViewById(R.id.btnMoreInformations);

        BD.setCaso_selecionado(buscanaslistas(title));
        Log.i("ScriptCasoSelecionado",BD.getCaso_selecionado().toString());
        if(BD.getCaso_selecionado() == null){
            Toast.makeText(this,"Erro: Processo não encontrado",Toast.LENGTH_SHORT);
            finish();
        }

        interessados = new ArrayList<ListInteressadoItem>();
        for(int i = 0; i < BD.getCaso_selecionado().getList_interessado().size();i++){
            ListInteressadoItem listInteressadoItem = new ListInteressadoItem(BD.getCaso_selecionado().getList_interessado().get(i).getNome(),"Interessado");
            interessados.add(listInteressadoItem);
            Log.i("ScriptListInteressado", "Interessado Adicionado");
        }
        if(BD.getCaso_selecionado().getList_crianca() != null) {
            for (int i = 0; i < BD.getCaso_selecionado().getList_crianca().size(); i++) {
                ListInteressadoItem listInteressadoItem = new ListInteressadoItem(BD.getCaso_selecionado().getList_crianca().get(i).getNome(), "Crianca");
                interessados.add(listInteressadoItem);
                Log.i("ScriptListInteressado", "Crianca Adicionada");
            }
        }

        initDialogs();
        atividades = new ArrayList<ListAtividadeItem>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(BD.getCaso_selecionado().getList_atendimentos() != null){
            for(int i = 0; i < BD.getCaso_selecionado().getList_atendimentos().size(); i++){
                ListAtividadeItem listAtividadeItem = new ListAtividadeItem("Atendimento "+BD.getCaso_selecionado().getList_atendimentos().get(i).getTipo(), sdf.format(BD.getCaso_selecionado().getList_atendimentos().get(i).getData())+" - "+BD.getCaso_selecionado().getList_atendimentos().get(i).getHorario(), BD.getCaso_selecionado().getList_atendimentos().get(i).getStatus());
                atividades.add(listAtividadeItem);
            }
        }
        if(BD.getCaso_selecionado().getList_visitas() != null){
            for(int i = 0; i < BD.getCaso_selecionado().getList_visitas().size(); i++){
                ListAtividadeItem listAtividadeItem = new ListAtividadeItem("Visita Domiciliar", sdf.format(BD.getCaso_selecionado().getList_visitas().get(i).getData()),BD.getCaso_selecionado().getList_visitas().get(i).getStatus());
                atividades.add(listAtividadeItem);
            }
        }

        //ListAtividadeItem listAtividadeItem = new ListAtividadeItem("Audiência Lúdica", "20/05/2015", "aberta");
        //ListAtividadeItem listAtividadeItem2 = new ListAtividadeItem("Audiência Pscicossocial", "20/05/2015", "aberta");
        //ListAtividadeItem listAtividadeItem3 = new ListAtividadeItem("Ofício Escolar", "23/05/2015", "aberta");


        //atividades.add(listAtividadeItem2);
        //atividades.add(listAtividadeItem3);
    }

    private Caso buscanaslistas(String title) {
        for (int i = 0; i < BD.getList_casos_buscados().size(); i++){
            if(BD.getList_casos_buscados().get(i).getNumero().equals(title)){
                return BD.getList_casos_buscados().get(i);
            }
        }
        for (int i = 0; i < BD.getList_casos_salvos().size(); i++){
            if(BD.getList_casos_salvos().get(i).getNumero().equals(title)){
                return BD.getList_casos_salvos().get(i);
            }
        }
        return null;
    }

    public void btnMoreInformations_onClicked(View view)
    {
        if(flagFrag == 0)
        {
            DadosProcessoFragment dadosProcessoFragment = new DadosProcessoFragment();
            //dadosProcessoFragment.setTxtAssunto(cb.getAssunto());
            //dadosProcessoFragment.alterDHAudiInicial(cb.getData_inicial());
            //dadosProcessoFragment.alterDHAudiInicial(cb.getData_final());
            dadosProcessoFragment.setListInteressadoItem(interessados);
            dadosProcessoFragment.setL(criarDialogInteressado());

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.lnlProcesso, dadosProcessoFragment, "dados_proce_frag");
            fragmentTransaction.commit();

            flagFrag = 1;
            btnMoreInformation.setText(R.string.btn_menos_informacoes);
        }
        else
        {
            AtividadesFragment atividadesFragment = new AtividadesFragment();
            atividadesFragment.setListAtividadeItens(atividades);
            atividadesFragment.setL(criarDialogAtividade());

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.lnlProcesso, atividadesFragment, "ativ_frag");
            fragmentTransaction.commit();

            flagFrag = 0;
            btnMoreInformation.setText(R.string.btn_more_information);
        }
    }

    public ListView.OnItemLongClickListener criarDialogAtividade()
    {
        return new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                if(atividades.get(position).getTipoAtividade().equals("Visita Domiciliar")){
                    Dialogs.setTipo_atividade(1);
                    for(int i = 0; i < BD.getCaso_selecionado().getList_visitas().size(); i++){
                        if(BD.getCaso_selecionado().getList_visitas().get(i).getStatus().equals("aberta")){
                            BD.setVisita_selecionada(BD.getCaso_selecionado().getList_visitas().get(i));
                            Log.i("ScriptVisita",BD.getVisita_selecionada().toString());
                        }
                    }

                }
                else{
                    Dialogs.setTipo_atividade(0);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    for(int i = 0; i < BD.getCaso_selecionado().getList_atendimentos().size();i++){
                        if((atividades.get(position).getTipoAtividade().equals("Atendimento "+BD.getCaso_selecionado().getList_atendimentos().get(i).getTipo()))&&(atividades.get(position).getDataHoraAtividade().equals(sdf.format(BD.getCaso_selecionado().getList_atendimentos().get(i).getData())+" - "+BD.getCaso_selecionado().getList_atendimentos().get(i).getHorario()))){
                            BD.setAtendimento_selecionado(BD.getCaso_selecionado().getList_atendimentos().get(i));
                            Log.i("ScriptAtendimento", BD.getAtendimento_selecionado().toString());
                        }
                    }
                }

                AtividadeDialogFragment atividadeDialogFragment = new AtividadeDialogFragment();
                atividadeDialogFragment.show(ft, "atividade_dialog");


                return true;
            }
        };

    }

    public ListView.OnItemLongClickListener criarDialogInteressado()
    {
        return new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                ListInteressadoItem item = interessados.get(position);
                Dialogs.setInteressado_dialog_title(item.getNomeInteressado());


                if(item.getTipoInteressado().equals("Crianca"))
                {
                    BD.setCrianca_selecionada(buscanaslistasCrianca(item.getNomeInteressado()));
                    CriancaDialogFragment criancaDialogFragment= new CriancaDialogFragment();
                    criancaDialogFragment.show(ft, "crianca_dialog");
                }
                else
                {
                    BD.setInteressado_selecionado(buscanaslistasInteressado(item.getNomeInteressado()));
                    InteressadoDialogFragment interessadoDialogFragment = new InteressadoDialogFragment();
                    interessadoDialogFragment.show(ft,"interessado_dialog");
                }

                return true;
            }
        };

    }

    private Interessado buscanaslistasInteressado(String title) {
        for(int i = 0;i<BD.getCaso_selecionado().getList_interessado().size();i++){
            if(BD.getCaso_selecionado().getList_interessado().get(i).getNome().equals(title)){
                return BD.getCaso_selecionado().getList_interessado().get(i);
            }
        }
        return null;
    }

    private Crianca buscanaslistasCrianca(String title){
        for(int i = 0;i<BD.getCaso_selecionado().getList_crianca().size();i++){
            if(BD.getCaso_selecionado().getList_crianca().get(i).getNome().equals(title)){
                return BD.getCaso_selecionado().getList_crianca().get(i);
            }
        }
        return null;
    }

    protected void initDialogs()
    {
        // Inicializa o Banco de Dados do Aplicativo
        Dialogs.initInstance();
    }
}
