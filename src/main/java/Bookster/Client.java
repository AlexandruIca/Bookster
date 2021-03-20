package Bookster;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public final class Client implements Unique {
    /**
     * Static atomic that holds increasing IDs for clients.
     */
    private static AtomicLong clientID = new AtomicLong(0);

    /**
     * @return a new unique ID for clients, use this when creating a new client.
     * @apiNote Method is completely thread-safe.
     */
    private static Long newID() {
        return clientID.getAndIncrement();
    }

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateBorn;

    public Client(String firstName, String lastName, String dateBorn) {
        this.id = newID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBorn = LocalDate.parse(dateBorn);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateBorn() {
        return dateBorn;
    }

    @Override
    public Long getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return new StringBuilder()
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
    }
}
