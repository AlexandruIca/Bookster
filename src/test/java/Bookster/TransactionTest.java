package Bookster;

import org.junit.jupiter.api.Test;

import java.util.Vector;
import java.util.stream.IntStream;

public class TransactionTest {
    private final Author author = new Author("Some", "Author", "1990-03-13");
    private final Publisher publisher = new Publisher("Some Publisher");
    private final Client client = new Client("Some", "Client", "2002-02-02");
    private final Book book = new Book("Some book", BookGenre.BIOGRAPHY, 25.99, "1998-10-02",
            author, publisher);

    @Test
    public void testTransactionInterface() {
        final var transact = new Transaction(this.book, this.client, "2020-07-06", 1);
        assert transact.getBook().getAuthors().size() == 1;
    }

    @Test
    public void testTransactionThreadSafety() {
        final int numIterations = 100;
        var transactions = new Vector<Transaction>();
        IntStream.range(0, numIterations).parallel().forEach(i -> {
            transactions.add(new Transaction(this.book, this.client, "2019-08-07", 1));
        });
        assert transactions.size() == numIterations;
    }
}
