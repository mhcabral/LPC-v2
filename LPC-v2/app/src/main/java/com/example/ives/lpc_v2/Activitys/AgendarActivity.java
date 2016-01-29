package com.example.ives.lpc_v2.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Models.Atendimento;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Caso;
import com.example.ives.lpc_v2.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AgendarActivity extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, DialogInterface.OnCancelListener
{
    private int year, month, day, hour, minutes,flag_btn_option,hourf,minutesf,hours2,minutes2;
    private TextView txtDHAudiFinal;
    private TextView txtDHAtendPsi;
    private TextView txtDHAtendLudico;
    //private Spinner spnDHAtendLudico;
    private CheckBox chbAtendLudico;
    private CheckBox chbInterBreve;
    private Button btnAtendLudico;
    private Button btnAtendPsi;
    private Button btnAudiFinal;
    private Date dataAudiInicial;
    private Date dataAudiFinal;
    private Date dataAtendPsi;
    private Date dataAtendLudico;
    private Atendimento atendPsi;
    private Atendimento atendLudico;
    private boolean flag_inter_breve;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        initializeComponents();
    }

    public void initializeComponents()
    {
        flag_btn_option = 0;
        txtDHAudiFinal = (TextView)findViewById(R.id.txtDHAudiFinal);
        txtDHAtendPsi = (TextView)findViewById(R.id.txtDHAtendPsi);

        txtDHAtendLudico = (TextView)findViewById(R.id.txtDHAtendLudico);
        txtDHAtendLudico.setEnabled(false);

        btnAtendLudico = (Button)findViewById(R.id.btnDHAtendLudico);
        btnAtendLudico.setEnabled(false);

        btnAtendPsi = (Button)findViewById(R.id.btnDHAtendPsi);
        btnAtendPsi.setEnabled(true);

        btnAudiFinal = (Button) findViewById(R.id.btnDHAudiFinal);
        btnAudiFinal.setEnabled(true);

        chbAtendLudico = (CheckBox)findViewById(R.id.chkAtendLudico);
        chbAtendLudico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtDHAtendLudico.setEnabled(true);
                    btnAtendLudico.setEnabled(true);
                    btnAtendLudico.setText(R.string.btn_DH_Atendimento_Ludico);

                } else {
                    txtDHAtendLudico.setEnabled(false);
                    btnAtendLudico.setEnabled(false);
                    btnAtendLudico.setText("");
                }
            }
        });

        chbInterBreve = (CheckBox) findViewById(R.id.chkInterBreve);
        chbInterBreve.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chbAtendLudico.setEnabled(false);
                    chbAtendLudico.setVisibility(View.INVISIBLE);
                    btnAtendPsi.setEnabled(false);
                    btnAtendPsi.setVisibility(View.INVISIBLE);
                    btnAudiFinal.setEnabled(false);
                    btnAudiFinal.setVisibility(View.INVISIBLE);

                    String txtassunto = BD.getAssunto()+" - Intervenção Breve";
                    BD.setAssunto(txtassunto);
                    dataAudiFinal = dataAudiInicial;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
                    String min = sdf2.format(minutes2);
                    String datAudF = sdf.format(dataAudiFinal);
                    hourf = hours2;
                    minutesf = minutes2;
                    txtDHAudiFinal.setText(
                            "Data e Hora da Audiência Final: "+ datAudF + " - " + hourf + ":" + min
                    );
                    flag_inter_breve = true;

                } else {
                    chbAtendLudico.setEnabled(true);
                    chbAtendLudico.setVisibility(View.VISIBLE);
                    btnAtendPsi.setEnabled(true);
                    btnAtendPsi.setVisibility(View.VISIBLE);
                    btnAudiFinal.setEnabled(true);
                    btnAudiFinal.setVisibility(View.VISIBLE);
                    txtDHAudiFinal.setText("");
                    flag_inter_breve = false;
                }
            }
        });

        atendLudico = null;
        atendPsi = null;
        flag_inter_breve = false;
        gerarDataAudiInicial();
    }

    public void gerarDataAudiInicial(){
        Calendar c = Calendar.getInstance();
        dataAudiInicial = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datahoje = sdf.format(dataAudiInicial);
        hours2 = new Time(System.currentTimeMillis()).getHours();
        minutes2 = new Time(System.currentTimeMillis()).getMinutes();
        Log.i("ScriptDataAudiencia", "Audiencia Inicial: " + datahoje);
    }

    public void btnFinalizarAgendamento_onClicked(View view)
    {
        Caso caso;
        if(txtDHAudiFinal.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Data da Audiencia Final não especificada", Toast.LENGTH_LONG).show();
        }
        else {
            Log.i("ScriptCaso", "Numero:" + BD.getNumero());
            Log.i("ScriptCaso", "Assunto:" + BD.getAssunto());
            for (int i = 0; i < BD.getList_interessados().size(); i++) {
                Log.i("ScriptInteressados", "Nome: " + BD.getList_interessados().get(i).getNome());
            }
            for (int i = 0; i < BD.getList_criancas().size(); i++) {
                Log.i("ScriptCriancas", "Nome: " + BD.getList_criancas().get(i).getNome());
            }

            Log.i("ScriptDataAudiencia", "Audiencia Final: " + dataAudiFinal);
            if(txtDHAtendPsi.getText().equals("")) {
                caso = new Caso(BD.getNumero(),dataAudiInicial,String.valueOf(hours2)+":"+String.valueOf(minutes2),dataAudiFinal,String.valueOf(hourf)+":"+String.valueOf(minutesf), BD.getAssunto(), BD.getList_interessados(), BD.getList_criancas());
            }
            else{
                ArrayList<Atendimento> lista_atendimento = new ArrayList<Atendimento>();
                lista_atendimento.add(atendPsi);
                if(atendLudico != null) {
                    lista_atendimento.add(atendLudico);
                }
                caso = new Caso(BD.getNumero(), dataAudiInicial,String.valueOf(hours2)+":"+String.valueOf(minutes2),dataAudiFinal,String.valueOf(hourf)+":"+String.valueOf(minutesf), BD.getAssunto(), BD.getList_interessados(), BD.getList_criancas(),lista_atendimento);
            }
            if(flag_inter_breve == true){
                caso.setFlag_inter_breve(true);
            }
            Log.i("ScriptCaso", caso.toString());
            BD.addList_casos_salvos(caso);
            BD.limpaList_Interessados_Criancas();
            Toast.makeText(getApplicationContext(), "Processo criado com sucesso", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), InicialActivity.class);
            startActivity(intent);
        }
    }

    public void btnDHAudiFinal_onClicked(View view)
    {
        flag_btn_option = 2;
        initDateHourCalendar();
        Calendar cDefault = Calendar.getInstance();
        cDefault.set(year, month, day);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,
                cDefault.get(Calendar.YEAR),
                cDefault.get(Calendar.MONTH),
                cDefault.get(Calendar.DAY_OF_MONTH)
        );

        Calendar cMin = Calendar.getInstance();
        datePickerDialog.setMinDate(cMin);
        datePickerDialog.setOnCancelListener(this);
        datePickerDialog.show(getFragmentManager(), "Data");
    }

    public void btnDHAtendPsi_onClicked(View view)
    {
        flag_btn_option = 1;
        initDateHourCalendar();
        Calendar cDefault = Calendar.getInstance();
        cDefault.set(year, month, day);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,
                cDefault.get(Calendar.YEAR),
                cDefault.get(Calendar.MONTH),
                cDefault.get(Calendar.DAY_OF_MONTH)
        );

        Calendar cMin = Calendar.getInstance();
        datePickerDialog.setMinDate(cMin);
        datePickerDialog.setOnCancelListener(this);
        datePickerDialog.show(getFragmentManager(), "Data");
    }

    public void btnDHAtendLudico_onClicked(View view)
    {
        flag_btn_option = 3;
        initDateHourCalendar();
        Calendar cDefault = Calendar.getInstance();
        cDefault.set(year, month, day);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,
                cDefault.get(Calendar.YEAR),
                cDefault.get(Calendar.MONTH),
                cDefault.get(Calendar.DAY_OF_MONTH)
        );

        Calendar cMin = Calendar.getInstance();
        datePickerDialog.setMinDate(cMin);
        datePickerDialog.setOnCancelListener(this);
        datePickerDialog.show(getFragmentManager(), "Data");
    }

    public void initDateHourCalendar()
    {
        if(year == 0)
        {
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            hour = c.get(Calendar.HOUR_OF_DAY);
            minutes = c.get(Calendar.MINUTE);
        }
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        year = month = day =  hour = minutes = 0;
        switch (flag_btn_option){
            case 1:
                txtDHAtendPsi.setText("");
                break;
            case 2:
                txtDHAudiFinal.setText("");
                break;
            case 3:
                txtDHAtendLudico.setText("");
                break;
        }



    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar tDefault = Calendar.getInstance();
        tDefault.set(year, month, day, hour, minutes);

        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;

        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this,
                tDefault.get(Calendar.HOUR_OF_DAY),
                tDefault.get(Calendar.MINUTE),
                true);


        timePickerDialog.setOnCancelListener(this);
        timePickerDialog.show(getFragmentManager(), "Tempo");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minutes = minute;

        switch (flag_btn_option){
            case 1:
                dataAtendPsi = (Date) new Date(year, month, day);
                atendPsi = new Atendimento("psicosocial",dataAtendPsi,hour+":"+minutes,"1 hora",BD.getList_interessados().get(0));
                txtDHAtendPsi.setText(
                        "Data e Hora do Atendimento PsicoSocial:" + "\n\n"
                                + (day < 10 ? "0" + day : day) + "/"
                                + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                + year + " - "
                                + (hour < 10 ? "0" + hour : hour) + ":"
                                + (minute < 10 ? "0" + minute : minute)
                );
                break;
            case 2:
                dataAudiFinal = (Date) new Date(year, month, day);
                hourf = hour;
                minutesf = minutes;
                txtDHAudiFinal.setText(
                        "Data e Hora da Audiência Final:" + "\n\n"
                                + (day < 10 ? "0" + day : day) + "/"
                                + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                + year + " - "
                                + (hour < 10 ? "0" + hour : hour) + ":"
                                + (minute < 10 ? "0" + minute : minute)
                );
                break;
            case 3:
                if(hour >= 10) {
                    dataAtendLudico = (Date) new Date(year, month, day);
                    atendLudico = new Atendimento("infantil",dataAtendLudico,hour+":"+minutes,"2 horas",BD.getList_criancas().get(0));
                    txtDHAtendLudico.setText(
                            "Data e Hora do Atendimento Infantil:" + "\n\n"
                                    + (day < 10 ? "0" + day : day) + "/"
                                    + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                    + year + " - "
                                    + (hour < 10 ? "0" + hour : hour) + ":"
                                    + (minute < 10 ? "0" + minute : minute)
                    );
                    hour = hour - 2;
                    dataAtendPsi = (Date) new Date(year, month, day);
                    atendPsi = new Atendimento("psicosocial",dataAtendPsi,hour+":"+minutes,"2 horas",BD.getList_interessados().get(0));
                    txtDHAtendPsi.setText(
                            "Data e Hora do Atendimento PsicoSocial:" + "\n\n"
                                    + (day < 10 ? "0" + day : day) + "/"
                                    + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                    + year + " - "
                                    + (hour < 10 ? "0" + hour : hour) + ":"
                                    + (minute < 10 ? "0" + minute : minute)
                    );
                }
                else{
                    dataAtendLudico = (Date) new Date(year, month, day);
                    atendLudico = new Atendimento("infantil",dataAtendLudico,hour+":"+minutes,"2 horas",BD.getList_criancas().get(0));
                    txtDHAtendLudico.setText(
                            "Data e Hora do Atendimento Infantil:" + "\n\n"
                                    + (day < 10 ? "0" + day : day) + "/"
                                    + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                    + year + " - "
                                    + (hour < 10 ? "0" + hour : hour) + ":"
                                    + (minute < 10 ? "0" + minute : minute)
                    );
                    hour = hour + 2;
                    dataAtendPsi = (Date) new Date(year, month, day);
                    atendPsi = new Atendimento("psicosocial",dataAtendPsi,hour+":"+minutes,"2 horas",BD.getList_interessados().get(0));
                    txtDHAtendPsi.setText(
                            "Data e Hora do Atendimento PsicoSocial:" + "\n\n"
                                    + (day < 10 ? "0" + day : day) + "/"
                                    + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                    + year + " - "
                                    + (hour < 10 ? "0" + hour : hour) + ":"
                                    + (minute < 10 ? "0" + minute : minute)
                    );
                }
                break;
        }
    }
}
