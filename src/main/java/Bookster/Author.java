package Bookster;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description of an author.
 */
public final class Author implements Unique {
    /**
     * Static atomic that holds increasing IDs for authors.
     */
    private static AtomicLong authorId = new AtomicLong(0);

    /**
     * @return a new unique ID for authors, use this when creating a new author.
     * @apiNote Method is completely thread-safe.
     */
    private static Long newID() {
        return authorId.getAndIncrement();
    }

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateBorn;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateBorn() {
        return dateBorn;
    }

    public Author(String firstName, String lastName, String dateBorn) {
        this.id = newID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBorn = LocalDate.parse(dateBorn);
    }

    @Override
    public Long getID() {
        return this.id;
    }

    @Override
    public String toString() {
        String s = new StringBuilder()
                .append('(')
                .append(this.id)
                .append(", ")
                .append(this.firstName)
                .append(' ')
                .append(this.lastName)
                .append(", ")
                .append(this.dateBorn)
                .append(')')
                .toString();
        return s;
    }
}
