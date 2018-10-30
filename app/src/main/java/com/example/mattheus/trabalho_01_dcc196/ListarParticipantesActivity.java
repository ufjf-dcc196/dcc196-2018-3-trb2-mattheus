package com.example.mattheus.trabalho_01_dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListarParticipantesActivity extends AppCompatActivity {
    private ListarParticipantesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_participantes);


        recyclerView = findViewById(R.id.rclv_listar_participantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListarParticipantesAdapter(Singleton.getInstance().getParticipantes());
        recyclerView.setAdapter(adapter);




    }
}
