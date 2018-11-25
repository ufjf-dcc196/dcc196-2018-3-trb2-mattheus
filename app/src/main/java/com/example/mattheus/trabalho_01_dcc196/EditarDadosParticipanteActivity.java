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

public class EditarDadosParticipanteActivity extends AppCompatActivity {
    private EditText txt_edt_nome_participante, txt_edt_email_participante, txt_edt_cpf_participante;
    private Button btn_edt_salvar_participante;
    private static Integer id_participante = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_participante);

        ParticipanteDAO.getInstance().inicializarDBHelper(getApplicationContext());

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();

        id_participante = bundleResult.getInt(ListarDetalhesParticipantesActivity.ID_PARTICIPANTE);

        txt_edt_nome_participante = findViewById(R.id.txt_edt_Nome_Participante);
        txt_edt_email_participante = findViewById(R.id.txt_edt_Email_Participante);
        txt_edt_cpf_participante = findViewById(R.id.txt_edt_cpf_Participante);
        btn_edt_salvar_participante = findViewById(R.id.btn_salvar_edt_participante);

        setINFO();

        btn_edt_salvar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(txt_edt_nome_participante.getText().toString()) || txt_edt_nome_participante.getText() ==null
                        || txt_edt_email_participante.getText().toString().equals("") || txt_edt_email_participante.getText()==null
                        || txt_edt_cpf_participante.getText().toString().equals("") || txt_edt_cpf_participante.getText()==null) {
                    Toast t = Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }else{
                    Intent resultado = new Intent();

                    ParticipanteDAO.getInstance().getParticipantes().get(id_participante).setNome(txt_edt_nome_participante.getText().toString());;
                    ParticipanteDAO.getInstance().getParticipantes().get(id_participante).setEmail(txt_edt_email_participante.getText().toString());
                    ParticipanteDAO.getInstance().getParticipantes().get(id_participante).setCPF(txt_edt_cpf_participante.getText().toString());

                    setResult(Activity.RESULT_OK, resultado);
                    finish();
                }

            }
        });

    }
    private void setINFO(){
        Participante p = ParticipanteDAO.getInstance().getParticipantes().get(id_participante);

        txt_edt_nome_participante.setText(p.getNome());
        txt_edt_email_participante.setText(p.getEmail());
        txt_edt_cpf_participante.setText(p.getCPF());

    }
}
