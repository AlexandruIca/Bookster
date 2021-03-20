package Bookster;

import org.junit.jupiter.api.Test;

public class BookServiceTest {
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
}
