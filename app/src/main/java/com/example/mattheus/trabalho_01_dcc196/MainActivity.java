package com.example.mattheus.trabalho_01_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static ListarEventosAdapter adapter;

    private Button btn_cadastrar_evento, btn_listar_participante, btn_cadastrar_participante;

    public static final int REQUEST_CADASTRO_PARTICIPANTE = 1;
    public static final int REQUEST_CADASTRO_EVENTO = 2;
    public static final int REQUEST_LISTAR_EVENTO = 3;
    public static final int REQUEST_LISTAR_PARTICIPANTE = 4;

    public static final String POSICAO_EVENTO = "Posição do Evento";
    private RecyclerView recyclerView;

    public static void Dale() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EventoDAO.getInstance().inicializarDBHelper(getApplicationContext());
        EventoParticipanteDAO.getInstance().inicializarDBHelper(getApplicationContext());

        btn_cadastrar_evento = findViewById(R.id.btn_Cadastrar_Evento);
        btn_cadastrar_participante = findViewById(R.id.btn_Cadastrar_Participante);
        btn_listar_participante = findViewById(R.id.btn_View_Participantes);

        recyclerView = findViewById(R.id.rcl_View_Eventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListarEventosAdapter(EventoDAO.getInstance().getEventos());
        recyclerView.setAdapter(adapter);



        adapter.setOnEventoClickListener(new ListarEventosAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View view, int position) {
                Integer id_evento = EventoDAO.getInstance().getEventos().get(position).getID();
                Intent intent = new Intent(MainActivity.this, ListarDetalhesEventosActivity.class);
                intent.putExtra(MainActivity.POSICAO_EVENTO,id_evento);
                startActivityForResult(intent, MainActivity.REQUEST_LISTAR_EVENTO);
            }

            @Override
            public void onLongEventoClick(View view, int position) {
                Integer id_evento = EventoDAO.getInstance().getEventos().get(position).getID();

                EventoDAO.getInstance().removeEvento(EventoDAO.getInstance().getEventos().get(position));
                EventoParticipanteDAO.getInstance().removerAllParticipantesEvento(id_evento);

                adapter.setEventos(EventoDAO.getInstance().getEventos());
                adapter.notifyItemRemoved(position);
            }

        });

        btn_cadastrar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarParticipanteActivity.class);
                startActivityForResult(intent, MainActivity.REQUEST_CADASTRO_PARTICIPANTE);
            }
        });

        btn_cadastrar_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarEventoActivity.class);
                startActivityForResult(intent, MainActivity.REQUEST_CADASTRO_EVENTO);
            }
        });

        btn_listar_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarParticipantesActivity.class);
                startActivityForResult(intent, MainActivity.REQUEST_LISTAR_PARTICIPANTE);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.setEventos(EventoDAO.getInstance().getEventos());
    }

}
