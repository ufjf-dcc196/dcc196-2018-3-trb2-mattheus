package com.example.mattheus.trabalho_01_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class EventoParticipanteDAO {

    private static EventoParticipanteDAO instance = new EventoParticipanteDAO();
    private TrabalhoDBHelper dbHelper;
    private Cursor cursor;
    private boolean done = false;
    private EventoParticipanteDAO() {
    }
    public static EventoParticipanteDAO getInstance(){
        return instance;
    }

    public void inicializarDBHelper(Context c){
        dbHelper = new TrabalhoDBHelper(c);
    }

    public ArrayList<Participante> getParticipantesNoEvento(Integer id_evento){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        ArrayList<Participante> participantes = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT ID_PARTICIPANTE FROM " + TrabalhoContract.EventoParticipanteTable.TABLE_NAME +
                " WHERE ID_EVENTO =" + id_evento, null);
        while (c.moveToNext()){
            Integer id = c.getInt(c.getColumnIndex("ID_PARTICIPANTE"));
            participantes.add(ParticipanteDAO.getInstance().getParticipanteById(id));
        }
        return participantes;
    }

    public ArrayList<Evento> getEventosDoParticipante(Integer id_participante){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        ArrayList<Evento> eventos = new ArrayList<>();
        String query = "SELECT * FROM Evento, EventoParticipante WHERE ID_PARTICIPANTE  = ? AND Evento._ID = ID_EVENTO";

        Cursor c = db.rawQuery( query, new String[]{String.valueOf(id_participante)});
        while (c.moveToNext()){
            Integer id = c.getInt(c.getColumnIndex(TrabalhoContract.EventoTable._ID));
            eventos.add(EventoDAO.getInstance().getEventoById(id));
        }
        return eventos;
    }

    public ArrayList<Evento> getEventosNaoInscritosDoParticipante(Integer id_participante){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        ArrayList<Evento> eventos = new ArrayList<>();
        String query = "SELECT ID_EVENTO FROM Evento, EventoParticipante WHERE ID_PARTICIPANTE  != ? AND Evento._ID = ID_EVENTO";

        Cursor c = db.rawQuery( query, new String[]{String.valueOf(id_participante)});
        if(!c.moveToNext()){
            eventos = EventoDAO.getInstance().getEventos();
        }
        while (c.moveToNext()){
            Integer id = c.getInt(c.getColumnIndex(TrabalhoContract.EventoTable._ID));
            eventos.add(EventoDAO.getInstance().getEventoById(id));
        }
        return eventos;
    }

    public void removeParticipanteEvento(Integer idEvento, Integer idParticipante){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        String[] ids_Where = new String[] {idEvento.toString(),idParticipante.toString()};
        db.delete(TrabalhoContract.EventoParticipanteTable.TABLE_NAME,"ID_EVENTO = ? AND ID_PARTICIPANTE = ?",ids_Where);
    }

    public void removerAllParticipantesEvento(int id_evento){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        db.delete(TrabalhoContract.EventoParticipanteTable.TABLE_NAME,"ID_EVENTO = ? "
                ,new String[]{String.valueOf(id_evento)});

    }

    public void addParticpanteEvento(int idEvento, int idParticipante){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_EVENTO, idEvento);
        valores.put(TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_PARTICIPANTE, idParticipante);
        db.insert(TrabalhoContract.EventoParticipanteTable.TABLE_NAME,null, valores);
    }


}

