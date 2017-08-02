package br.com.viperfish.importarbasedadoscomsqliteassethelper.produto;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.importarbasedadoscomsqliteassethelper.DaoBase;


/**
 * Created by AV on 22/11/2016.
 */

public class ProdutoDao extends DaoBase {

    private static ProdutoDao instance;
    private static String TAG = "ProdutoDao"; // LogCat

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private ProdutoDao(Context context) {
        super(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static ProdutoDao getInstance(Context context) {
        if (instance == null) {
            instance = new ProdutoDao(context);
        }
        return instance;
    }

    public List<Produto> buscarTodos() {

        Cursor cursor = null;
        List<Produto> produtos = new ArrayList<Produto>();

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IProdutoSchema.TABELA,
                    IProdutoSchema.COLUNAS,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Produto produto = transformaCursorEmEntidade(cursor);
                produtos.add(produto);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Produtos");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return produtos;
    }

    /**
     * Update the contact details.
     *
     */
    public boolean atualizar(Produto categoria) {
        //TODO NAO IMPLEMENTADO
        return false;
    }

    /**
     * Delete the provided contact.
     *
     * @param categoria the contact to delete
     */
    public boolean deletar(Produto categoria) {
        //TODO NAO IMPLEMENTADO
        return false;
    }

    protected Produto transformaCursorEmEntidade(Cursor cursor) {

        Produto produto = new Produto(

                cursor.getLong(cursor.getColumnIndex(
                        IProdutoSchema._ID)),

                cursor.getLong(cursor.getColumnIndex(
                        IProdutoSchema.SUBCATEGORIA_ID)),

                cursor.getString(cursor.getColumnIndex(
                        IProdutoSchema.NOME))
        );

        return produto;
    }

    public Produto buscarPorId(Integer id) {

        Produto produto = null;
        Cursor cursor = null;

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IProdutoSchema.TABELA,
                    IProdutoSchema.COLUNAS,
                    IProdutoSchema._ID + " = ?",
                    new String[]{id.toString()},
                    null, null, null);

            if (cursor.moveToNext()) {
                produto = transformaCursorEmEntidade(cursor);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar produto");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return produto;
    }
}
