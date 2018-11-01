package com.example.mattheus.trabalho_01_dcc196;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListarEventosCadastradosAdapter extends RecyclerView.Adapter<ListarEventosCadastradosAdapter.ViewHolder>{


    private ArrayList<Evento> eventos = new ArrayList<>();
    private OnEventoCadastradosClickListener listener;

    public interface OnEventoCadastradosClickListener {
        void onEventoCadastradosClick(View view, int position);
    }

    public void setOnEventoCadastradosClickListener(OnEventoCadastradosClickListener listener){
        this.listener = listener;
    }

    public ListarEventosCadastradosAdapter(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }


    @NonNull
    @Override
    public ListarEventosCadastradosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstEventoView;
        lstEventoView = inflater.inflate(R.layout.recycle_view_listar_eventos_cadastrados, viewGroup, false);
        ListarEventosCadastradosAdapter.ViewHolder holderView = new ListarEventosCadastradosAdapter.ViewHolder(lstEventoView);
        return holderView;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTituloEvento.setText(eventos.get(position).getTitulo());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtTituloEvento;
        public ViewHolder(View itemView) {
            super(itemView);

            txtTituloEvento = itemView.findViewById(R.id.txt_lista_dos_eventos_cadastrados);



            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onEventoCadastradosClick(v, position);
                        }
                    }
                }
            });


        }

        @Override
        public void onClick(View v){
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onEventoCadastradosClick(v, position);
                }
            }
        }

    }

}
