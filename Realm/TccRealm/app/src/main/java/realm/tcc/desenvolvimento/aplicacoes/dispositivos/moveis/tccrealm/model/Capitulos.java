package realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model;

import io.realm.RealmObject;

/**
 * Created by vinic on 19/09/2016.
 */
public class Capitulos extends RealmObject {
    private String titulo;
    private String subtitulo;

    public Capitulos(){

    }

    public Capitulos(RealmObject capitulo){
        setTitulo(((Capitulos)capitulo).getTitulo());
        setSubtitulo(((Capitulos)capitulo).getSubtitulo());
    }

    public String getTitulo(){ return this.titulo; }

    public void setTitulo(String titulo){ this.titulo = titulo; }

    public String getSubtitulo() { return this.subtitulo; }

    public void setSubtitulo(String subtitulo) { this.subtitulo = subtitulo; }
}