package com.example.mattheus.trabalho_01_dcc196;

import java.util.ArrayList;

public class Participante {
    private Integer id;
    private String nome, email, cpf;
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Evento> eventosNaoCadstrados = new ArrayList<>();

    public Participante(Integer id, String nome, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
    public Participante(){
    }

    public Integer getID() {
        return this.id;
    }

    public void setID(Integer id) {
        this.id = id;
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

    public void addEvento(Evento evento) {
        if(!this.eventos.contains(evento))this.eventos.add(evento);
    }

    public void removeEvento(Evento evento){
        if(this.eventos.contains(evento))this.eventos.remove(evento);
    }

    public void addEventoNaoCadastrado(Evento evento) {
        if(!this.eventosNaoCadstrados.contains(evento))this.eventosNaoCadstrados.add(evento);
    }
    public void removeEventoNaoCadastrado(Evento evento) {
        if(this.eventosNaoCadstrados.contains(evento))this.eventosNaoCadstrados.remove(evento);
    }

    public ArrayList<Evento> getEventosNaoCadstrados() {
        return eventosNaoCadstrados;
    }

    public void setEventosNaoCadstrados(ArrayList<Evento> eventosNaoCadstrados) {
        this.eventosNaoCadstrados = eventosNaoCadstrados;
    }


}
