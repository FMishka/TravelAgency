package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static backend.Model.deleteFlight;
import static backend.Model.editFlight;

public class Main {
    public static void main(String[] args){
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
        //Model.addFlight("hui", a, b, 6, 7, 12, 1);
        //Model.editFlight(1, "hui", a ,b , 6, 7, 1, 12);
        //Model.deleteFlight(1);
        Model model = new Model();
        //Model.addFlight("hui", a, b, 6, 7, 12, 1);


    }
}