package Bookster;

import com.opencsv.bean.CsvBindByName;

public class AuthorVisitor implements Visitor<Author> {
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateBorn() {
        return dateBorn;
    }

    @CsvBindByName(column="firstName", required=true)
    private String firstName;

    @CsvBindByName(column="lastName", required=true)
    private String lastName;

    @CsvBindByName(column="dateBorn", required=true)
    private String dateBorn;

    @Override
    public Author convert() {
        return new Author(firstName, lastName, dateBorn);
    }
}
