package br.com.viperfish.importarbasedadoscomsqliteassethelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.viperfish.importarbasedadoscomsqliteassethelper.Anuncio.Anuncio;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.Anuncio.AnuncioDao;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.categoria.Categoria;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.categoria.CategoriaDao;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.marca.Marca;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.marca.MarcaDao;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.produto.Produto;
import br.com.viperfish.importarbasedadoscomsqliteassethelper.produto.ProdutoDao;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ListView listAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        this.listAnuncio = (ListView) findViewById(R.id.listAnucios);

        MarcaDao marcaDao = MarcaDao.getInstance(this);

        listarMarcas(marcaDao);

        atualizar(marcaDao);

        Marca novaMarca = inserir(marcaDao);

        deletar(marcaDao, novaMarca);

        Marca marca = marcaDao.buscarPorId(2);
        Log.i("marca id  ", marca.toString());


        List<Marca> listaMarcas = marcaDao.buscarTodos();

        ArrayAdapter<Marca> adapter = new ArrayAdapter<Marca>(this, android.R.layout.simple_list_item_1, listaMarcas);
        //this.listView.setAdapter(adapter);

        CategoriaDao categoriaDao = CategoriaDao.getInstance(MainActivity.this);
        listaTodasCategorias(categoriaDao);

        Categoria categoria = categoriaDao.buscarPorId(2);
        Log.i("categoria id  ", marca.toString());

        ProdutoDao produtoDao = ProdutoDao.getInstance(MainActivity.this);
        listarTodosProdutos(produtoDao);

        AnuncioDao anuncioDao = AnuncioDao.getInstance(MainActivity.this);

        inserirAnuncio(anuncioDao);

        listaTodosAnuncios(anuncioDao);

        atulizarAnuncio(anuncioDao);

        Anuncio anuncio = anuncioDao.buscarPorId(2);
        Log.i("Anuncio id  ", anuncio.toString());

    }

    private void atulizarAnuncio(AnuncioDao anuncioDao) {
        Anuncio anuncio = new Anuncio();
        anuncio.setId(new Long(1));
        anuncio.setDescricao("avelino troquei");
        anuncio.setTitulo("avelino troquei");
        anuncio.setPreco(2.34);
        anuncio.setDataAnuncio(new Date());
        boolean update = anuncioDao.atualizar(anuncio);
        Log.i("Atualizou Anuncio ? ", String.valueOf(update));
    }

    private void inserirAnuncio(AnuncioDao anuncioDao) {

        Random random = new Random(1000);

        Anuncio anuncio = new Anuncio();
        anuncio.setProdutoId(new Long(1));
        anuncio.setTitulo("teste" + random.nextInt());
        anuncio.setDescricao("test teste descricao");
        anuncio.setDataAnuncio(new Date());
        anuncio.setPreco(new Double(1.99));

        anuncioDao.inserir(anuncio);
    }

    private void listaTodosAnuncios(AnuncioDao anuncioDao) {
        List<Anuncio> anuncios = anuncioDao.buscarTodos();

        for (Anuncio c : anuncios) {
            Log.i("anuncios", c.toString());
        }

        ArrayAdapter<Anuncio> adapter = new ArrayAdapter<Anuncio>(this, android.R.layout.simple_list_item_1, anuncios);
        this.listAnuncio.setAdapter(adapter);

    }

    private void listarTodosProdutos(ProdutoDao produtoDao) {
        List<Produto> produtos = produtoDao.buscarTodos();

        for (Produto c : produtos) {
            Log.i("produto", c.toString());
        }
    }

    private void listaTodasCategorias(CategoriaDao categoriaDao) {
        List<Categoria> categorias = categoriaDao.buscarTodos();

        for (Categoria c : categorias) {
            Log.i("a", c.toString());
        }
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
        Log.i("Atualizou Marca ? ", String.valueOf(update));
    }
}
