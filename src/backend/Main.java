package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDateTime;
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

       // Model model = new Model();
        LocalDateTime z = LocalDateTime.of(2024, 2, 1 , 12,00);
        LocalDateTime x = LocalDateTime.of(2025,11,10,13,00);


String [] [] c = Model.filterFlights(0, 0, z, x , null, null);
for (int i = 0; i < c.length; i++ ) {
    for (int j = 0; j < c[0].length; j++){
        System.out.print(c[i] [j] + " ");
    }
    System.out.println();
}
        //Date date = new Date();

        //System.out.println(Model.orderingTicket(2, 1, 1,1,true,"Shimon", "Roytman", date, 'M'));
    }
}