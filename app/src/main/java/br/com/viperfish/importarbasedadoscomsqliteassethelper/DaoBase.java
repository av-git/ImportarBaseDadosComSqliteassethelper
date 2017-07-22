package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ddark on 22/07/17.
 */

public class DaoBase {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    public DaoBase(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * abre uma conexao com banco dados para escrita (Insert, Update).
     */
    protected void abriConexaoEmModoEscrita() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * abre uma conexao com banco dados somente leitura.
     */
    protected void abrirConexaoEmModoLeitura() {
        this.database = openHelper.getReadableDatabase();
    }

    /**
     * Close the database connection.
     */
    protected void fecharConexao() {
        if (this.database != null) {
            this.database.close();
        }
    }

    protected SQLiteDatabase getDatabase() {
        return database;
    }

    protected long inserir(String tabela, ContentValues values) {

        abriConexaoEmModoEscrita();
        long resultado = getDatabase().insert(tabela, null, values);
        fecharConexao();

        return resultado;
    }

    protected int atualizar(String tabela, ContentValues values, String whereClause, String[] whereArgs) {

        abriConexaoEmModoEscrita();
        int resultado = getDatabase().update(tabela, values, whereClause, whereArgs);
        fecharConexao();

        return resultado;
    }

    protected int deletar (String table, String whereClause, String[] whereArgs) {

        abrirConexaoEmModoLeitura();
        int resultado = getDatabase().delete(table, whereClause, whereArgs);
        fecharConexao();

        return resultado;
    }
}
