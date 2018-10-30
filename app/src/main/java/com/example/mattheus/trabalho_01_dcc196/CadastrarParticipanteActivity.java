package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarParticipanteActivity extends AppCompatActivity {
    private EditText txt_nome_participante, txt_email_participante, txt_cpf_participante;
    private Button btn_salvar_participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_participante);

        txt_nome_participante = findViewById(R.id.txt_Nome_Participante);
        txt_email_participante = findViewById(R.id.txt_Email_Participante);
        txt_cpf_participante = findViewById(R.id.txt_cpf_Participante);
        btn_salvar_participante = findViewById(R.id.btn_salvar_participante);

        Bundle extras = getIntent().getExtras();

        btn_salvar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();
                resultado.putExtra("Nome",txt_nome_participante.getText().toString());
                resultado.putExtra("Email",txt_email_participante.getText().toString());
                resultado.putExtra("CPF",txt_cpf_participante.getText().toString());
                Participante p = new Participante(txt_nome_participante.getText().toString(),txt_email_participante.getText().toString(),txt_cpf_participante.getText().toString());
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });

    }
}
