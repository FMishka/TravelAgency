import java.sql.Connection;
import java.time.LocalDateTime;

public class Model {
    private static DbFunctions db;
    private static Connection conn;

    private static View view;

    public Model() {
        db = new DbFunctions();
        conn = db.connect_to_db("TravelAgency","postgres","admin");

        db.initDB(conn);

        view = new View();
    }

    public void addFlight(String flightName, LocalDateTime departureTime, LocalDateTime arrivalTime, int departureCountryId, int arrivalCountryId, int planeId, int price) {

    }
}
