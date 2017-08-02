package br.com.viperfish.importarbasedadoscomsqliteassethelper.categoria;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.importarbasedadoscomsqliteassethelper.DaoBase;


/**
 * Created by AV on 22/11/2016.
 */

public class CategoriaDao extends DaoBase {

    private static CategoriaDao instance;
    private static String TAG = "CategoriaDao"; // LogCat

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private CategoriaDao(Context context) {
        super(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static CategoriaDao getInstance(Context context) {
        if (instance == null) {
            instance = new CategoriaDao(context);
        }
        return instance;
    }

    public List<Categoria> buscarTodos() {

        Cursor cursor = null;
        List<Categoria> categorias = new ArrayList<Categoria>();

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(ICategoriaSchema.TABELA,
                    ICategoriaSchema.COLUNAS,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Categoria categoria = transformaCursorEmEntidade(cursor);
                categorias.add(categoria);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Categoria");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return categorias;
    }

    /**
     * Update the contact details.
     *
     */
    public boolean atualizar(Categoria categoria) {
        //TODO NAO IMPLEMENTADO
        return false;
    }

    /**
     * Delete the provided contact.
     *
     * @param categoria the contact to delete
     */
    public boolean deletar(Categoria categoria) {
        //TODO NAO IMPLEMENTADO
        return false;
    }

    protected Categoria transformaCursorEmEntidade(Cursor cursor) {

        Categoria categoria = new Categoria(

                cursor.getLong(cursor.getColumnIndex(
                        ICategoriaSchema._ID)),

                cursor.getLong(cursor.getColumnIndex(
                        ICategoriaSchema.IDPAI)),

                cursor.getString(cursor.getColumnIndex(
                        ICategoriaSchema.NOME))
        );

        return categoria;
    }

    public Categoria buscarPorId(Integer id) {

        Categoria categoria = null;
        Cursor cursor = null;

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(ICategoriaSchema.TABELA,
                    ICategoriaSchema.COLUNAS,
                    ICategoriaSchema._ID + " = ?",
                    new String[]{id.toString()},
                    null, null, null);

            if (cursor.moveToNext()) {
                categoria = transformaCursorEmEntidade(cursor);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar categoria");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return categoria;
    }
}
