package com.example.mattheus.trabalho_01_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListarParticipantesActivity extends AppCompatActivity {
    private ListarParticipantesAdapter adapter;
    private RecyclerView recyclerView;
    public static final String POSICAO_PARTICIPANTE = "Posição do Participante";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_participantes);


        recyclerView = findViewById(R.id.rclv_listar_participantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListarParticipantesAdapter(Singleton.getInstance().getParticipantes());
        recyclerView.setAdapter(adapter);

        adapter.setOnParticipanteClickListener(new ListarParticipantesAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {
                Intent intent = new Intent(ListarParticipantesActivity.this,ListarDetalhesParticipantesActivity.class);
                intent.putExtra(ListarParticipantesActivity.POSICAO_PARTICIPANTE,position);
                startActivity(intent);
            }

            @Override
            public void onLongParticipanteClick(View view, int position) {
                Participante p = Singleton.getInstance().getParticipantes().get(position);
                Singleton.getInstance().removeParticipanteEvento(p);
                Singleton.getInstance().removeParticipante(p);
                adapter.notifyItemRemoved(position);
            }
        });

    }
}
