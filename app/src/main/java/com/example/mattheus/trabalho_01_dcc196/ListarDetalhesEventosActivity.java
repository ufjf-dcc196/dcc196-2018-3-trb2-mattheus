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

public class ListarDetalhesEventosActivity extends AppCompatActivity {
    private ListarParticipantesNoEventoAdapter adapter;
    private TextView txt_titulo, txt_dia, txt_hora, txt_facilitador, txt_descricao;
    private Button btn_editar_evento;
    private Integer id_evento;
    private static final int REQUEST_EDITAR_EVENTO = 1;
    private static final String POSICAO_PARTICIPANTE = "Posição do Participante";
    public static final String ID_EVENTO = "Id do Evento";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_detalhes_eventos);

        EventoDAO.getInstance().inicializarDBHelper(getApplicationContext());
        ParticipanteDAO.getInstance().inicializarDBHelper(getApplicationContext());

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        id_evento = bundleResult.getInt(MainActivity.POSICAO_EVENTO);

        recyclerView = findViewById(R.id.recyclerView_Participantes_no_Evento);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListarParticipantesNoEventoAdapter(
                EventoParticipanteDAO.getInstance().getEventoParticipantes(id_evento)
        );
        recyclerView.setAdapter(adapter);

        txt_titulo = findViewById(R.id.txt_list_evento_titulo);
        txt_dia = findViewById(R.id.txt_list_evento_data);
        txt_hora = findViewById(R.id.txt_list_evento_hora);
        txt_facilitador = findViewById(R.id.txt_list_evento_facilitador);
        txt_descricao = findViewById(R.id.txt_list_evento_desc);
        btn_editar_evento = findViewById(R.id.btn_Editar_Evento);

        updateData();




        btn_editar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarDetalhesEventosActivity.this, EditarDadosEventoActivity.class);
                intent.putExtra(ListarDetalhesEventosActivity.ID_EVENTO,id_evento);
                startActivityForResult(intent,REQUEST_EDITAR_EVENTO);
            }
        });

        adapter.setOnParticipanteNoEventoClickListener(new ListarParticipantesNoEventoAdapter.OnParticipanteNoEventoClickListener() {
            @Override
            public void onParticipanteNoEventoClick(View view, int position) {

                Intent intent2 = new Intent(ListarDetalhesEventosActivity.this,ListarDetalhesParticipantesActivity.class);
                intent2.putExtra(ListarDetalhesEventosActivity.POSICAO_PARTICIPANTE,position);
                startActivity(intent2);

            }
            @Override
            public void onLongParticipanteNoEventoClick(View view, int position) {

                Integer id_participante  = ParticipanteDAO.getInstance()
                        .getParticipantes().get(position).getID();


                EventoParticipanteDAO.getInstance().removeParticipanteEvento(id_evento,id_participante);


                adapter.notifyItemRemoved(position);

            }
        });




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ListarDetalhesEventosActivity.REQUEST_EDITAR_EVENTO && resultCode== Activity.RESULT_OK){
            updateData();
        }

    }

    protected void updateData(){
        txt_titulo.setText(EventoDAO.getInstance().getEventoById(id_evento).getTitulo());
        txt_dia.setText(EventoDAO.getInstance().getEventoById(id_evento).getData());
        txt_hora.setText(EventoDAO.getInstance().getEventoById(id_evento).getHora());
        txt_facilitador.setText(EventoDAO.getInstance().getEventoById(id_evento).getFacilitador());
        txt_descricao.setText(EventoDAO.getInstance().getEventoById(id_evento).getDescricao());
    }

}
