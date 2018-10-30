package com.example.mattheus.trabalho_01_dcc196;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private static Singleton instance = new Singleton();

    private List<Evento> eventos;
    private List<Participante> participantes;


    private Singleton() {

    }
    public static Singleton getInstance(){
        return instance;
    }


    public List<Evento> getInstanceEvento(){
        if (eventos == null){
            eventos = new ArrayList<>();
        }
        return eventos;
    }

    public List<Participante> getInstanceParticipante(){
        if (participantes == null){
            participantes = new ArrayList<>();
        }
        return participantes;
    }

}
