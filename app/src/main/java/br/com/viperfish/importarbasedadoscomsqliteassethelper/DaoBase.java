package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ddark on 22/07/17.
 */

public abstract class DaoBase {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static String TAG = "DaoBase"; // LogCat


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

    protected boolean inserir(String tabela, ContentValues values) {

        long resultado = 0;

        if (tabela != null && !tabela.isEmpty() && values != null && values.size() > 0) {

            try {

                abriConexaoEmModoEscrita();
                resultado = getDatabase().insert(tabela, null, values);

            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "ERRO Buscar Marca");

            } finally {
                fecharConexao();
            }

        } else {
            Log.i(TAG, "ERRO. Informe o nome da tabela e/ou seus valores");
        }

        return resultado > 0;
    }

    protected boolean atualizar(String tabela, ContentValues values, String whereClause, String[] whereArgs) {

        int totalRegistrosAtualizados = 0;

        try {

            abriConexaoEmModoEscrita();
            totalRegistrosAtualizados = getDatabase().update(tabela, values, whereClause, whereArgs);

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Atualizar");

        } finally {
            fecharConexao();
        }

        return totalRegistrosAtualizados > 0;
    }

    protected boolean deletar(String table, String whereClause, String[] whereArgs) {

        int resultado = 0;

        try {

            abrirConexaoEmModoLeitura();
            resultado = getDatabase().delete(table, whereClause, whereArgs);

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO AO DELETAR");

        } finally {
            fecharConexao();
        }

        return resultado > 0;
    }

    /**
     *
     * Transforma um {@link Cursor} em uma objeto entidade
     *
     * @param cursor
     * @param <T>
     * @return Objeto
     */
    protected abstract <T> T transformaCursorEmEntidade(Cursor cursor);
}
