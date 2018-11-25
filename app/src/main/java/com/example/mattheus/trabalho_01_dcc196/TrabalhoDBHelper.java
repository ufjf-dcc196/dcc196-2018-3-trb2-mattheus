package com.example.mattheus.trabalho_01_dcc196;

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
