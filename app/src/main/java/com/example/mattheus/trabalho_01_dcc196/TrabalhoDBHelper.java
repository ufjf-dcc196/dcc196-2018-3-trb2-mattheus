package com.example.mattheus.trabalho_01_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TrabalhoDBHelper extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "TrabalhoDBHelper.db";

    public TrabalhoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TrabalhoContract.EventoTable.SQL_CREATE_EVENTO);
        db.execSQL(TrabalhoContract.ParticipanteTable.SQL_CREATE_PARTICIPANTE);
        db.execSQL(TrabalhoContract.EventoParticipanteTable.SQL_CREATE_EVENTO_PARTICIPANTE);

        db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_NOME, "Mattheus Soares Santos");
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_CPF, "111.222.333-44");
        valores.put(TrabalhoContract.ParticipanteTable.COLUMN_NAME_EMAIL, "mattheus@soares.com.br");
        db.insert(TrabalhoContract.ParticipanteTable.TABLE_NAME,null, valores);

        ContentValues valores2 = new ContentValues();
        valores2.put(TrabalhoContract.EventoTable.COLUMN_NAME_TITULO, "Evento 0");
        valores2.put(TrabalhoContract.EventoTable.COLUMN_NAME_DESCRICAO, "Teste do banco de dados, evento 0");
        valores2.put(TrabalhoContract.EventoTable.COLUMN_NAME_DATA, "01/11/2020");
        valores2.put(TrabalhoContract.EventoTable.COLUMN_NAME_FACILITADOR, "Azaghal");
        valores2.put(TrabalhoContract.EventoTable.COLUMN_NAME_HORA, "00:00");
        db.insert(TrabalhoContract.EventoTable.TABLE_NAME,null, valores2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TrabalhoContract.EventoTable.SQL_DROP_EVENTO);
        db.execSQL(TrabalhoContract.ParticipanteTable.SQL_DROP_PARTICIPANTE);
        db.execSQL(TrabalhoContract.EventoParticipanteTable.SQL_DROP_EVENTO_PARTICIPANTE);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion,newVersion);
    }

}
