package com.example.mattheus.trabalho_01_dcc196;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListarParticipantesAdapter extends RecyclerView.Adapter<ListarParticipantesAdapter.ViewHolder> {
    private ArrayList<Participante> participantes = new ArrayList<>();
    private OnParticipanteClickListener listener;


    public interface OnParticipanteClickListener {
        void onParticipanteClick(View view, int position);
        void onLongParticipanteClick(View view, int position);
    }

    public void setOnParticipanteClickListener(OnParticipanteClickListener listener){
        this.listener = listener;
    }

    public ListarParticipantesAdapter(ArrayList<Participante> participantes) {
        this.participantes = participantes;

    }


    @NonNull
    @Override
    public ListarParticipantesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstParticipantesView = inflater.inflate(R.layout.recycle_view_listar_participantes, viewGroup, false);
        ViewHolder holder = new ViewHolder(lstParticipantesView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListarParticipantesAdapter.ViewHolder holder, int position) {
        holder.txtNomeParticpante.setText(participantes.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return participantes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNomeParticpante;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeParticpante= itemView.findViewById(R.id.txt_lista_dos_participantes);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onLongParticipanteClick(view, position);
                        }
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onParticipanteClick(v, position);
                        }
                    }
                }
            });


        }

        @Override
        public void onClick(View view){
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onParticipanteClick(view, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongParticipanteClick(view, position);
                }
            }
            return true;
        }
    }
}

