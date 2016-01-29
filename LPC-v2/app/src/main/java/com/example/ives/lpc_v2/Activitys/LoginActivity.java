package com.example.ives.lpc_v2.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ives.lpc_v2.R;

public class LoginActivity extends AppCompatActivity
{
    private Toolbar mtbLogin;
    private EditText edtlogin;
    private EditText edtSenha;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeComponents();
    }

    /**
     * Método que dispara ao clique do botão ENTRAR
     * verifica o login e a senha e abre a tela de Processos
     * @param view
     */
    public void btnLogin_onClicked(View view)
    {
        if((edtlogin.getText().toString().equals("psico"))&&(edtSenha.getText().toString().equals(senha))){
            Intent intent = new Intent(getApplicationContext(), InicialActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Usuario ou senha incorretos",Toast.LENGTH_SHORT).show();
        }
    }

    public void btnForgotPassword_onClicked(View view)
    {
        Toast.makeText(getApplicationContext(), "Senha Padrao redefinida", Toast.LENGTH_LONG).show();
        senha = "psico";
    }

    /**
     *  Procedimento que configura valores iniciais
     *  para os componentes da activity
     */
    private void initializeComponents()
    {
        /* Toolbar */
        mtbLogin = (Toolbar)findViewById(R.id.incTlbLogin);
        mtbLogin.setTitle(R.string.activity_login_title);
        mtbLogin.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        setSupportActionBar(mtbLogin);
        edtlogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtPassword);
        senha = "psico";
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
        moveTaskToBack(true);
    }
}
