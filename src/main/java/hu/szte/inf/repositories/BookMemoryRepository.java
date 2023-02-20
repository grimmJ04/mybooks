package hu.szte.inf.repositories;

import hu.szte.inf.dao.IDao;
import hu.szte.inf.models.Book;
import hu.szte.inf.services.BookMemoryDbService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookMemoryRepository implements IDao<Book, Long> {

    private final BookMemoryDbService dbService = BookMemoryDbService.getInstance();
    private static Long currentId = 0L;

    @Override
    public Iterable<Book> findAll() {
        return dbService.getModel().values();
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return Optional.ofNullable(dbService.getModel().get(aLong));
    }

    @Override
    public void removeById(Long aLong) {
        dbService.getModel().remove(aLong);
    }

    @Override
    public void save(Book model) {
        if (model.getId() == null) {
            // error prone but simple, ok for demonstration
            model.setId(currentId++);
        }
        dbService.getModel().put(model.getId(), model);
    }

    @Override
    public void saveAll(Iterable<Book> models) {
        dbService.getModel().putAll(
                StreamSupport.stream(
                        models.spliterator(), false
                ).collect(Collectors.toMap(Book::getId, model -> {
                    if (model.getId() == null) {
                        model.setId(currentId++);
                    }
                    return model;
                })));
    }
}
