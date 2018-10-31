package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditarDadosParticipanteActivity extends AppCompatActivity {
    private EditText txt_edt_nome_participante, txt_edt_email_participante, txt_edt_cpf_participante;
    private Button btn_edt_salvar_participante;
    private static Integer id_participante = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_participante);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();

        id_participante = bundleResult.getInt(ListarDetalhesParticipantesActivity.ID_PARTICIPANTE);

        txt_edt_nome_participante = findViewById(R.id.txt_edt_Nome_Participante);
        txt_edt_email_participante = findViewById(R.id.txt_edt_Email_Participante);
        txt_edt_cpf_participante = findViewById(R.id.txt_edt_cpf_Participante);
        btn_edt_salvar_participante = findViewById(R.id.btn_salvar_edt_participante);

        Participante p = Singleton.getInstance().getParticipantes().get(id_participante);

        txt_edt_nome_participante.setText(p.getNome());
        txt_edt_email_participante.setText(p.getEmail());
        txt_edt_cpf_participante.setText(p.getCPF());

        btn_edt_salvar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();

                Participante edt_p = new Participante();

                edt_p.setNome(txt_edt_nome_participante.getText().toString());
                edt_p.setEmail(txt_edt_email_participante.getText().toString());
                edt_p.setCPF(txt_edt_cpf_participante.getText().toString());

                Singleton.getInstance().updateParticipante(edt_p);

                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });

    }
}
