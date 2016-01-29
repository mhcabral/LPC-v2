package com.example.ives.lpc_v2.Layouts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Activitys.ProcessosActivity;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Visita;
import com.example.ives.lpc_v2.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ives on 15/10/2015.
 */
public class NovaVisitaFragment extends Fragment
{
    private int day = 0,month = 0,year = 0;
    private TextView txtDHVisita;
    private Button btnVisita;
    private Button btnFinalizarVisita;
    private Visita visita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_nova_visita, null);

        visita = null;

        txtDHVisita = (TextView) view.findViewById(R.id.txtDHVisita);

        btnVisita = (Button) view.findViewById(R.id.btnDHVisita);
        btnVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDateHourCalendar();
                Calendar cDefault = Calendar.getInstance();
                cDefault.set(year, month, day);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                                     @Override
                                                                                     public void onDateSet(DatePickerDialog view, int year2, int monthOfYear, int dayOfMonth) {
                                                                                         year = year2;
                                                                                         month = monthOfYear;
                                                                                         day = dayOfMonth;
                                                                                         String data = day+"-"+(month+1)+"-"+year;
                                                                                         SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                                                                                         try {
                                                                                             visita = new Visita(sdf2.parse(data), BD.getCaso_selecionado().getList_interessado().get(0).getEndereco());
                                                                                         } catch (ParseException e) {
                                                                                             e.printStackTrace();
                                                                                         }
                                                                                         txtDHVisita.setText(
                                                                                                 "Data da Visita:" + "\n\n"
                                                                                                         + (day < 10 ? "0" + day : day) + "/"
                                                                                                         + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                                                                                         + year
                                                                                         );
                                                                                         Log.i("ScriptVisita", visita.toString());
                                                                                     }
                                                                                 },
                        cDefault.get(Calendar.YEAR),
                        cDefault.get(Calendar.MONTH),
                        cDefault.get(Calendar.DAY_OF_MONTH)
                );

                Calendar cMin = Calendar.getInstance();
                datePickerDialog.setMinDate(cMin);
                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        year = month = day = 0;
                        txtDHVisita.setText("");
                    }
                });
                datePickerDialog.show(getActivity().getFragmentManager(), "DataVisita");
            }
        });

        btnFinalizarVisita = (Button) view.findViewById(R.id.btnFinalizarVisita);
        btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visita != null){
                    BD.getCaso_selecionado().addList_visitas(visita);
                    Toast.makeText(getContext(),"Visita cadastrada com sucesso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity().getApplicationContext(), ProcessosActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(),"Data da visita não selecionada",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void initDateHourCalendar()
    {
        if(year == 0)
        {
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }
    }
}
