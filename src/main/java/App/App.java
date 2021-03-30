package App;

import Bookster.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    private static void populate(BookService srv) {
        Author[] authors = {
                new Author("Mihai", "Eminescu", "1850-01-15"),
                new Author("Ion", "Creanga", "1837-03-13"),
                new Author("Ion Luca", "Caragiale", "1852-03-13"),
                new Author("Ioan", "Slavici", "1848-01-18"),
                new Author("Marin", "Preda", "1840-01-15"),
                new Author("Titu", "Maiorescu", "1840-02-15"),
        };

        Publisher[] publishers = {
                new Publisher("Penguin"),
                new Publisher("Hatchet"),
                new Publisher("Harper"),
                new Publisher("Schuster"),
                new Publisher("Macmillan"),
                new Publisher("Junimea"),
        };

        Client[] clients = {
                new Client("Ema", "Ivan", "1952-04-06"),
                new Client("Beatrice", "Hall", "1975-08-07"),
                new Client("Barry", "Anderson", "1992-02-04"),
                new Client("Henry", "Craig", "1948-02-12"),
                new Client("Deann", "Alexander", "1984-01-03"),
                new Client("Glenda", "O'brien", "1976-10-19"),
        };

        Book[] books = {
                new Book("Forge of the Heart", BookGenre.CRIME, 11.99, "1986-04-23", authors[0],
                        publishers[0]),
                new Book("The Ghost in the West", BookGenre.TRAVEL, 9.99, "2017-07-07",
                        authors[3], publishers[2]),
                new Book("Crimson Leviathan", BookGenre.ACTION, 39.99, "2016-11-08",
                        authors[4], publishers[1]),
                new Book("Male Delivery", BookGenre.BIOGRAPHY, 93.99, "2002-04-02",
                        authors[2], publishers[3]),
                new Book("Clue of the Crooked Puppet", BookGenre.BUSINESS, 54.99, "2009-10-13",
                        authors[3], publishers[3]),
        };

        books[0].addAuthor(authors[1]);

        Transaction[] transactions = {
                new Transaction(books[0], clients[2], "2021-01-12", 2),
                new Transaction(books[3], clients[1], "2021-02-03", 1),
                new Transaction(books[4], clients[0], "2021-01-12", 5),
                new Transaction(books[0], clients[4], "2021-01-23", 1),
                new Transaction(books[2], clients[3], "2021-03-01", 10),
        };

        BookReview[] reviews = {
                new BookReview(books[0], clients[3], "Review.", 4),
                new BookReview(books[2], clients[1], "Review.", 2),
                new BookReview(books[4], clients[2], "Review.", 3),
                new BookReview(books[1], clients[1], "Review.", 1),
                new BookReview(books[3], clients[4], "Review.", 3),
        };

        Arrays.stream(authors).forEach(srv::register);
        Arrays.stream(publishers).forEach(srv::register);
        Arrays.stream(clients).forEach(srv::register);
        Arrays.stream(books).forEach(srv::register);
        Arrays.stream(transactions).forEach(srv::register);
        Arrays.stream(reviews).forEach(srv::register);
    }

    public static void main(String[] args) {
        BookService srv = BookService.INSTANCE;
        populate(srv);
        System.out.println("Authors:");
        srv.authorStream().forEach(a -> System.out.println(a.getValue()));
        System.out.println("Publishers:");
        srv.publisherStream().forEach(p -> System.out.println(p.getValue()));
        System.out.println("Clients:");
        srv.clientStream().forEach(c -> System.out.println(c.getValue()));
        System.out.println("Books:");
        srv.bookStream().forEach(b -> System.out.println(b.getValue()));
        System.out.println("Transactions:");
        srv.transactionStream().forEach(t -> System.out.println(t.getValue()));
        System.out.println("Reviews:");
        srv.reviewStream().forEach(r -> System.out.println(r.getValue()));

        System.out.println("Transactions on 2021-01-12:");
        System.out.println(srv.getTransactionsOnDate(LocalDate.parse("2021-01-12")).toString());
    }
}
