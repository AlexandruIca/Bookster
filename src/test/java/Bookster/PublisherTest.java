package Bookster;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Vector;

public class PublisherTest {
    private static final ArrayList<Publisher> names = new ArrayList<>();

    @BeforeAll
    static void setup() {
        names.add(new Publisher("Hoffman"));
        names.add(new Publisher("Penguin"));
        names.add(new Publisher("Macmillan"));
        names.add(new Publisher("Collins"));
        names.add(new Publisher("Hatchet"));
        names.add(new Publisher("Schuster"));
    }

    @Test
    public void testUniqueID() {
        var ids = new ArrayList<Long>();
        names.stream().map(Publisher::getID).forEach(ids::add);
        assert ids.stream().distinct().count() == names.size();
    }

    @Test
    public void testThreadSafety() {
        var temp = new Vector<Publisher>();
        names.parallelStream().forEach(name -> temp.add(new Publisher(name.getName())));
        assert temp.size() == names.size();
    }

    @Test
    public void testToString() {
        var temp = new Publisher("XYZ");
        assert temp.toString().equals("(" + temp.getID() + ", XYZ)");
    }
}
