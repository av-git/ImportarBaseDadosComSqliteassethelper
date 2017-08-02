package br.com.viperfish.importarbasedadoscomsqliteassethelper.produto;

/**
 * Created by ddark on 23/07/17.
 */

interface IProdutoSchema {

    public static final String TABELA = "PRODUTO";
    public static final String _ID = "_id";
    public static final String NOME =  "nome";
    public static final String SITE =  "site";
    public static final String SUBCATEGORIA_ID =  "subCategoria_id";
    public static final String MARCA_ID =  "marca_id";
    public static final String FOTO =  "foto";

    public static final String[] COLUNAS = new String[] {
            _ID, NOME, SITE , FOTO , SUBCATEGORIA_ID, MARCA_ID
    };


}
