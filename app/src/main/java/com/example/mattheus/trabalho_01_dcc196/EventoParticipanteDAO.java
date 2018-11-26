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
            Integer id = cursor.getInt(cursor.getColumnIndex("ID_PARTICIPANTE"));
            participantes.add(ParticipanteDAO.getInstance().getParticipantes().get(id));
        }
        return participantes;
    }

    public ArrayList<Evento> getEventosDoParticipante(Integer id_participante){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        ArrayList<Evento> eventos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT ID_EVENTO FROM " + TrabalhoContract.EventoParticipanteTable.TABLE_NAME +
                " WHERE ID_PARTICIPANTE  =" + id_participante, null);
        while (c.moveToNext()){
            Integer id = cursor.getInt(cursor.getColumnIndex("ID_EVENTO"));
            eventos.add(EventoDAO.getInstance().getEventos().get(id));
        }
        return eventos;
    }

    public ArrayList<Evento> getEventosNaoInscritosDoParticipante(Integer id_participante){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        ArrayList<Evento> eventos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT ID_EVENTO FROM " + TrabalhoContract.EventoParticipanteTable.TABLE_NAME +
                " WHERE ID_PARTICIPANTE  !=" + id_participante, null);
        while (c.moveToNext()){
            Integer id = cursor.getInt(cursor.getColumnIndex("ID_EVENTO"));
            eventos.add(EventoDAO.getInstance().getEventos().get(id));
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
        db.delete(TrabalhoContract.EventoParticipanteTable.TABLE_NAME,"ID_EVENTO=? "
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

