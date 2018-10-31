package com.example.mattheus.trabalho_01_dcc196;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String titulo, data, hora, faci, desc;
    private ArrayList<Participante> participantes;

    public Evento(String titulo, String data, String hora, String facilitador, String desc) {
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.faci = facilitador;
        this.desc = desc;
        participantes = new ArrayList();
    }

    public Evento(){

    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return desc;
    }

    public void setDescricao(String desc) {
        this.desc = desc;
    }

    public String getFacilitador() {
        return this.faci;
    }

    public void setFacilitador(String facilitador) {
        this.faci = facilitador;
    }

    public ArrayList getParticipantes() {
        return participantes;
    }

    public Participante getParticipante(int index) {
        return participantes.get(index);
    }

    public void addParticipante(Participante p) {
        participantes.add(p);
    }
    public void removeParticipante(Participante p){
        participantes.remove(p);
    }
}
