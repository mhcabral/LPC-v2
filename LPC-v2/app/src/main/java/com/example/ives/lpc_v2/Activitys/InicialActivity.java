package com.example.ives.lpc_v2.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Models.Atendimento;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Caso;
import com.example.ives.lpc_v2.Models.Crianca;
import com.example.ives.lpc_v2.Models.Interessado;
import com.example.ives.lpc_v2.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InicialActivity extends AppCompatActivity {

    private Toolbar mtbInicial;
    private Drawer drawer;
    private AccountHeader header;
    private ListView lstProcessos;
    private String line1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        initializeComponents();
    }

    /**
     *  Procedimento que configura valores iniciais
     *  para os componentes da activity
     */
    private void initializeComponents()
    {
        /* Toolbar */
        mtbInicial = (Toolbar)findViewById(R.id.incTlbInicial);
        mtbInicial.setTitle(R.string.title_activity_inicial);
        mtbInicial.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        setSupportActionBar(mtbInicial);

        /* Header */
        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_background)
                .build();

        /* Drawer */
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mtbInicial)
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
                                startActivityProcessos();
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

        initBD();
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

    protected void initBD()
    {
        // Inicializa o Banco de Dados do Aplicativo
        BD.initInstance();
        //lerArquivo();
        cria_exemplos();
    }

    private void cria_exemplos() {
        if(BD.isCria_ex() == false) {
            BD.setCria_ex(true);
            Calendar c = Calendar.getInstance();
            Date date = c.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datahoje = sdf.format(date);

            Interessado interessado = new Interessado("Joao","Rua Tancredo Neves");
            Interessado interessado2 = new Interessado("Maria","Rua Tancredo Neves");
            Crianca crianca = new Crianca("Ana");
            BD.addList_interessados(interessado);
            BD.addList_interessados(interessado2);
            BD.addList_criancas(crianca);

            Atendimento atendLudico = new Atendimento("Infantil", date, "8:00", "2 horas",BD.getList_criancas().get(0));
            Atendimento atendPsi = new Atendimento("PsicoSocial", date, "10:00", "1 hora",BD.getList_interessados().get(0));
            Atendimento atendPsi2 = new Atendimento("PsicoSocial", date, "11:00", "1 hora",BD.getList_interessados().get(1));
            ArrayList<Atendimento> listAtendimentos = new ArrayList<Atendimento>();
            listAtendimentos.add(atendLudico);
            listAtendimentos.add(atendPsi);
            listAtendimentos.add(atendPsi2);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Caso caso = null;
            Caso caso2 = null;
            try {
                caso = new Caso("34334264", sdf2.parse("2015-12-20"), "9:00", sdf2.parse("2016-01-08"), "15:00", "Mudança de Guarda", BD.getList_interessados(), BD.getList_criancas(), listAtendimentos);
                caso2 = new Caso("29473943", sdf2.parse("2016-01-10"), "13:00", sdf2.parse("2016-02-08"), "10:00", "Pensao Alimenticia", BD.getList_interessados(), BD.getList_criancas());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            BD.addList_casos_buscados(caso);
            BD.addList_casos_buscados(caso2);
            Log.i("ScriptExemplo", caso.toString());
            Log.i("ScriptExemplo", caso2.toString());
            BD.limpaList_Interessados_Criancas();
        }
    }

    public void lerArquivo(){
        Log.i("ScriptRead", "Tentando ler arquivo");
        try {
            FileInputStream fileis = new FileInputStream("/storage/extSdCard/teste/LPC-BD1.txt");
            InputStreamReader instream = new InputStreamReader(fileis);
            BufferedReader buffreader = new BufferedReader(instream);
            String line;
            line1 = "";
            while((line = buffreader.readLine()) != null){
                line1+= line;
            }
            Log.i("ScriptRead", line1.toString());
            fileis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvaArquivo(){
        Log.i("ScriptSave", "Tentando salvar arquivo");
        try {
            FileOutputStream fileos = new FileOutputStream("/storage/extSdCard/teste/LPC-BD1.txt");
            fileos.write(line1.getBytes());
            fileos.write("\n\n".getBytes());
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

    @Override
    public void onBackPressed(){
        salvaArquivo();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
}
