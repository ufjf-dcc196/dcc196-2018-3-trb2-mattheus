package com.example.mattheus.trabalho_01_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ParticipanteDAO {
    private static ParticipanteDAO instance = new ParticipanteDAO();
    private TrabalhoDBHelper dbHelper;
    private Cursor cursor;
    private boolean done = false;

    private ParticipanteDAO() {
    }

    public static ParticipanteDAO getInstance(){
        return instance;
    }


    public void insertBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME, "Mattheus Soares Santos");
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF, "111.222.333-44");
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL, "mattheus@soares.com.br");
        db.insert(TrabalhoContract.ParticipanteTable.TABLE_NAME,null, valores);
    }
    public void inicializarDBHelper(Context c){
        dbHelper = new TrabalhoDBHelper(c);
        if(!done){
            insertBanco();
            done = true;
        }
    }

    public ArrayList<Participante> getParticipantes() {
        ArrayList<Participante> participantes = new ArrayList<>();
        cursor = getAllParticipantesBanco();
        int indexNomeParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME);
        int indexCpfParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF);
        int indexEmailParticipante = cursor.
                getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL);
        int indexIdParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable._ID);
        if(cursor.moveToFirst()){
            do{
                Participante temp = new Participante();
                temp.setNome(cursor.getString(indexNomeParticipante));
                temp.setCPF(cursor.getString(indexCpfParticipante));
                temp.setEmail(cursor.getString(indexEmailParticipante));
                temp.setID(Integer.parseInt(cursor.getString(indexIdParticipante)));
                participantes.add(temp);
            }while (cursor.moveToNext());
        }
        return participantes;
    }
    public Participante getParticipanteById(Integer idParticipante) {
        Participante participante  = null;
        cursor = getAllParticipantesBanco();
        int indexNomeParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME);
        int indexCpfParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF);
        int indexEmailParticipante = cursor.
                getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL);
        int indexIdParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable._ID);
        if(cursor.moveToFirst()){
            participante = new Participante();
            participante.setNome(cursor.getString(indexNomeParticipante));
            participante.setCPF(cursor.getString(indexCpfParticipante));
            participante.setEmail(cursor.getString(indexEmailParticipante));
            participante.setID(Integer.parseInt(cursor.getString(indexIdParticipante)));
        }
        return participante;
    }

    public void addParticipante(Participante p){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME, p.getNome());
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF, p.getCPF());
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL, p.getEmail());
        db.insert(TrabalhoContract.ParticipanteTable.TABLE_NAME,null, valores);
    }


    public void removeParticipante(Participante indice){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TrabalhoContract.ParticipanteTable.TABLE_NAME,"_ID=?",new String[]{String.valueOf(indice.getID())});

    }

    public void removeAllParticipanteEvento(Evento e,Participante participantes){
        e.removeParticipante(participantes.getID());
    }

    private Cursor getAllParticipantesBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME,
                TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF,
                TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL,
                TrabalhoContract.ParticipanteTable._ID
        };
        String sort = TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME+ " DESC";
        Cursor c = db.query(TrabalhoContract.ParticipanteTable.TABLE_NAME, visao,
                null,null,null,null, sort);
        return c;
    }

}
