package hu.szte.inf.services;

import hu.szte.inf.models.Book;
import hu.szte.inf.models.Genre;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestBookMemoryDbService {

    @Test
    private void testSingletonPattern() {
         Assert.assertEquals(BookMemoryDbService.getInstance(), BookMemoryDbService.getInstance());
    }

    @Test
    private void testGlobality() {
        var instance1 = BookMemoryDbService.getInstance();
        var instance2 = BookMemoryDbService.getInstance();

        var books = new Book[]{
                new Book(0L, "My Book 1", "I am!", 666, Genre.HORROR),
                new Book(1L, "My Book 2", "I am NOT!", 555, Genre.ACTION),
                new Book(2L, "My Book 3", "To be or not to be!", 42, Genre.ROMANCE)
        };
        instance1.getModel().putAll(Arrays.stream(books).collect(Collectors.toMap(Book::getId, m -> m)));
        Assert.assertEquals(Arrays.stream(books).toList(), instance2.getModel().values());
        Assert.assertEquals(instance1.getModel().values(), instance2.getModel().values());
    }
}
