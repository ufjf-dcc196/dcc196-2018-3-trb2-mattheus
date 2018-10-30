package com.example.mattheus.trabalho_01_dcc196;

import java.util.ArrayList;

public class Participante {
    private String nome, email, cpf;
    private ArrayList<Evento> eventos;

    public Participante(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        eventos = new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public Evento getEvento(int index) {
        return eventos.get(index);
    }

    public void addEvento(Evento evento) {
        eventos.add(evento);
    }

    public void rmvEvento(Integer id){
        eventos.remove(id);
    }
}
