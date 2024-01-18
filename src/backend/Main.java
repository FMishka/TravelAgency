package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Date;

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

        Model.initConnection();

        Model model = new Model();

        Date date = new Date();

        System.out.println(Model.orderingTicket(2, 1, 1,1,true,"Shimon", "Roytman", date, 'M'));
    }
}