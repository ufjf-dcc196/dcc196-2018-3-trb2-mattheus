package com.example.mattheus.trabalho_01_dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListarDetalhesParticipantesActivity extends AppCompatActivity {
    private TextView txt_nome, txt_email, txt_cpf;
    private int id_participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_detalhes_participantes);



    }
}
