package hu.szte.inf.repositories;

import hu.szte.inf.utils.PersistenceSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

public abstract class RepositoryBase<T, ID> implements CrudRepository<T, ID> {

    private static final EntityManagerFactory entityManagerFactory = PersistenceSupport.createEntityManagerFactory();
    private final Class<T> forClass;

    public RepositoryBase(Class<T> forClass) {
        this.forClass = forClass;
    }

    @Override
    public <S extends T> S save(S entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                entityManager.persist(entity);
            } catch (PersistenceException e) {
                transaction.rollback();
                transaction.begin();
                entityManager.merge(entity);
            }
            transaction.commit();
        }
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            for (S entity : entities) {
                try {
                    entityManager.persist(entity);
                } catch (PersistenceException e) {
                    transaction.rollback();
                    transaction.begin();
                    entityManager.merge(entity);
                }
            }
            transaction.commit();
        }
        return entities;
    }

    @Override
    public Optional<T> findById(ID aLong) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            T entity = entityManager.find(forClass, aLong);
            return Optional.of(entity);
        }
    }

    @Override
    public boolean existsById(ID aLong) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.contains(aLong);
        }
    }

    @Override
    public Iterable<T> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            var baseQuery = entityManager.getCriteriaBuilder().createQuery(forClass);
            var tableSelection = baseQuery.from(forClass);
            var selectStatement = baseQuery.select(tableSelection);
            var selectAll = entityManager.createQuery(selectStatement);
            return selectAll.getResultList();
        }
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> longs) {
        var entities = new ArrayList<T>();
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            for (var id : longs) {
                T entity = entityManager.find(forClass, id);
                entities.add(entity);
            }
        }
        return entities;
    }

    @Override
    public long count() {
        return StreamSupport.stream(findAll().spliterator(), false).count();
    }

    @Override
    public void deleteById(ID aLong) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            var entity = entityManager.find(forClass, aLong);
            if (entity != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
            }
        }
    }

    @Override
    public void delete(T entity) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> longs) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            for (var id : longs) {
                var entity = entityManager.find(forClass, id);
                if (entity != null) {
                    entityManager.remove(entity);
                }
            }
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            for (var entity : entities) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void deleteAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            var baseQuery = entityManager.getCriteriaBuilder().createQuery(forClass);
            var tableSelection = baseQuery.from(forClass);
            var selectStatement = baseQuery.select(tableSelection);
            var selectAll = entityManager.createQuery(selectStatement);
            entityManager.getTransaction().begin();
            for (var entity : selectAll.getResultList()) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        }
    }
}
