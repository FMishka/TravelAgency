package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

import static backend.Model.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Model.initConnection();
        LocalDateTime l = LocalDateTime.now();
        LocalDateTime a = l.plusDays(4);
        Model model = new Model();
        //System.out.println(Model.checkingFlightNameDuplicates("AZ-428"));
        //System.out.println(searchingFlightName(1));
        //Model.addFlight("AZ-438", a, a, "Moscow (SVO)", "Tel Aviv (TLV)", "A-320", 123);
        Model.editFlight(1, "AZ-445", a, a, "Moscow (SVO)", "Tel Aviv (TLV)", "A-320", 123);
        //Model.printSortedFlights("ticketsDown");

    }
}