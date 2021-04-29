package Bookster;

import App.App;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;

public enum CSVService {
    INSTANCE();

    private FileWriter audit;

    CSVService() {
        try {
            this.audit = new FileWriter("audit.csv");
            this.audit.write("action,timestamp\n");
        } catch(Exception e) {
            System.out.println("Failed to create audit.csv!");
        }
    }

    public <T extends Visitor> void register(String filePath, BookService srv,
                                             Class<T> rtti) {
        try {
            URL resource = App.class.getClassLoader().getResource(filePath);
            var data = new CsvToBeanBuilder<T>(new FileReader(resource.getFile()))
                    .withType(rtti).build().parse();
            data.stream().forEach(v -> srv.register(v.convert()));
        } catch (Exception e) {
            System.out.println("Failed reading CSV(" + filePath + "): " + e.getMessage());
        }
    }

    public void logAction(String name) {
        var date = LocalDate.now();
        var format = new StringBuilder()
                .append('"')
                .append(name)
                .append('"')
                .append(',')
                .append('"')
                .append(date)
                .append('"')
                .append('\n');

        try {
            this.audit.write(format.toString());
        }
        catch(Exception e) {
            System.out.println("Failed to log to audit, action=" + name + ": " + e.getMessage());
        }
    }

    public void shutdown() {
        try {
            this.audit.close();
        }
        catch(Exception e) {
            System.out.println("Can't close audit file!");
        }
    }
}
