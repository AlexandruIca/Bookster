package Bookster;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class BookServiceTest {
    @BeforeAll
    public static void someFields() {
        var srv = BookService.INSTANCE;
        var author = new Author("A", "B", "2000-01-01");
        var publisher = new Publisher("Publ");
        var book = new Book("Title", BookGenre.TRAVEL, 29.99, "1996-10-03", author, publisher);
        var client = new Client("X", "Y", "1986-07-08");
        Transaction[] transactions = {
                new Transaction(book, client, "2021-03-28", 2),
                new Transaction(book, client, "2021-03-28", 3),
                new Transaction(book, client, "2018-04-06", 1)
        };

        srv.register(author);
        srv.register(publisher);
        srv.register(book);
        srv.register(client);
        Arrays.stream(transactions).forEach(srv::register);
    }

    @Test
    public void testRegisterAuthor() {
        var srv = BookService.INSTANCE;
        var auth = new Author("A", "B", "2000-01-01");

        assert srv.register(new Author("X", "Y", "1999-09-09"));
        assert srv.register(auth);
        assert !srv.register(auth);
    }

    @Test
    public void testRegisterPublisher() {
        var srv = BookService.INSTANCE;
        var publisher = new Publisher("Whatever");

        assert srv.register(new Publisher("ABC"));
        assert srv.register(publisher);
        assert !srv.register(publisher);
    }

    @Test
    public void testTransactionsOnDate() {
        var srv = BookService.INSTANCE;
        final var date = LocalDate.parse("2021-03-28");

        assert srv.getTransactionsOnDate(date).size() == 2;
    }
}
