package Bookster;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuthorTest {
    private static final ArrayList<String> firstNames = new ArrayList<>();
    private static final ArrayList<String> lastNames = new ArrayList<>();
    private static final ArrayList<Author> authors = new ArrayList<>();

    @BeforeAll
    static void setup() {
        firstNames.add("Juan");
        firstNames.add("Pablo");
        firstNames.add("Jerry");
        firstNames.add("John");
        firstNames.add("Tom");
        firstNames.add("Mariah");
        firstNames.add("Paul");

        lastNames.ensureCapacity(firstNames.size());

        lastNames.add("Lovelace");
        lastNames.add("Herbert");
        lastNames.add("Muriel");
        lastNames.add("Guy");
        lastNames.add("Davidson");
        lastNames.add("Winters");
        lastNames.add("Sean");

        var random = new Random();

        for (final var name : firstNames) {
            authors.add(
                new Author(name, lastNames.get(random.nextInt(lastNames.size())), "1978-11-04"));
        }
    }

    @Test
    public void testUniqueID() {
        var ids = new ArrayList<Long>();
        authors.stream().map(Author::getID).forEach(ids::add);
        assert ids.stream().distinct().count() == firstNames.size();
    }

    @Test
    public void testThreadSafety() {
        var temp = new Vector<Author>();
        authors.parallelStream().forEach(author
            -> temp.add(new Author(author.getFirstName(), author.getLastName(), "2010-10-07")));
        assert temp.size() == authors.size();
    }

    @Test
    public void testAuthorToString() {
        var temp = new Author("A", "B", "1934-12-12");
        assert temp.toString().equals("(" + temp.getID() + ", A B, 1934-12-12)");
    }
}

