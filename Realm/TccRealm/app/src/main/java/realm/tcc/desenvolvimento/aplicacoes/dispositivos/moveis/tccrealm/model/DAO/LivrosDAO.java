package realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.DAO;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Capitulos;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Livros;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Utils.IPrimaryKeyDAO;
import realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.realm.RealmController;

/**
 * Created by Vinicius on 27/07/2016.
 */
public class LivrosDAO implements IPrimaryKeyDAO<Livros> {
    private static LivrosDAO instance;
    private final Realm REALM;

    private LivrosDAO(){
        this.REALM = RealmController.getInstance().getRealm();
    }

    public static LivrosDAO getInstance(){
        if(instance == null)
            instance = new LivrosDAO();
        return instance;
    }

    @Override
    public void add(List<Livros> objects) {
        this.REALM.beginTransaction();
        for(Livros livro : objects){
            this.REALM.insertOrUpdate(livro);
        }
        this.REALM.commitTransaction();
    }

    @Override
    public void add(Livros object) {
        this.REALM.beginTransaction();
        this.REALM.insertOrUpdate(object);
        this.REALM.commitTransaction();
    }

    @Override
    public void delete(Long id) {
        this.REALM.beginTransaction();
        this.REALM.where(Livros.class).equalTo("id", id).findFirst().deleteFromRealm();
        this.REALM.commitTransaction();
    }

    @Override
    public Livros getById(Long id) {
        return new Livros(this.REALM.where(Livros.class).equalTo("id", id).findFirst());
    }

    @Override
    public List<Livros> getList() {
        RealmResults<Livros> livrosRealmList = this.REALM.where(Livros.class).findAll();

        return livrosRealmList;
    }

    @Override
    public Long getCount() {
        return this.REALM.where(Livros.class).count();
    }

    public Long getCountCapitulos(){
        return this.REALM.where(Capitulos.class).count();
    }

    @Override
    public void deleteAll() {
        this.REALM.beginTransaction();
        this.REALM.where(Capitulos.class).findAll().deleteAllFromRealm();
        this.REALM.where(Livros.class).findAll().deleteAllFromRealm();
        this.REALM.commitTransaction();
    }

    @Override
    public Long getNextId() {
        Long id = new Long(0);
        if(this.REALM.where(Livros.class).count() > 0)
            id = this.REALM.where(Livros.class).max("id").longValue() + 1;
        return id;
    }
}
