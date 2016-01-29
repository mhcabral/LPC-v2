package com.example.ives.lpc_v2.Layouts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Activitys.ProcessosActivity;
import com.example.ives.lpc_v2.Models.Atendimento;
import com.example.ives.lpc_v2.Models.BD;
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
public class NovoAtendimentoFragment extends Fragment
{
    private int year = 0, month = 0, day = 0, hour = 0, minutes = 0;
    private Atendimento atendLudico,atendpsi;
    private CheckBox chbAtendLudico;
    private TextView txtDHAtendLudico;
    private TextView txtDHAtendPsi;
    private Button btnAtendLudico;
    private Button btnAtendPsi;
    private Button btnFinalizarAtendimento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_novo_atendimento, null);

        atendLudico = atendpsi = null;

        txtDHAtendLudico = (TextView) view.findViewById(R.id.txtDHAtendLudico2);

        txtDHAtendPsi = (TextView) view.findViewById(R.id.txtDHAtendPsi2);

        btnAtendLudico = (Button) view.findViewById(R.id.btnDHAtendLudico2);
        btnAtendLudico.setEnabled(false);
        btnAtendLudico.setVisibility(View.INVISIBLE);
        btnAtendLudico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDateHourCalendar();
                Calendar cDefault = Calendar.getInstance();
                cDefault.set(year, month, day);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                                     @Override
                                                                                     public void onDateSet(DatePickerDialog view, int year2, int monthOfYear, int dayOfMonth) {
                                                                                         Calendar tDefault = Calendar.getInstance();
                                                                                         tDefault.set(year, month, day, hour, minutes);

                                                                                         year = year2;
                                                                                         month = monthOfYear;
                                                                                         day = dayOfMonth;

                                                                                         TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                                                                                                                              @Override
                                                                                                                                                              public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                                                                                                                                                  hour = hourOfDay;
                                                                                                                                                                  minutes = minute;
                                                                                                                                                                  String data = day+"-"+(month+1)+"-"+year;
                                                                                                                                                                  SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                                                                                                                                                                  SimpleDateFormat sdf = new SimpleDateFormat("mm");
                                                                                                                                                                  try {
                                                                                                                                                                      atendLudico = new Atendimento("Infantil", sdf2.parse(data), hour + ":" + sdf.format(minutes), "2 horas");
                                                                                                                                                                  } catch (ParseException e) {
                                                                                                                                                                      e.printStackTrace();
                                                                                                                                                                  }
                                                                                                                                                                  txtDHAtendLudico.setText(
                                                                                                                                                                          "Data e Hora do Atendimento Infantil:" + "\n\n"
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
                                                                                                 txtDHAtendLudico.setText("");
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
                        txtDHAtendLudico.setText("");
                    }
                });
                datePickerDialog.show(getActivity().getFragmentManager(), "Data");
            }
        });

        btnAtendPsi = (Button) view.findViewById(R.id.btnDHAtendPsi2);
        btnAtendPsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDateHourCalendar();
                Calendar cDefault = Calendar.getInstance();
                cDefault.set(year, month, day);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                                     @Override
                                                                                     public void onDateSet(DatePickerDialog view, int year2, int monthOfYear, int dayOfMonth) {
                                                                                         Calendar tDefault = Calendar.getInstance();
                                                                                         tDefault.set(year, month, day, hour, minutes);

                                                                                         year = year2;
                                                                                         month = monthOfYear;
                                                                                         day = dayOfMonth;

                                                                                         TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
                                                                                                                                                              @Override
                                                                                                                                                              public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                                                                                                                                                  hour = hourOfDay;
                                                                                                                                                                  minutes = minute;
                                                                                                                                                                  String data = day+"-"+(month+1)+"-"+year;
                                                                                                                                                                  SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                                                                                                                                                                  SimpleDateFormat sdf = new SimpleDateFormat("mm");
                                                                                                                                                                  try {
                                                                                                                                                                      atendpsi = new Atendimento("PsicoSocial",sdf2.parse(data),hour+":"+sdf.format(minutes),"1 hora");
                                                                                                                                                                  } catch (ParseException e) {
                                                                                                                                                                      e.printStackTrace();
                                                                                                                                                                  }
                                                                                                                                                                  txtDHAtendPsi.setText(
                                                                                                                                                                          "Data e Hora do Atendimento PsicoSocial:" + "\n\n"
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
                                                                                                 txtDHAtendPsi.setText("");
                                                                                             }
                                                                                         });
                                                                                         timePickerDialog.show(getActivity().getFragmentManager(), "Tempo");
                                                                                         year = year2;

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
                        txtDHAtendPsi.setText("");
                    }
                });
                datePickerDialog.show(getActivity().getFragmentManager(), "Data");
            }
        });

        chbAtendLudico = (CheckBox) view.findViewById(R.id.chkAtendLudico2);
        chbAtendLudico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnAtendLudico.setEnabled(true);
                    btnAtendLudico.setVisibility(View.VISIBLE);
                } else {
                    btnAtendLudico.setEnabled(false);
                    btnAtendLudico.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnFinalizarAtendimento = (Button) view.findViewById(R.id.btnFinalizarAtendimento);
        btnFinalizarAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean finalizou = false;
                if((atendLudico != null)&&(atendpsi != null)){
                    if(atendLudico.getHorario().compareToIgnoreCase(atendpsi.getHorario())<0){
                        BD.getCaso_selecionado().addList_atendimentos(atendLudico);
                        BD.getCaso_selecionado().addList_atendimentos(atendpsi);
                    }
                    else{
                        BD.getCaso_selecionado().addList_atendimentos(atendpsi);
                        BD.getCaso_selecionado().addList_atendimentos(atendLudico);
                    }
                    Toast.makeText(getContext(), "Atendimentos adicionados", Toast.LENGTH_SHORT).show();
                    finalizou = true;
                }
                else if(atendpsi != null){
                    BD.getCaso_selecionado().addList_atendimentos(atendpsi);
                    Toast.makeText(getContext(), "Atendimento PsicoSocial adicionado", Toast.LENGTH_SHORT).show();
                    finalizou = true;
                }
                else if(atendLudico != null){
                    BD.getCaso_selecionado().addList_atendimentos(atendLudico);
                    Toast.makeText(getContext(), "Atendimento Infantil adicionado", Toast.LENGTH_SHORT).show();
                    finalizou = true;
                }
                else{
                    Toast.makeText(getContext(),"Datas não selecionadas",Toast.LENGTH_SHORT).show();
                }
                if(finalizou == true){
                    Intent intent = new Intent(getActivity().getApplicationContext(), ProcessosActivity.class);
                    startActivity(intent);
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
