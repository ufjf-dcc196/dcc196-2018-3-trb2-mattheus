package com.example.mattheus.trabalho_01_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class EventoDAO {
    private static EventoDAO instance = new EventoDAO();
    private TrabalhoDBHelper dbHelper;
    private Cursor cursor;
    private boolean done = false;
    private EventoDAO() {
    }
    public static EventoDAO getInstance(){
        return instance;
    }

    private void insertBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_TITULO, "Evento 0");
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO, "Teste do banco de dados, evento 0");
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_DATA, "01/11/2020");
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR, "Azaghal");
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_HORA, "00:00");
        db.insert(TrabalhoContract.EventoTable.TABLE_NAME,null, valores);
    }


    public void inicializarDBHelper(Context c){
        dbHelper = new TrabalhoDBHelper(c);
        if(!done){
            insertBanco();
            done = true;
        }
    }

    public ArrayList<Evento> getEventos() {
        cursor = getAllEventosBanco();
        ArrayList<Evento> eventos = new ArrayList<>();
        int indexTituloEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_TITULO);
        int indexDataEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_DATA);
        int indexHoraEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_HORA);
        int indexFacilitadorEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR);
        int indexDescricaoEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO);
        int indexIdEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable._ID);
        if(cursor.moveToFirst()){
            do{
                Evento temp = new Evento();
                temp.setTitulo(cursor.getString(indexTituloEvento));
                temp.setData(cursor.getString(indexDataEvento));
                temp.setHora(cursor.getString(indexHoraEvento));
                temp.setFacilitador(cursor.getString(indexFacilitadorEvento));
                temp.setDescricao(cursor.getString(indexDescricaoEvento));
                temp.setID(Integer.parseInt(cursor.getString(indexIdEvento)));
                eventos.add(temp);
            }while (cursor.moveToNext());
        }
        return eventos;
    }


    public void addEvento(Evento e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_TITULO, e.getTitulo());
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO, e.getDescricao());
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_DATA, e.getData());
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR, e.getFacilitador());
        valores.put(TrabalhoContract.EventoTable.COLUMN_NAME_HORA, e.getHora());
        db.insert(TrabalhoContract.EventoTable.TABLE_NAME,null, valores);

    }
    public void removeEvento(Evento e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows=db.delete("TABLE_NAME","_ID=?",new String[]{String.valueOf(e.getID())});
    }

    public int getIndiceEvento(Evento e){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                TrabalhoContract.EventoTable.COLUMN_NAME_TITULO,
                TrabalhoContract.EventoTable._ID
        };
        String sort = TrabalhoContract.EventoTable.COLUMN_NAME_DATA+ " DESC";

        Cursor c = db.query(TrabalhoContract.EventoTable.TABLE_NAME, visao,
                "Where TITULO= "+e.getTitulo(),null,
                null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        if(c.moveToFirst()){
            return c.getInt(1);
        }
        return -1;
    }






    private Cursor getAllEventosBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                TrabalhoContract.EventoTable.COLUMN_NAME_TITULO,
                TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO,
                TrabalhoContract.EventoTable.COLUMN_NAME_DATA,
                TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR,
                TrabalhoContract.EventoTable.COLUMN_NAME_HORA,
                TrabalhoContract.EventoTable._ID
        };
        String sort = TrabalhoContract.EventoTable.COLUMN_NAME_DATA+ " DESC";
        Cursor c = db.query(TrabalhoContract.EventoTable.TABLE_NAME, visao,
                null,null,null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }


}
