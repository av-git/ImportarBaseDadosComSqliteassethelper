package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by ddark on 21/07/17.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "mpb.db";
    private static final int DATABASE_VERSION = 1;

    public static class Marca {
        public static final String TABELA = "MARCA";
        public static final String _ID = "_id";
        public static final String NOME =  "nome";

        public static final String[] COLUNAS = new String[] {
                _ID, NOME
        };
    }


    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
