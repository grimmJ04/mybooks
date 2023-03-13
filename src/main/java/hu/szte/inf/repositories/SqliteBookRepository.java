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
        try (Connection connection = getConnection()) {
            // create
            if (entity.getId() == null || entity.getId() <= 0) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO " + tableName + " (title,author,pageNumber,genre) VALUES (?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, entity.getTitle());
                    statement.setString(2, entity.getAuthor());
                    statement.setInt(3, entity.getPageNumber());
                    statement.setInt(4, entity.getGenre().ordinal());

                    if (statement.executeUpdate() > 0) {
                        try (ResultSet resultSet = statement.getGeneratedKeys()) {
                            if (resultSet.next()) {
                                long id = resultSet.getLong(1);
                                entity.setId(id);
                            }
                        }
                    }
                }
            }
            // update
            else {
                try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE " + tableName + " SET title=?,author=?,pageNumber=?,genre=? WHERE id=?")) {
                    statement.setString(1, entity.getTitle());
                    statement.setString(2, entity.getAuthor());
                    statement.setInt(3, entity.getPageNumber());
                    statement.setInt(4, entity.getGenre().ordinal());
                    statement.setLong(5, entity.getId());
                }
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM " + tableName + " WHERE id=?")) {
            statement.setLong(1, aLong);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Book(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getInt("pageNumber"),
                            Genre.values()[resultSet.getInt("genre")]));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Long aLong) {
        return findById(aLong).isPresent();
    }

    @Override
    public Iterable<Book> findAll() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {
            ArrayList<Book> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("pageNumber"),
                        Genre.values()[resultSet.getInt("genre")]));
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> longs) {
        return StreamSupport.stream(longs.spliterator(), false)
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName)) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + tableName + " WHERE id=?")) {
                statement.setLong(1, aLong);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Book entity) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + tableName +
                            " WHERE id=? AND name=? AND credit=? AND semester=? AND grade=?")) {
                statement.setLong(1, entity.getId());
                statement.setString(2, entity.getTitle());
                statement.setString(3, entity.getAuthor());
                statement.setInt(4, entity.getPageNumber());
                statement.setInt(5, entity.getGenre().ordinal());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        longs.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
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
