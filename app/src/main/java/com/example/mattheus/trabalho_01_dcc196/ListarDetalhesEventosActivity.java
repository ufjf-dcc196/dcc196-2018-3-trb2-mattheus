package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListarDetalhesEventosActivity extends AppCompatActivity {
    private TextView txt_titulo, txt_dia, txt_hora, txt_facilitador, txt_descricao;
    private Button btn_editar_evento;
    private int id_evento;
    private static final int REQUEST_EDITAR_EVENTO = 1;
    public static final String ID_EVENTO = "Id do Evento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_detalhes_eventos);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        id_evento = bundleResult.getInt(MainActivity.POSICAO_EVENTO);

        txt_titulo = findViewById(R.id.txt_list_evento_titulo);
        txt_dia = findViewById(R.id.txt_list_evento_data);
        txt_hora = findViewById(R.id.txt_list_evento_hora);
        txt_facilitador = findViewById(R.id.txt_list_evento_facilitador);
        txt_descricao = findViewById(R.id.txt_list_evento_desc);
        btn_editar_evento = findViewById(R.id.btn_Editar_Evento);

        txt_titulo.setText(Singleton.getInstance().getEventos().get(id_evento).getTitulo());
        txt_dia.setText(Singleton.getInstance().getEventos().get(id_evento).getData());
        txt_hora.setText(Singleton.getInstance().getEventos().get(id_evento).getHora());
        txt_facilitador.setText(Singleton.getInstance().getEventos().get(id_evento).getFacilitador());
        txt_descricao.setText(Singleton.getInstance().getEventos().get(id_evento).getDescricao());


        btn_editar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarDetalhesEventosActivity.this, EditarDadosEventoActivity.class);
                intent.putExtra(ListarDetalhesEventosActivity.ID_EVENTO,id_evento);
                startActivityForResult(intent,REQUEST_EDITAR_EVENTO);
            }
        });

    }
}
