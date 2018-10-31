package com.example.mattheus.trabalho_01_dcc196;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListarParticipantesNoEventoAdapter extends RecyclerView.Adapter<ListarParticipantesNoEventoAdapter.ViewHolder> {
    private ArrayList<Participante> participantes = new ArrayList<>();
    private OnParticipanteNoEventoClickListener listener;


    public interface OnParticipanteNoEventoClickListener {
        void onParticipanteNoEventoClick(View view, int position);
        void onLongParticipanteNoEventoClick(View view, int position);
    }

    public void setOnParticipanteNoEventoClickListener(OnParticipanteNoEventoClickListener listener){
        this.listener = listener;
    }

    public ListarParticipantesNoEventoAdapter(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }


    @NonNull
    @Override
    public ListarParticipantesNoEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstParticipantesNoEventoView = inflater.inflate(R.layout.recycle_view_listar_participantes_no_evento, viewGroup, false);
        ViewHolder holder = new ViewHolder(lstParticipantesNoEventoView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListarParticipantesNoEventoAdapter.ViewHolder holder, int position) {
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

            txtNomeParticpante= itemView.findViewById(R.id.txt_lista_dos_participantes_no_evento);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onLongParticipanteNoEventoClick(view, position);
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
                            listener.onParticipanteNoEventoClick(v, position);
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
                    listener.onParticipanteNoEventoClick(view, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongParticipanteNoEventoClick(view, position);
                }
            }
            return true;
        }
    }
}

