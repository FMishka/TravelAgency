package backend;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static backend.Model.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //Model model = new Model();
        Model.initConnection();
        //Model.printSortedFlights("ticketsDown");
        String departureCountryName = "Tel Aviv (TLV)";
        String arrivalCountryName = "Moscow (SVO)";
        Date departureDate = new Date();


        String[][] matchingRows = checkingBackCountries(departureCountryName,arrivalCountryName, departureDate);

        for (int i = 0; i < matchingRows.length; i++) {
            for (int j = 0; j < matchingRows[i].length; j++) {
                System.out.print(matchingRows[i][j] + "\t" );
            }
            System.out.println();
        }

    }
}