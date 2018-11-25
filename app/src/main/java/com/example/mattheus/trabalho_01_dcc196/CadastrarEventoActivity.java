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

import java.util.ArrayList;

public class CadastrarEventoActivity extends AppCompatActivity {
    private EditText txt_titulo, txt_dia, txt_hora, txt_facilitador, txt_desc;
    private Button btn_salvar_evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        EventoDAO.getInstance().inicializarDBHelper(getApplicationContext());

        txt_titulo = findViewById(R.id.txt_titulo_evento);
        txt_dia = findViewById(R.id.txt_dia_evento);
        txt_hora = findViewById(R.id.txt_hora_evento);
        txt_facilitador = findViewById(R.id.txt_facilitador_evento);
        txt_desc = findViewById(R.id.txt_descrição_evento);
        btn_salvar_evento = findViewById(R.id.btn_salvar_evento);

        btn_salvar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("".equals(txt_titulo.getText().toString()) || txt_titulo.getText() ==null
                        || txt_desc.getText().toString().equals("") || txt_desc.getText()==null
                        || txt_dia.getText().toString().equals("") || txt_dia.getText()==null
                        || txt_facilitador.getText().toString().equals("") || txt_facilitador.getText()==null
                        || txt_hora.getText().toString().equals("") || txt_hora.getText()==null) {
                    Toast t = Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }else{

                    Intent resultado = new Intent();
                    Evento e = new Evento(txt_titulo.getText().toString(), txt_dia.getText().toString(),txt_hora.getText().toString(), txt_facilitador.getText().toString(),txt_desc.getText().toString());
                    EventoDAO.getInstance().addEvento(e);
                    MainActivity.Dale();
                    setResult(Activity.RESULT_OK, resultado);
                    finish();

                }


            }
        });
    }
}
