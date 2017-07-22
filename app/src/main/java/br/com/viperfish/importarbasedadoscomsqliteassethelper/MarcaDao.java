package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;



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
    public void insert(Marca marca) {

        ContentValues values = new ContentValues();

        values.put(DatabaseOpenHelper.Marca.NOME,
                marca.getNome());

        long resultado = inserir(DatabaseOpenHelper.Marca.TABELA, values);

        if (resultado == -1) {
            Log.i(TAG, "ERRO Inserindo Marca" + String.valueOf(marca.toString()));
        }
    }

    public List<Marca> buscarTodos() {

        abrirConexaoEmModoLeitura();

        Cursor cursor = getDatabase().query(DatabaseOpenHelper.Marca.TABELA,
                DatabaseOpenHelper.Marca.COLUNAS,
                null, null, null, null, null);

        List<Marca> marcas = new ArrayList<Marca>();

        while(cursor.moveToNext()){
            Marca marca = criarMarca(cursor);
            marcas.add(marca);
        }

        cursor.close();
        fecharConexao();

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
    public void update(Marca marca) {

        ContentValues values = new ContentValues();

        values.put(DatabaseOpenHelper.Marca.NOME,
                marca.getNome());

        abriConexaoEmModoEscrita();

        int resultado = atualizar(DatabaseOpenHelper.Marca.TABELA,
                values, DatabaseOpenHelper.Marca._ID + " = ?",
                new String[]{marca.getId().toString()});

        fecharConexao();
    }

    /**
     * Delete the provided contact.
     *
     * @param marca the contact to delete
     */
    public void delete(Marca marca) {
        try {
            deletar(DatabaseOpenHelper.Marca.TABELA, DatabaseOpenHelper.Marca._ID+ " = ?", new String[]{marca.getId().toString()});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Marca criarMarca(Cursor cursor) {

        Marca marca = new Marca(

                cursor.getLong(cursor.getColumnIndex(
                        DatabaseOpenHelper.Marca._ID)),

                cursor.getString(cursor.getColumnIndex(
                        DatabaseOpenHelper.Marca.NOME))
        );

        return marca;
    }
}
