package br.com.viperfish.importarbasedadoscomsqliteassethelper.marca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import br.com.viperfish.importarbasedadoscomsqliteassethelper.DaoBase;


/**
 * Created by AV on 22/11/2016.
 */

public class MarcaDao extends DaoBase {

    private static MarcaDao instance;
    private static String TAG = "MarcaDao"; // LogCat

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private MarcaDao(Context context) {
        super(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static MarcaDao getInstance(Context context) {
        if (instance == null) {
            instance = new MarcaDao(context);
        }
        return instance;
    }

    /**
     * Insert a contact into the database.
     *
     */
    public boolean inserir(Marca marca) {

        ContentValues values = new ContentValues();

        values.put(IMarcaSchema.COLUNA_NOME,
                marca.getNome());

        return inserir(IMarcaSchema.TABELA, values);
    }

    public List<Marca> buscarTodos() {

        Cursor cursor = null;
        List<Marca> marcas = new ArrayList<Marca>();

        try {

            abrirConexaoEmModoLeitura();

            cursor = getDatabase().query(IMarcaSchema.TABELA,
                    IMarcaSchema.COLUNAS,
                    null, null, null, null, null);

            while (cursor.moveToNext()) {
                Marca marca = transformaCursorEmEntidade(cursor);
                marcas.add(marca);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ERRO Buscar Marca");

        } finally {
            cursor.close();
            fecharConexao();
        }

        return marcas;
    }

    /**
     *
     * Forma alternativa
     * Read all contacts from the database.
     *
     * @return a List of Contacts
     */
    public List<Marca> getContacts() {

        List<Marca> list = new ArrayList<>();
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM Marca", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Marca contact = new Marca();
            contact.setId(cursor.getLong(0));
            contact.setNome(cursor.getString(1));

            list.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Update the contact details.
     *
     */
    public boolean atualizar(Marca marca) {

        ContentValues values = new ContentValues();

        values.put(IMarcaSchema.COLUNA_NOME,
                marca.getNome());

        boolean atualizou = atualizar(IMarcaSchema.TABELA,
                values, IMarcaSchema.COLUNA_ID + " = ?",
                new String[]{marca.getId().toString()});

        return atualizou;
    }

    /**
     * Delete the provided contact.
     *
     * @param marca the contact to delete
     */
    public boolean deletar(Marca marca) {

        return deletar(IMarcaSchema.TABELA, IMarcaSchema.COLUNA_ID + " = ?", new String[]{marca.getId().toString()});

    }

    private Marca transformaCursorEmEntidade(Cursor cursor) {

        Marca marca = new Marca(

                cursor.getLong(cursor.getColumnIndex(
                        IMarcaSchema.COLUNA_ID)),

                cursor.getString(cursor.getColumnIndex(
                        IMarcaSchema.COLUNA_NOME))
        );

        return marca;
    }
}
