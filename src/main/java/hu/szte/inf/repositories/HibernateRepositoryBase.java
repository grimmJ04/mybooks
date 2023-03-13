package hu.szte.inf.repositories;

import hu.szte.inf.utils.PersistenceSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

public abstract class HibernateRepositoryBase<T, ID> implements CrudRepository<T, ID> {

    private final EntityManagerFactory entityManagerFactory;
    private final Class<T> forClass;

    public HibernateRepositoryBase(EntityManagerFactory entityManagerFactory, Class<T> forClass) {
        this.entityManagerFactory = entityManagerFactory;
        this.forClass = forClass;
    }

    @Override
    public <S extends T> S save(S entity) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> findById(ID aLong) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(ID aLong) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> findAll() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> longs) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(ID aLong) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T entity) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> longs) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        // TODO
        throw new UnsupportedOperationException();
    }
}
