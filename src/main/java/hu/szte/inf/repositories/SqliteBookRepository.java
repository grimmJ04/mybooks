package hu.szte.inf.repositories;

import java.sql.*;
import java.util.Optional;

import hu.szte.inf.models.Book;
import hu.szte.inf.utils.AppConfigSupport;
import org.springframework.data.repository.CrudRepository;

public class SqliteBookRepository implements CrudRepository<Book, Long> {

    private static final String connectionString = AppConfigSupport.getProperty("db.connection.url");
    private static final String tableName = Book.class.getSimpleName();

    @Override
    public <S extends Book> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Book entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public void createTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT exists " + tableName + "(" +
                            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                            "title TEXT NOT NULL," +
                            "author TEXT NOT NULL," +
                            "pageNUmber INTEGER NOT NULL," +
                            "genre INTEGER" +
                            ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTableIfExists() {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS " + tableName + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
