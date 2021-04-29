package Bookster;

import com.opencsv.bean.CsvBindByName;

public class ClientVisitor implements Visitor<Client> {
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
    String firstName;

    @CsvBindByName(column="lastName", required=true)
    String lastName;

    @CsvBindByName(column="dateBorn", required=true)
    String dateBorn;

    @Override
    public Client convert() {
        return new Client(firstName, lastName, dateBorn);
    }
}
