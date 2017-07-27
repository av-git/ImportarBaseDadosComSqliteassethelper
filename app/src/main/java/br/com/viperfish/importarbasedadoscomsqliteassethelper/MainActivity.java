package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.viperfish.importarbasedadoscomsqliteassethelper.marca.Marca;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.marca.MarcaDao;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);

        MarcaDao marcaDao = MarcaDao.getInstance(this);

        listarMarcas(marcaDao);

        atualizar(marcaDao);

        Marca novaMarca = inserir(marcaDao);

        deletar(marcaDao, novaMarca);

        List<Marca> listaMarcas = marcaDao.buscarTodos();

        ArrayAdapter<Marca> adapter = new ArrayAdapter<Marca>(this, android.R.layout.simple_list_item_1, listaMarcas);
        this.listView.setAdapter(adapter);
    }

    private void deletar(MarcaDao marcaDao, Marca novaMarca) {
        novaMarca.setId(new Long(11));
        boolean delete = marcaDao.deletar(novaMarca);
        Log.i("Deletou ? ", String.valueOf(delete));
    }

    private void listarMarcas(MarcaDao marcaDao) {
        List<Marca> quotes = marcaDao.buscarTodos();

        for (Marca marca1 : quotes) {
            Log.i("a", marca1.toString());
        }
    }

    @NonNull
    private Marca inserir(MarcaDao marcaDao) {
        Marca novaMarca = new Marca();
        novaMarca.setNome("Joao 22");
        boolean insert = marcaDao. inserir(novaMarca);
        Log.i("Inseriu ? ", String.valueOf(insert));
        return novaMarca;
    }

    private void atualizar(MarcaDao marcaDao) {
        Marca marca = new Marca();
        marca.setId(new Long(9));
        marca.setNome("avelino 2222");
        boolean update = marcaDao.atualizar(marca);
        Log.i("Atualizou ? ", String.valueOf(update));
    }
}
