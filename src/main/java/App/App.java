package App;

import Bookster.Author;
import Bookster.BookService;
import Bookster.Client;
import Bookster.Publisher;

public class App {
    private static void populate(BookService srv) {
        // authors
        srv.register(new Author("Mihai", "Eminescu", "1850-01-15"));
        srv.register(new Author("Ion", "Creanga", "1837-03-13"));
        srv.register(new Author("Ion Luca", "Caragiale", "1852-03-13"));
        srv.register(new Author("Ioan", "Slavici", "1848-01-18"));
        srv.register(new Author("Marin", "Preda", "1840-01-15"));
        srv.register(new Author("Titu", "Maiorescu", "1840-02-15"));

        // publishers
        srv.register(new Publisher("Penguin"));
        srv.register(new Publisher("Hatchet"));
        srv.register(new Publisher("Harper"));
        srv.register(new Publisher("Schuster"));
        srv.register(new Publisher("Macmillan"));
        srv.register(new Publisher("Junimea"));

        // clients
        srv.register(new Client("Ema", "Ivan", "1952-04-06"));
        srv.register(new Client("Beatrice", "Hall", "1975-08-07"));
        srv.register(new Client("Barry", "Anderson", "1992-02-04"));
        srv.register(new Client("Henry", "Craig", "1948-02-12"));
        srv.register(new Client("Deann", "Alexander", "1984-01-03"));
        srv.register(new Client("Glenda", "O'brien", "1976-10-19"));
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
    }
}
