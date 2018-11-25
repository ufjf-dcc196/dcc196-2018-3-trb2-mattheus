package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarDadosEventoActivity extends AppCompatActivity {
    private EditText txt_edt_titulo, txt_edt_dia, txt_edt_hora, txt_edt_facilitador, txt_edt_desc;
    private Button btn_edt_salvar_evento;
    private static Integer id_evento = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_evento);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();

        id_evento = bundleResult.getInt(ListarDetalhesEventosActivity.ID_EVENTO);
        txt_edt_titulo = findViewById(R.id.txt_edt_titulo_evento);
        txt_edt_dia = findViewById(R.id.txt_edt_dia_evento);
        txt_edt_hora = findViewById(R.id.txt_edt_hora_evento);
        txt_edt_facilitador = findViewById(R.id.txt_edt_facilitador_evento);
        txt_edt_desc = findViewById(R.id.txt_edt_descrição_evento);
        btn_edt_salvar_evento = findViewById(R.id.btn_salvar_edt_evento);

        Evento e = Singleton.getInstance().getEventos().get(id_evento);

        txt_edt_titulo.setText(e.getTitulo());
        txt_edt_dia.setText(e.getData());
        txt_edt_hora.setText(e.getHora());
        txt_edt_facilitador.setText(e.getFacilitador());
        txt_edt_desc.setText(e.getDescricao());

        btn_edt_salvar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();



                Singleton.getInstance().getEventos().get(id_evento).setTitulo(txt_edt_titulo.getText().toString());
                Singleton.getInstance().getEventos().get(id_evento).setData(txt_edt_dia.getText().toString());
                Singleton.getInstance().getEventos().get(id_evento).setHora(txt_edt_hora.getText().toString());
                Singleton.getInstance().getEventos().get(id_evento).setFacilitador(txt_edt_facilitador.getText().toString());
                Singleton.getInstance().getEventos().get(id_evento).setDescricao(txt_edt_desc.getText().toString());

                MainActivity.Dale();
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });



    }
}
