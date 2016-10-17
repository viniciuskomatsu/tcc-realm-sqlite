package realm.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccrealm.model.Utils;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Vinicius on 27/07/2016.
 */
public interface IPrimaryKeyDAO<T extends RealmObject> {

    public void add(List<T> objects);

    public void add(T object);

    public void delete(Long id);

    public T getById(Long id);

    public List<T> getList();

    public void deleteAll();

    public Long getCount();

    public Long getNextId();
}
