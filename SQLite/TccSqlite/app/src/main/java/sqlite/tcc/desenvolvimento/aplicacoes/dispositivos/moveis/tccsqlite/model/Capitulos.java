package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model;

import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.utils.IPrimaryKey;

/**
 * Created by vinic on 20/09/2016.
 */
public class Capitulos implements IPrimaryKey {
    private Long id;
    private String titulo;
    private String subtitulo;
    private Livros livro;

    public Capitulos(Long id, String titulo, String subtitulo, Livros livro){
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.livro = livro;
    }

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return this.titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getSubtitulo() { return this.subtitulo; }

    public void setSubtitulo(String subtitulo) { this.subtitulo = subtitulo; }

    public Livros getLivro() { return this.livro; }

    public void setLivro(Livros livro) { this.livro = livro; }
}
