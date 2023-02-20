package hu.szte.inf.dao;

import java.util.Optional;

public interface IDao<T, ID> {

    Iterable<T> findAll();

    Optional<T> findById(ID id);

    void removeById(ID id);

    void save(T model);

    void saveAll(Iterable<T> models);
}
