package com.example.ives.lpc_v2.Activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.ives.lpc_v2.Layouts.NovaVisitaFragment;
import com.example.ives.lpc_v2.Layouts.NovoAtendimentoFragment;
import com.example.ives.lpc_v2.R;

public class NovaAtividadeActivity extends FragmentActivity {

    private Button btnTipoAtividade;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private int flagFrag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_atividade);

        initializeComponents();

        if(savedInstanceState == null)
        {
            NovoAtendimentoFragment novoAtendimentoFragment = new NovoAtendimentoFragment();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.lnlTipoAtividade, novoAtendimentoFragment, "novoAtend_frag");
            fragmentTransaction.commit();
        }
    }

    private void initializeComponents() {
        btnTipoAtividade = (Button) findViewById(R.id.btnTipoAtividade);
        btnTipoAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flagFrag == 0){
                    btnTipoAtividade.setText(R.string.btn_TipoAtividade2);
                    NovaVisitaFragment novaVisitaFragment = new NovaVisitaFragment();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.lnlTipoAtividade, novaVisitaFragment, "novaVisita_frag");
                    fragmentTransaction.commit();
                    flagFrag = 1;
                }
                else{
                    btnTipoAtividade.setText(R.string.btn_TipoAtividade1);
                    NovoAtendimentoFragment novoAtendimentoFragment = new NovoAtendimentoFragment();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.lnlTipoAtividade, novoAtendimentoFragment, "novoAtend_frag");
                    fragmentTransaction.commit();
                    flagFrag = 0;
                }
            }
        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nova_atividade, menu);
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
