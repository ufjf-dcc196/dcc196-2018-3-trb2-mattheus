package com.example.mattheus.trabalho_01_dcc196;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private static List<Evento> eventos;
    private static List<Participante> participantes;

    public static List<Evento> getInstanceEvento(){
        if (eventos == null){
            eventos = new ArrayList<>();
        }
        return eventos;
    }

    public static List<Participante> getInstanceParticipante(){
        if (participantes == null){
            participantes = new ArrayList<>();
        }
        return participantes;
    }

}
