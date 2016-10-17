package realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Utils.IPrimaryKey;

/**
 * Created by Vinicius on 26/07/2016.
 */
public class Livros extends RealmObject implements IPrimaryKey {

    @PrimaryKey
    @Required
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private RealmList<Capitulos> capituloList;

    public Livros(){

    }

    public Livros (RealmObject livro){
        setId(((Livros)livro).getId());
        setTitulo(((Livros)livro).getTitulo());
        setAutor(((Livros)livro).getAutor());
        setEditora(((Livros)livro).getEditora());
        setCapitulos(((Livros)livro).getCapitulos());
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

    public RealmList<Capitulos> getCapitulos(){ return this.capituloList; }

    public void setCapitulos(RealmList<Capitulos> capituloList) { this.capituloList = capituloList; }
}
