package hu.szte.inf.repositories;

import hu.szte.inf.models.Book;
import hu.szte.inf.models.Genre;
import hu.szte.inf.utils.AppConfigSupport;
import org.springframework.data.repository.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SqliteBookRepository implements CrudRepository<Book, Long> {

    private static final String connectionString = AppConfigSupport.getProperty("db.connection.url");
    private static final String tableName = Book.class.getSimpleName();

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }

    @Override
    public <S extends Book> S save(S entity) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Book> findAll() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> longs) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Book entity) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public void createTableIfNotExists() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public void dropTableIfExists() {
        // TODO
        throw new UnsupportedOperationException();
    }
}
