package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static backend.Model.deleteFlight;
import static backend.Model.editFlight;

public class Main {
    public static void main(String[] args) throws SQLException {
//        String password = "";
//        try{
//            password = new String(Files.readAllBytes(Paths.get("password.txt")), StandardCharsets.UTF_8);
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        DbFunctions db = new DbFunctions();
//        Connection conn = db.connection("TravelAgency","postgres",password);
//
//        db.initDB(conn);
        LocalDateTime a = LocalDateTime.now();
        LocalDateTime b = LocalDateTime.now();
        Model.initConnection();
        //System.out.println(Model.searchingCountryId("Israel"));
        //System.out.println(Model.searchingPlaneId("Boeing737-800"));
        Model model = new Model();
        //Model.editByWordFlight(1, "hui", a ,b , "Israel", "Russia", "A-322", 12);
        Model.addByWordFlight("hui", a, b, "Israel", "Russia", "A-321", 12);
        //Model.addFlight("hui", a, b, 6, 7, 12, 1);


    }
}