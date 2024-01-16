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

    private static View view;

    public Model() throws IOException {
        String password = new String(Files.readAllBytes(Paths.get("password.txt")), StandardCharsets.UTF_8);

        db = new DbFunctions();
        conn = db.connection("TravelAgency","postgres",password);

        db.initDB(conn);

        view = new View();
    }

    public void addFlight(String flightName, LocalDateTime departureTime, LocalDateTime arrivalTime, int departureCountryId, int arrivalCountryId, int planeId, int price) {

    }
}
