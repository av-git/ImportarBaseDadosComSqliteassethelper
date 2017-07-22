package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        MarcaDao marcaDao = MarcaDao.getInstance(this);
        //marcaDao.open();

        //marcaDao.fecharConexao();

        Marca marca = new Marca();
        marca.setId(new Long(9));
        marca.setNome("avelino 2222");
        marcaDao.update(marca);

        Marca novaMarca = new Marca();
        novaMarca.setNome("Joao 22");
        marcaDao.insert(novaMarca);

        List<Marca> quotes = marcaDao.buscarTodos();

        for (Marca marca1 : quotes) {
            Log.i("a", marca1.toString());
        }
        novaMarca.setId(new Long(11));
        marcaDao.delete(novaMarca);

        quotes = marcaDao.buscarTodos();

        ArrayAdapter<Marca> adapter = new ArrayAdapter<Marca>(this, android.R.layout.simple_list_item_1, quotes);
        this.listView.setAdapter(adapter);
    }
}
