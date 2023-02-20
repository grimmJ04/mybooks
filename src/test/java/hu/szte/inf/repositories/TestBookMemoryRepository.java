package hu.szte.inf.repositories;

import hu.szte.inf.models.Book;
import hu.szte.inf.models.Genre;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestBookMemoryRepository {

    @Test
    private void testBookSaving() {
        var repository = new BookMemoryRepository();

        var book = new Book("My new book", "whoami", 982, Genre.SCIFI);

        repository.save(book);
        Assert.assertTrue(repository.findById(0L).isPresent());
    }

    @Test
    private void testBatchBookSaving() {
        var repository = new BookMemoryRepository();

        var books = new ArrayList<Book>() {{
                new Book(0L, "My Book 1", "I am!", 666, Genre.HORROR);
                new Book(1L, "My Book 2", "I am NOT!", 555, Genre.ACTION);
                new Book(2L, "My Book 3", "To be or not to be!", 42, Genre.ROMANCE);
        }};

        repository.saveAll(books);
        Assert.assertEquals(books, repository.findAll());
    }
}
