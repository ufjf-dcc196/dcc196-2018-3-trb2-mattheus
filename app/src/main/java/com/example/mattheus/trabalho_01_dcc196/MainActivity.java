package com.example.mattheus.trabalho_01_dcc196;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_cadastrar_evento, btn_listar_eventos, btn_cadastrar_participante;
    public static final int REQUEST_CADASTRO_PARTICIPANTE = 1;
    public static final int REQUEST_CADASTRO_EVENTO = 1;
    public static final int REQUEST_LISTAR_EVENTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cadastrar_evento = findViewById(R.id.btn_Cadastrar_Evento);
        btn_cadastrar_participante = findViewById(R.id.btn_Cadastrar_Participante);
        btn_listar_eventos = findViewById(R.id.btn_View_Eventos);

        btn_cadastrar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarParticipante.class);
                startActivityForResult(intent, MainActivity.REQUEST_CADASTRO_PARTICIPANTE);
            }
        });
        btn_cadastrar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarEvento.class);
                startActivityForResult(intent, MainActivity.REQUEST_CADASTRO_EVENTO);
            }
        });
        btn_listar_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarEvento.class);
                startActivityForResult(intent, MainActivity.REQUEST_CADASTRO_EVENTO);

            }
        });


    }
}
