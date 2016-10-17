package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model;

import java.util.List;

import sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.utils.IPrimaryKey;

/**
 * Created by Vinicius on 26/07/2016.
 */
public class Livros implements IPrimaryKey {
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private List<Capitulos> capitulosList;

    public Livros(Long id, String titulo, String autor, String editora, List<Capitulos> capitulosList){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.capitulosList = capitulosList;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public List<Capitulos> getCapitulosList(){ return this.capitulosList; }

    public void setCapitulosList(List<Capitulos> capitulosList) { this.capitulosList = capitulosList; }
}
