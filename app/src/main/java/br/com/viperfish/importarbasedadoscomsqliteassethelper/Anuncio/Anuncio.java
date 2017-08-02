package br.com.viperfish.importarbasedadoscomsqliteassethelper.Anuncio;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by av on 13/11/16.
 */
public class Anuncio implements Serializable {

    private Long id;
    private Long produtoId;
    private String titulo;
    private String descricao;
    private Date dataAnuncio;
    private Double preco;


    public Anuncio(long id, long codigoProduto, String titulo, String descricao, long dataAnuncio, Double preco) {

        this.id = id;
        this.produtoId = codigoProduto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataAnuncio = new Date(dataAnuncio);
        this.preco = preco;

    }

    public Anuncio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(Date dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataAnuncio=" + dataAnuncio +
                ", preco=" + preco +
                '}';
    }
}
