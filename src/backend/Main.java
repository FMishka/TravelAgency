package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

import java.time.LocalDateTime;

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


       // Model model = new Model();
        LocalDateTime z = LocalDateTime.of(2024, 2, 1 , 12,00);
        LocalDateTime x = LocalDateTime.of(2025,11,10,13,00);


//String [] [] c = Model.filterFlights(0, 0, z, x , null, null);
//for (int i = 0; i < c.length; i++ ) {
//    for (int j = 0; j < c[0].length; j++){
//        System.out.print(c[i] [j] + " ");
//    }
//    System.out.println();
//}
     Model.printSortedFlights("ticketsUp" );
        //Date date = new Date();

        //System.out.println(Model.orderingTicket(2, 1, 1,1,true,"Shimon", "Roytman", date, 'M'));

        //Model.addFlight("hui", a, b, 6, 7, 12, 1);
        //Model.editFlight(1, "hui", a ,b , 6, 7, 1, 12);
        //Model.deleteFlight(1);
        //Model model = new Model();
        //Model.addFlight("hui", a, b, 6, 7, 12, 1);



    }
}