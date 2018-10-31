package com.example.mattheus.trabalho_01_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListarDetalhesParticipantesActivity extends AppCompatActivity {
    private TextView txt_nome, txt_email, txt_cpf;
    private Button btn_editar_participante;
    private int id_participante;
    private static final String ID_PARTICIPANTE = "Id do Participante";
    private static final int REQUEST_EDITAR_PARTICIPANTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_detalhes_participantes);


        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        id_participante = bundleResult.getInt(ListarParticipantesActivity.POSICAO_PARTICIPANTE);

        txt_nome = findViewById(R.id.txt_list_participante_nome);
        txt_email = findViewById(R.id.txt_list_participante_email);
        txt_cpf = findViewById(R.id.txt_list_participante_cpf);
        btn_editar_participante = findViewById(R.id.btn_Editar_Participante);

        txt_nome.setText(Singleton.getInstance().getParticipantes().get(id_participante).getNome());
        txt_email.setText(Singleton.getInstance().getParticipantes().get(id_participante).getEmail());
        txt_cpf.setText(Singleton.getInstance().getParticipantes().get(id_participante).getCPF());


        btn_editar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarDetalhesParticipantesActivity.this,EditarDadosParticipanteActivity.class);
                intent.putExtra(ListarDetalhesParticipantesActivity.ID_PARTICIPANTE,id_participante);
                startActivityForResult(intent,REQUEST_EDITAR_PARTICIPANTE);
            }
        });

    }
}
