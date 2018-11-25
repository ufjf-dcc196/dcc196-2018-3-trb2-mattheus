package com.example.mattheus.trabalho_01_dcc196;

import android.provider.BaseColumns;
import android.widget.ExpandableListView;

public class TrabalhoContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";

    public static final class EventoTable implements BaseColumns {
        public static final String TABLE_NAME = "evento";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_HORA = "hora";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_FACILITADOR = "facilitador";


        public static final String SQL_CREATE_EVENTO = "CREATE TABLE " + EventoTable.TABLE_NAME + " (" +
                EventoTable._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
                EventoTable.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
                EventoTable.COLUMN_NAME_DATA + TEXT_TYPE + SEP +
                EventoTable.COLUMN_NAME_HORA + TEXT_TYPE + SEP +
                EventoTable.COLUMN_NAME_DESCRICAO + TEXT_TYPE + SEP +
                EventoTable.COLUMN_NAME_FACILITADOR + TEXT_TYPE + ")";
        public static final String SQL_DROP_EVENTO = "DROP TABLE IF EXISTS" + EventoTable.TABLE_NAME;
        public final static String SQL_DELETE_EVENTO= "DELETE FROM" + EventoTable.TABLE_NAME+ "WHERE id=" + EventoTable._ID;

    }






    public static final class ParticipanteTable implements BaseColumns {
        public static final String TABLE_NAME = "participante";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_CPF = "cpf";


        public static final String SQL_CREATE_PARTICIPANTE = "CREATE TABLE " + ParticipanteTable.TABLE_NAME + " (" +
                ParticipanteTable._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + SEP +
                ParticipanteTable.COLUMN_NAME_NOME + TEXT_TYPE + SEP +
                ParticipanteTable.COLUMN_NAME_EMAIL + TEXT_TYPE + SEP +
                ParticipanteTable.COLUMN_NAME_CPF + TEXT_TYPE + ")";
        public static final String SQL_DROP_PARTICIPANTE = "DROP TABLE IF EXISTS" + ParticipanteTable.TABLE_NAME;
        public static final String SQL_DELETE_PARTICIPANTE = "DELETE FROM" + ParticipanteTable.TABLE_NAME+ "WHERE id=" + ParticipanteTable._ID;;

    }




    public final class EventoParticipanteTable implements BaseColumns {
        public final static String TABLE_NAME = "Evento_Participante";
        public final static String COLUMN_NAME_ID_EVENTO = "ID_EVENTO";
        public final static String COLUMN_NAME_ID_PARTICIPANTE = "ID_PARTICIPANTE";

        public final static String SQL_CREATE_EVENTO_PARTICIPANTE  = "CREATE TABLE "+EventoParticipanteTable.TABLE_NAME+" ("
                + EventoParticipanteTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventoParticipanteTable.COLUMN_NAME_ID_EVENTO+ " INTEGER,"
                + EventoParticipanteTable.COLUMN_NAME_ID_PARTICIPANTE+ " INTEGER "
                +")";

        public final static String SQL_DROP_EVENTO_PARTICIPANTE = "DROP TABLE IF EXISTS "+EventoParticipanteTable.TABLE_NAME;
        public static final String SQL_DELETE_EVENTO_PARTICIPANTE = "DELETE FROM" + EventoParticipanteTable.TABLE_NAME+ "WHERE id=" + EventoParticipanteTable._ID;;

    }


}
