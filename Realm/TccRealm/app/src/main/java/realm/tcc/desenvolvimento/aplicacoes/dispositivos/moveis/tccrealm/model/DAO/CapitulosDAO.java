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
 * Created by vinic on 04/10/2016.
 */
public class CapitulosDAO implements IPrimaryKeyDAO<Capitulos> {
    private static CapitulosDAO instance;
    private final Realm REALM;

    private CapitulosDAO(){
        this.REALM = RealmController.getInstance().getRealm();
    }

    public static CapitulosDAO getInstance(){
        if(instance == null)
            instance = new CapitulosDAO();
        return instance;
    }

    @Override
    public void add(List<Capitulos> objects) {
        this.REALM.beginTransaction();
        for(Capitulos capitulo : objects){
            this.REALM.insertOrUpdate(capitulo);
        }
        this.REALM.commitTransaction();
    }

    @Override
    public void add(Capitulos object) {
        this.REALM.beginTransaction();
        this.REALM.insertOrUpdate(object);
        this.REALM.commitTransaction();
    }

    @Override
    public void delete(Long id) {
        this.REALM.beginTransaction();
        this.REALM.where(Capitulos.class).equalTo("id", id).findFirst().deleteFromRealm();
        this.REALM.commitTransaction();
    }

    @Override
    public Capitulos getById(Long id) {
        return new Capitulos(this.REALM.where(Capitulos.class).equalTo("id", id).findFirst());
    }

    @Override
    public List<Capitulos> getList() {
        RealmResults<Capitulos> capitulosRealmList = this.REALM.where(Capitulos.class).findAll();

        return capitulosRealmList;
    }

    @Override
    public void deleteAll() {
        this.REALM.beginTransaction();
        this.REALM.where(Livros.class).findAll().deleteAllFromRealm();
        this.REALM.where(Capitulos.class).findAll().deleteAllFromRealm();
        this.REALM.commitTransaction();
    }

    @Override
    public Long getCount() { return this.REALM.where(Livros.class).count(); }

    @Override
    public Long getNextId() {
        Long id = new Long(0);
        if(this.REALM.where(Capitulos.class).count() > 0)
            id = this.REALM.where(Capitulos.class).max("id").longValue() + 1;
        return id;
    }
}
