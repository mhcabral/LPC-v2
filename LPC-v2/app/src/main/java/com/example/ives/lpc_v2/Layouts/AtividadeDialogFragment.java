package com.example.ives.lpc_v2.Layouts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Activitys.ProcessosActivity;
import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Dialogs;
import com.example.ives.lpc_v2.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ives on 15/10/2015.
 */
public class AtividadeDialogFragment extends DialogFragment
{
    private int day = 0,month = 0,year = 0,hour = 0,minutes = 0;
    private Button btnCancelar;
    private Button btnReagendar;
    private Button btnAtualizarStatus;
    private Spinner spnNewStatus;
    private TextView txtReagendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.atividade_dialog_fragment,null);
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        view.setMinimumWidth(width);
        view.setMinimumHeight(height);

        txtReagendar = (TextView) view.findViewById(R.id.txtReagendar);

        btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogs.setTipo_atividade(-1);
                if(BD.getAtendimento_selecionado() != null){
                    BD.limpaAtendimento_selecionado();
                }
                if(BD.getVisita_selecionada() != null){
                    BD.limpaVisita_selecionada();
                }
                dismiss();
            }
        });

        /* Spinner New Status */
        spnNewStatus = (Spinner) view.findViewById(R.id.spnNewStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.itens_status_atividade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNewStatus.setAdapter(adapter);
        if(Dialogs.getTipo_atividade() == 0){
            if(BD.getAtendimento_selecionado().getStatus().equals("aberta")){
                spnNewStatus.setSelection(0);
            }
            else{
                spnNewStatus.setSelection(1);
            }
        }
        else{
            if(BD.getVisita_selecionada().getStatus().equals("aberta")){
                spnNewStatus.setSelection(0);
            }
            else{
                spnNewStatus.setSelection(1);
            }
        }

        btnReagendar = (Button) view.findViewById(R.id.btnReagendar);
        btnReagendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDateHourCalendar();
                Calendar cDefault = Calendar.getInstance();
                cDefault.set(year, month, day);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                                     @Override
                                                                                     public void onDateSet(DatePickerDialog view, int years, int monthOfYear, int dayOfMonth) {
                                                                                         Calendar tDefault = Calendar.getInstance();
                                                                                         tDefault.set(year, month, day, hour, minutes);

                                                                                         year = years;
                                                                                         month = monthOfYear;
                                                                                         day = dayOfMonth;

                                                                                         TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                                                                                                                              @Override
                                                                                                                                                              public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                                                                                                                                                  hour = hourOfDay;
                                                                                                                                                                  minutes = minute;
                                                                                                                                                                  String data = day+"-"+(month+1)+"-"+year;
                                                                                                                                                                  SimpleDateFormat sdf = new SimpleDateFormat("mm");
                                                                                                                                                                  SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                                                                                                                                                                  if(Dialogs.getTipo_atividade() == 1){
                                                                                                                                                                      try {
                                                                                                                                                                          BD.getVisita_selecionada().setData(sdf2.parse(data));
                                                                                                                                                                      } catch (ParseException e) {
                                                                                                                                                                          e.printStackTrace();
                                                                                                                                                                      }
                                                                                                                                                                  }
                                                                                                                                                                  else if(Dialogs.getTipo_atividade() == 0){
                                                                                                                                                                      try {
                                                                                                                                                                          BD.getAtendimento_selecionado().setData(sdf2.parse(data));
                                                                                                                                                                      } catch (ParseException e) {
                                                                                                                                                                          e.printStackTrace();
                                                                                                                                                                      }
                                                                                                                                                                      BD.getAtendimento_selecionado().setHorario(hour + ":" + sdf.format(minutes));
                                                                                                                                                                      Log.i("ScriptAtendimento", BD.getAtendimento_selecionado().toString());
                                                                                                                                                                  }
                                                                                                                                                                  txtReagendar.setText(
                                                                                                                                                                          "Nova Data e Hora:" + "\n\n"
                                                                                                                                                                                  + (day < 10 ? "0" + day : day) + "/"
                                                                                                                                                                                  + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                                                                                                                                                                  + year + " - "
                                                                                                                                                                                  + (hour < 10 ? "0" + hour : hour) + ":"
                                                                                                                                                                                  + (minute < 10 ? "0" + minute : minute)
                                                                                                                                                                  );

                                                                                                                                                              }
                                                                                                                                                          },
                                                                                                 tDefault.get(Calendar.HOUR_OF_DAY),
                                                                                                 tDefault.get(Calendar.MINUTE),
                                                                                                 true);


                                                                                         timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                                                             @Override
                                                                                             public void onCancel(DialogInterface dialog) {
                                                                                                 hour = minutes = 0;
                                                                                             }
                                                                                         });
                                                                                         timePickerDialog.show(getActivity().getFragmentManager(), "Tempo");

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
                    }
                });
                datePickerDialog.show(getActivity().getFragmentManager(), "Data");
            }
        });

        btnAtualizarStatus = (Button) view.findViewById(R.id.btnAtualizarStatus);
        btnAtualizarStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Dialogs.getTipo_atividade() == 1){
                    BD.getVisita_selecionada().setStatus(spnNewStatus.getSelectedItem().toString());
                    Toast.makeText(getContext(),"Visita Atualizada com sucesso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity().getApplicationContext(), ProcessosActivity.class);
                    startActivity(intent);
                }
                else if(Dialogs.getTipo_atividade() == 0){
                    BD.getAtendimento_selecionado().setStatus(spnNewStatus.getSelectedItem().toString());
                    Toast.makeText(getContext(), "Atendimento Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity().getApplicationContext(), ProcessosActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "Erro encontrado", Toast.LENGTH_SHORT).show();
                    dismiss();
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
            hour = c.get(Calendar.HOUR_OF_DAY);
            minutes = c.get(Calendar.MINUTE);
        }
    }

}
