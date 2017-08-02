package br.com.viperfish.importarbasedadoscomsqliteassethelper.categoria;

/**
 * Created by ddark on 23/07/17.
 */

interface ICategoriaSchema {

    public static final String TABELA = "CATEGORIA";
    public static final String _ID = "_id";
    public static final String IDPAI = "id_pai";
    public static final String NOME =  "nome";

    public static final String[] COLUNAS = new String[] {
            _ID, IDPAI, NOME
    };

}
