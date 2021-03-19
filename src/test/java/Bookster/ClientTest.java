package Bookster;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ClientTest {
    private static final ArrayList<String> firstNames = new ArrayList<>();
    private static final ArrayList<String> lastNames = new ArrayList<>();
    private static final ArrayList<Author> clients = new ArrayList<>();

    @BeforeAll
    static void setup() {
        firstNames.add("David");
        firstNames.add("Mario");
        firstNames.add("Jerry");
        firstNames.add("Rossum");
        firstNames.add("Von");
        firstNames.add("Anna");
        firstNames.add("Luigi");

        lastNames.ensureCapacity(firstNames.size());

        lastNames.add("Conroy");
        lastNames.add("Hubert");
        lastNames.add("Eustache");
        lastNames.add("Pistachio");
        lastNames.add("Arleyne");
        lastNames.add("Manuel");
        lastNames.add("Roy");

        var random = new Random();

        for (final var name : firstNames) {
            clients.add(new Author(
                    name,
                    lastNames.get(random.nextInt(lastNames.size())),
                    "1986-06-24"
            ));
        }
    }

    @Test
    public void testUniqueID() {
        var ids = new ArrayList<Long>();
        clients.stream().map(Author::getID).forEach(ids::add);
        assert ids.stream().distinct().count() == firstNames.size();
    }

    @Test
    public void testThreadSafety() {
        var temp = new Vector<Author>();
        clients.parallelStream().forEach(author -> temp.add(new Author(author.getFirstName(),
                author.getLastName(), "2001-09-17")));
        assert temp.size() == clients.size();
    }
}

