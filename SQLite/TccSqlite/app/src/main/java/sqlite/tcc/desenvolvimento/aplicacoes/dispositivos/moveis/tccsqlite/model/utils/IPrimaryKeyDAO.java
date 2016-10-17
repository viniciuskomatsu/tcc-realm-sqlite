package sqlite.tcc.desenvolvimento.aplicacoes.dispositivos.moveis.tccsqlite.model.utils;

import java.util.List;

/**
 * Created by Vinicius on 27/07/2016.
 */
public interface IPrimaryKeyDAO<T> {

    public void add(List<T> objects);

    public void add(T object);

    public void delete(Long id);

    public T getById(Long id);

    public List<T> getList();

    public void deleteAll();

    public Long getCount();
}
