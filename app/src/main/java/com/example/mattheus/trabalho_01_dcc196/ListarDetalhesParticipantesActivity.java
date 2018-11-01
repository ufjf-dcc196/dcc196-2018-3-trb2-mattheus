package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListarDetalhesParticipantesActivity extends AppCompatActivity {

    private TextView txt_nome, txt_email, txt_cpf;
    private RecyclerView recyclerView_eventosCadastrados,recyclerView_eventosDisponiveis;
    private ListarEventosDisponiveisAdapter adapter_disponiveis;
    private ListarEventosCadastradosAdapter adapter_cadastrados;
    private Button btn_editar_participante;
    private int id_participante;
    public static final String ID_PARTICIPANTE = "Id do Participante";
    private static final int REQUEST_EDITAR_PARTICIPANTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_detalhes_participantes);


        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();

        id_participante = bundleResult.getInt(ListarParticipantesActivity.POSICAO_PARTICIPANTE);

        recyclerView_eventosCadastrados = findViewById(R.id.rcl_View_eventos_cadastrados);
        recyclerView_eventosCadastrados.setLayoutManager(new LinearLayoutManager(this));

        adapter_cadastrados = new ListarEventosCadastradosAdapter(
                Singleton.getInstance().getParticipantes().get(id_participante).getEventos());

        recyclerView_eventosCadastrados.setAdapter(adapter_cadastrados);



        recyclerView_eventosDisponiveis = findViewById(R.id.rcl_View_eventos_disponiveis);
        recyclerView_eventosDisponiveis.setLayoutManager(new LinearLayoutManager(this));

        adapter_disponiveis = new ListarEventosDisponiveisAdapter(
                Singleton.getInstance().getParticipantes().get(id_participante).getEventosNaoCadstrados());

        recyclerView_eventosDisponiveis.setAdapter(adapter_disponiveis);


        txt_nome = findViewById(R.id.txt_list_participante_nome);
        txt_email = findViewById(R.id.txt_list_participante_email);
        txt_cpf = findViewById(R.id.txt_list_participante_cpf);
        btn_editar_participante = findViewById(R.id.btn_Editar_Participante);

        updateData();
        adapter_cadastrados.setOnEventoCadastradosClickListener(new ListarEventosCadastradosAdapter.OnEventoCadastradosClickListener() {
            @Override
            public void onEventoCadastradosClick(View view, int position) {

                Integer i = Singleton.getInstance().getEventos().indexOf
                        (Singleton.getInstance().getParticipantes().get(id_participante).getEventos().get(position));

                Singleton.getInstance().getParticipantes().get(id_participante).removeEvento
                        (Singleton.getInstance().getEventos().get(i));

                Singleton.getInstance().getParticipantes().get(id_participante).addEventoNaoCadastrado
                        (Singleton.getInstance().getEventos().get(i));

                Singleton.getInstance().getEventos().get(i).removeParticipante
                        (Singleton.getInstance().getParticipantes().get(id_participante));

                adapter_disponiveis.notifyDataSetChanged();
                adapter_cadastrados.notifyDataSetChanged();
            }
        });

        adapter_disponiveis.setOnEventoDisponiveisClickListener(new ListarEventosDisponiveisAdapter.OnEventoDisponiveisClickListener() {
            @Override
            public void onEventoDisponiveisClick(View view, int position) {
                Integer i = Singleton.getInstance().getEventos().indexOf
                        (Singleton.getInstance().getParticipantes().get(id_participante).getEventosNaoCadstrados().get(position));

                Singleton.getInstance().getParticipantes().get(id_participante).
                        addEvento(Singleton.getInstance().getEventos().get(i));

                Singleton.getInstance().getParticipantes().get(id_participante).
                        removeEventoNaoCadastrado(Singleton.getInstance().getEventos().get(i));

                Singleton.getInstance().getEventos().get(i).
                        addParticipante(Singleton.getInstance().getParticipantes().get(id_participante));

                adapter_disponiveis.notifyDataSetChanged();
                adapter_cadastrados.notifyDataSetChanged();
            }
        });

        btn_editar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarDetalhesParticipantesActivity.this,EditarDadosParticipanteActivity.class);
                intent.putExtra(ListarDetalhesParticipantesActivity.ID_PARTICIPANTE,id_participante);
                startActivityForResult(intent,REQUEST_EDITAR_PARTICIPANTE);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ListarDetalhesParticipantesActivity.REQUEST_EDITAR_PARTICIPANTE && resultCode== Activity.RESULT_OK){
            updateData();
        }

    }

    protected void updateData(){
        txt_nome.setText(Singleton.getInstance().getParticipantes().get(id_participante).getNome());
        txt_email.setText(Singleton.getInstance().getParticipantes().get(id_participante).getEmail());
        txt_cpf.setText(Singleton.getInstance().getParticipantes().get(id_participante).getCPF());

    }
}
