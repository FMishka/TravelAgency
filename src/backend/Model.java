package backend;
import frontend.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDateTime;

import java.io.*;

public class Model {
    private static DbFunctions db;
    private static Connection conn;


    public Model() throws IOException {
        String password = new String(Files.readAllBytes(Paths.get("password.txt")), StandardCharsets.UTF_8);

        db = new DbFunctions();
        conn = db.connection("TravelAgency","postgres",password);

        db.initDB(conn);

        View.init();    //Initializing GUI
    }


    //Этот метод должен возвращать -1 если креды невалидные, 0 если они валидные и пользователь не админ и 1 если креды валидные и это админ
    public static int validateCredentials(String login, String password) {
        if(login.equals("admin")) return 1;
        if(login.equals("user")) return 0;
        return -1;
    }

    public void addFlight(String flightName, LocalDateTime departureTime, LocalDateTime arrivalTime, int departureCountryId, int arrivalCountryId, int planeId, int price) {

    }
}
