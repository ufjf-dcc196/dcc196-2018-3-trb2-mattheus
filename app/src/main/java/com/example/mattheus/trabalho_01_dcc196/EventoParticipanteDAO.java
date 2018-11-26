package com.example.mattheus.trabalho_01_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public ArrayList<Participante> getEventoParticipantes(int id_Evento) {
        cursor = getAllParticipantesEventosBanco(id_Evento,"Participante");
        ArrayList<Participante> participantes = new ArrayList<>();

        int indexNomeParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME);
        int indexCpfParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF);
        int indexEmailParticipante = cursor.
                getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL);
        int indexIdParticipante = cursor.getColumnIndexOrThrow(TrabalhoContract.ParticipanteTable._ID);

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Participante temp = new Participante();
            temp.setNome(cursor.getString(indexNomeParticipante));
            temp.setCPF(cursor.getString(indexCpfParticipante));
            temp.setEmail(cursor.getString(indexEmailParticipante));
            temp.setID(Integer.parseInt(cursor.getString(indexIdParticipante)));
            participantes.add(temp);
        }
        return participantes;
    }
    public ArrayList<Evento> getParticipanteEventosNaoInscritos(int id_Participante){
        Cursor cursor = getParticipanteEventosNaoInscritosBanco(id_Participante);
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

    public ArrayList<Evento> getParticipanteEventos(int id_Participante) {
        cursor = getAllParticipantesEventosBanco(id_Participante,"Evento");
        ArrayList<Evento> eventos = new ArrayList<>();
        int indexTituloEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_TITULO);
        int indexDataEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_DATA);
        int indexHoraEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_HORA);
        int indexFacilitadorEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR);
        int indexDescricaoEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO);
        int indexIdEvento = cursor.getColumnIndexOrThrow(TrabalhoContract.EventoTable._ID);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Evento temp = new Evento();
            temp.setTitulo(cursor.getString(indexTituloEvento));
            temp.setData(cursor.getString(indexDataEvento));
            temp.setHora(cursor.getString(indexHoraEvento));
            temp.setFacilitador(cursor.getString(indexFacilitadorEvento));
            temp.setDescricao(cursor.getString(indexDescricaoEvento));
            temp.setID(Integer.parseInt(cursor.getString(indexIdEvento)));
            eventos.add(temp);
        }
        return eventos;
    }

    public void addParticpanteEvento(int idEvento, int idParticipante){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_EVENTO, idEvento);
        valores.put(TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_PARTICIPANTE, idParticipante);
        db.insert(TrabalhoContract.EventoParticipanteTable.TABLE_NAME,null, valores);
    }



    public void removeParticipanteEvento(Integer idEvento, Integer idParticipante){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        String[] ids_Where = new String[] {idEvento.toString(),idParticipante.toString()};

        db.delete(TrabalhoContract.EventoParticipanteTable.TABLE_NAME,"ID_EVENTO = ? AND ID_PARTICIPANTE = ?",ids_Where);
    }

    public void removerAllParticipantesEvento(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("EventoParticipante","ID_EVENTO=? "
                ,new String[]{String.valueOf(id)});

    }


    private Cursor getAllParticipantesEventosBanco(int id, String argumento) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c;
        if("Participante".equals(argumento)){
            String[] visao = {
                    TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_EVENTO,
                    TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_PARTICIPANTE,
                    TrabalhoContract.EventoParticipanteTable._ID,
                    TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME,
                    TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF,
                    TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL,
                    TrabalhoContract.ParticipanteTable._ID
            };
            String sort = TrabalhoContract.EventoParticipanteTable._ID+ " DESC";
            c = db.query(TrabalhoContract.EventoParticipanteTable.TABLE_NAME, visao,"where ID_PARTICIPANTE = "+id,null,null,null, sort);
        }else{
            String[] visao = {
                    TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_EVENTO,
                    TrabalhoContract.EventoParticipanteTable.COLUMN_NAME_ID_PARTICIPANTE,
                    TrabalhoContract.EventoParticipanteTable._ID,
                    TrabalhoContract.EventoTable.COLUMN_NAME_TITULO,
                    TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO,
                    TrabalhoContract.EventoTable.COLUMN_NAME_DATA,
                    TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR,
                    TrabalhoContract.EventoTable.COLUMN_NAME_HORA,
                    TrabalhoContract.EventoTable._ID
            };
            String sort = TrabalhoContract.EventoParticipanteTable._ID+ " DESC";
            c = db.query(TrabalhoContract.EventoParticipanteTable.TABLE_NAME, visao,
                    "where ID_EVENTO = "+id,null,null,null, sort);

        }
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }

    private Cursor getParticipanteEventosNaoInscritosBanco(int idParticipante) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        String MY_QUERY =
                "SELECT * FROM "+TrabalhoContract.EventoParticipanteTable.TABLE_NAME
                        +" as  EvPart inner join "+TrabalhoContract.EventoTable.TABLE_NAME+
                        " as Ev WHERE EvPart.ID_PARTICIPANTE !=?";

        cursor= db.rawQuery(MY_QUERY, new String[]{String.valueOf(idParticipante)});
        Log.i("SQLTEST", "getCursorSeriado: "+cursor.getCount());

        return cursor;
    }



}
