package br.com.viperfish.importarbasedadoscomsqliteassethelper.Anuncio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.viperfish.importarbasedadoscomsqliteassethelper.DaoBase;


/**
 * Created by AV on 22/11/2016.
 */

public class AnuncioDao extends DaoBase {

    private static AnuncioDao instance;
    private static String TAG = "AnuncioDao"; // LogCat

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private AnuncioDao(Context context) {
        super(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static AnuncioDao getInstance(Context context) {
        if (instance == null) {
            instance = new AnuncioDao(context);
        }
        return instance;
    }

    /**
     * Insert a {@link Anuncio} into the database.
     */
    public boolean inserir(Anuncio anuncio) {

        ContentValues values = new ContentValues();

        values.put(IAnuncioSchema.PRODUTO_ID,
                anuncio.getProdutoId());

        values.put(IAnuncioSchema.TITULO,
                anuncio.getTitulo());

        values.put(IAnuncioSchema.DESCRICAO,
                anuncio.getDescricao());

        values.put(IAnuncioSchema.DATA_ANUNCIO,
                new Date().getTime());

        values.put(IAnuncioSchema.PRECO,
                anuncio.getPreco());

        return super.inserir(IAnuncioSchema.TABELA, values);
    }

    public Anuncio buscarPorId(Integer id) {

        Anuncio anuncio = null;
        Cursor cursor = null;

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IAnuncioSchema.TABELA,
                    IAnuncioSchema.COLUNAS,
                    IAnuncioSchema._ID + " = ?",
                    new String[]{id.toString()},
                    null, null, null);

            if (cursor.moveToNext()) {
                anuncio = transformaCursorEmEntidade(cursor);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Anuncio");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return anuncio;
    }

    public List<Anuncio> buscarTodos() {

        Cursor cursor = null;
        List<Anuncio> anuncios = new ArrayList<Anuncio>();

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IAnuncioSchema.TABELA,
                    IAnuncioSchema.COLUNAS,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Anuncio anuncio = transformaCursorEmEntidade(cursor);
                anuncios.add(anuncio);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Anuncio");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return anuncios;
    }

    /**
     * Update the contact details.
     */
    public boolean atualizar(Anuncio anuncio) {

        ContentValues values = new ContentValues();

        values.put(IAnuncioSchema.PRODUTO_ID,
                anuncio.getProdutoId());

        values.put(IAnuncioSchema.TITULO,
                anuncio.getTitulo());

        values.put(IAnuncioSchema.DESCRICAO,
                anuncio.getDescricao());

        values.put(IAnuncioSchema.DATA_ANUNCIO,
                anuncio.getDataAnuncio().getTime());

        values.put(IAnuncioSchema.PRECO,
                anuncio.getPreco());

        boolean atualizou = super.atualizar(IAnuncioSchema.TABELA,
                values, IAnuncioSchema._ID + " = ?",
                new String[]{anuncio.getId().toString()});

        return atualizou;
    }

    /**
     * Delete the provided contact.
     *
     * @param anuncio the contact to delete
     */
    public boolean deletar(Anuncio anuncio) {
        return super.deletar(IAnuncioSchema.TABELA, IAnuncioSchema._ID + " = ?", new String[]{anuncio.getId().toString()});

    }

    protected Anuncio transformaCursorEmEntidade(Cursor cursor) {

        Anuncio anuncio = new Anuncio(

                cursor.getLong(cursor.getColumnIndex(
                        IAnuncioSchema._ID)),

                cursor.getLong(cursor.getColumnIndex(
                        IAnuncioSchema.PRODUTO_ID)),

                cursor.getString(cursor.getColumnIndex(
                        IAnuncioSchema.TITULO)),

                cursor.getString(cursor.getColumnIndex(
                        IAnuncioSchema.DESCRICAO)),

                cursor.getLong(cursor.getColumnIndex(
                        IAnuncioSchema.DATA_ANUNCIO)),

                cursor.getDouble(cursor.getColumnIndex(
                        IAnuncioSchema.PRECO))
        );

        return anuncio;
    }
}
