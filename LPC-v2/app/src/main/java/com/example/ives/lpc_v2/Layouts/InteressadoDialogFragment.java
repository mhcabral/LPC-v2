package com.example.ives.lpc_v2.Layouts;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ives.lpc_v2.Models.BD;
import com.example.ives.lpc_v2.Models.Dialogs;
import com.example.ives.lpc_v2.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ives on 15/10/2015.
 */
public class InteressadoDialogFragment extends DialogFragment
{

    private Button btnCancelar;
    private int year, month, day;
    private TextView txtDataNascimento;
    private TextView txtTitle;
    private EditText edtEndereco;
    private EditText edtTelefone;
    private Button btnDataNascimento;
    private Button btnAtualizar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);

        initDateHourCalendar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.interessado_dialog_fragment, null);
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        view.setMinimumWidth(width);
        view.setMinimumHeight(height);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtTitle.setText("Interessado: " + Dialogs.getInteressado_dialog_title());
        txtDataNascimento = (TextView)view.findViewById(R.id.txtDataNascimento);

        edtEndereco = (EditText) view.findViewById(R.id.edtEndereco);

        edtTelefone = (EditText) view.findViewById(R.id.edtTelefone);

        //verificacaodados();

        btnDataNascimento = (Button)view.findViewById(R.id.btnDataNascimento);
        btnDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDateHourCalendar();
                Calendar cDefault = Calendar.getInstance();
                cDefault.set(year, month, day);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                                     @Override
                                                                                     public void onDateSet(DatePickerDialog view, int year2, int monthOfYear, int dayOfMonth) {
                                                                                         year = year2;
                                                                                         txtDataNascimento.setText(
                                                                                                 "Data de Nascimento:" + "\n\n"
                                                                                                         + (day < 10 ? "0" + day : day) + "/"
                                                                                                         + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/"
                                                                                                         + year
                                                                                         );
                                                                                     }
                                                                                 },
                        cDefault.get(Calendar.YEAR),
                        cDefault.get(Calendar.MONTH),
                        cDefault.get(Calendar.DAY_OF_MONTH)
                );

                //Calendar cMin = Calendar.getInstance();
                //cMin.set(1900,1,1);
                //datePickerDialog.setMinDate(cMin);
                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        year = month = day = 0;
                        txtDataNascimento.setText("");
                    }
                });
                datePickerDialog.show(getActivity().getFragmentManager(), "Data");
            }
        });

        btnCancelar = (Button) view.findViewById(R.id.btnCancelarI);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAtualizar = (Button) view.findViewById(R.id.btnAtualizarI);
        btnAtualizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                BD.getInteressado_selecionado().setEndereco(edtEndereco.getText().toString());
                BD.getInteressado_selecionado().setTelefone(edtTelefone.getText().toString());
                BD.getInteressado_selecionado().setData_nascimento((Date) new Date(year,month,day));
                Log.i("ScriptInteressado", BD.getInteressado_selecionado().toString2());
                Toast.makeText(getContext(), "Dados Atualizados", Toast.LENGTH_SHORT);
                BD.limpaInteressado_selecionado();
                dismiss();
            }
        });

        return view;
    }

    private void verificacaodados() {
        if(BD.getInteressado_selecionado().getData_nascimento() != null){
            txtDataNascimento.setText(BD.getInteressado_selecionado().getData_nascimento().toString());
        }
        if(BD.getInteressado_selecionado().getEndereco()!=null){
            edtEndereco.setText(BD.getInteressado_selecionado().getEndereco());
        }
        if(BD.getInteressado_selecionado().getTelefone()!=null){
            edtTelefone.setText(BD.getInteressado_selecionado().getTelefone());
        }
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
