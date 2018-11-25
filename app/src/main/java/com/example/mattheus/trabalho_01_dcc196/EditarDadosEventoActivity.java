package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarDadosEventoActivity extends AppCompatActivity {
    private EditText txt_edt_titulo, txt_edt_dia, txt_edt_hora, txt_edt_facilitador, txt_edt_desc;
    private Button btn_edt_salvar_evento;
    private static Integer id_evento = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_evento);
        EventoDAO.getInstance().inicializarDBHelper(getApplicationContext());

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();

        id_evento = bundleResult.getInt(ListarDetalhesEventosActivity.ID_EVENTO);
        txt_edt_titulo = findViewById(R.id.txt_edt_titulo_evento);
        txt_edt_dia = findViewById(R.id.txt_edt_dia_evento);
        txt_edt_hora = findViewById(R.id.txt_edt_hora_evento);
        txt_edt_facilitador = findViewById(R.id.txt_edt_facilitador_evento);
        txt_edt_desc = findViewById(R.id.txt_edt_descrição_evento);
        btn_edt_salvar_evento = findViewById(R.id.btn_salvar_edt_evento);

        setINFO();

        btn_edt_salvar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(txt_edt_titulo.getText().toString()) || txt_edt_titulo.getText() ==null
                        || txt_edt_desc.getText().toString().equals("") || txt_edt_desc.getText()==null
                        || txt_edt_dia.getText().toString().equals("") || txt_edt_dia.getText()==null
                        || txt_edt_facilitador.getText().toString().equals("") || txt_edt_facilitador.getText()==null
                        || txt_edt_hora.getText().toString().equals("") || txt_edt_hora.getText()==null) {
                    Toast t = Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }else{

                    Intent resultado = new Intent();
                    EventoDAO.getInstance().getEventos().get(id_evento).setTitulo(txt_edt_titulo.getText().toString());
                    EventoDAO.getInstance().getEventos().get(id_evento).setData(txt_edt_dia.getText().toString());
                    EventoDAO.getInstance().getEventos().get(id_evento).setHora(txt_edt_hora.getText().toString());
                    EventoDAO.getInstance().getEventos().get(id_evento).setFacilitador(txt_edt_facilitador.getText().toString());
                    EventoDAO.getInstance().getEventos().get(id_evento).setDescricao(txt_edt_desc.getText().toString());
                    MainActivity.Dale();
                    setResult(Activity.RESULT_OK, resultado);
                    finish();

                }
            }
        });


    }
    private void setINFO(){
        Evento e = EventoDAO.getInstance().getEventos().get(id_evento);

        txt_edt_titulo.setText(e.getTitulo());
        txt_edt_dia.setText(e.getData());
        txt_edt_hora.setText(e.getHora());
        txt_edt_facilitador.setText(e.getFacilitador());
        txt_edt_desc.setText(e.getDescricao());

    }

}
