package backend;

import frontend.View;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Model {
    private static DbFunctions db;
    private static Connection conn;
    private static int userId = -1;
    public static boolean isAdmin = false;

    public static void initConnection(){
        String password = "";
        try{
            password = new String(Files.readAllBytes(Paths.get("password.txt")), StandardCharsets.UTF_8);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        db = new DbFunctions();
        conn = db.connection("TravelAgency","postgres",password);
        db.initDB(conn);
    }

    public Model() {
        initConnection();
        View.init();    //Initializing GUI
    }
    //Этот метод должен возвращать -1 если креды невалидные, 0 если они валидные и пользователь не админ и 1 если креды валидные и это админ
    public static int validateCredentials(String login, String password) {
        Statement statement;
        String user = String.format("SELECT * FROM users WHERE login = '%s' AND userpassword = '%s'", login, password);
        String admin = String.format("SELECT isAdmin FROM users WHERE login = '%s' AND userpassword = '%s'", login, password);
        isAdmin = false;
        boolean isAuthCorrect = false;
        try{
            ResultSet resultSet;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(user);
            if (resultSet.next()){
                isAuthCorrect = true;
                userId = resultSet.getInt(1);
            }
            resultSet = statement.executeQuery(admin);
            if (resultSet.next()){
                isAdmin = resultSet.getBoolean(1);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(isAdmin) return 1;
        if(isAuthCorrect) return 0;
        return -1;
    }

    private static boolean isCorrectCreditCardDetails(String cardNumber, String expireDate, int cvv){
        return cardNumber.length() == 16 && expireDate.matches("\\d{2}/\\d{2}") && cvv > 99 && cvv < 1000;
    }

    private static String aesEncrypt(String element){
        try{
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec("bestProjectInTheWorld!!!".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedElement = cipher.doFinal(element.getBytes());
            return DatatypeConverter.printHexBinary(encryptedElement);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String aesDecrypt(String element){
        try{
            Cipher cipher =  Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec("bestProjectInTheWorld!!!".getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] dectyptElement = cipher.doFinal(DatatypeConverter.parseHexBinary(element));
            return new String(dectyptElement);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void addPaymentData(int id, String cardNumber, String expireDate, String cardName, String cvv){
        try{
            if(isCorrectCreditCardDetails(cardNumber, expireDate, Integer.parseInt(cvv))){
                String[] paymentData = new String[] {Integer.toString(id),
                        "'" + Model.aesEncrypt(cardNumber) + "'",
                        "'" + Model.aesEncrypt(expireDate) + "'",
                        "'" + Model.aesEncrypt(cardName) + "'",
                        "'" + Model.aesEncrypt(cvv) + "'"
                };
                db.insertRow(conn, "paymentData", paymentData);
            }
            else{
                System.out.println("Credit card details are not correct!");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String[] getUserPaymentData(int id){
        Statement statement;
        String userPaymentDataQuery = String.format("SELECT * FROM paymentData WHERE fk_user_ID = %s", id);
        String[] userPaymentData = new String[5];
        try{
            ResultSet resultSet;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(userPaymentDataQuery);

            while(resultSet.next()){
                userPaymentData[0] = Integer.toString(resultSet.getInt(1));
                userPaymentData[1] = Model.aesDecrypt(resultSet.getString(2));
                userPaymentData[2] = Model.aesDecrypt(resultSet.getString(3));
                userPaymentData[3] = Model.aesDecrypt(resultSet.getString(4));
                userPaymentData[4] = Model.aesDecrypt(resultSet.getString(5));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userPaymentData;
    }
    public static int[][] getAllFlyghtSeats(int flightId){
        String flightQuery = "";
        Statement statement;
        int[][] allFlightSeats = null;
        try{
            flightQuery = String.format("SELECT flights.flight_ID, flights.fk_plane_ID, planes.rowsNumber, planes.columnsNumber FROM flights LEFT JOIN planes\n" +
                    "ON flights.fk_plane_id = plane_ID WHERE flights.flight_ID = %s", flightId);
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(flightQuery);
            resultSet.next();
            allFlightSeats = new int[resultSet.getInt("rowsnumber")][resultSet.getInt("columnsnumber")];
            initAllFlyghtSeats(flightId, allFlightSeats);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return allFlightSeats;
    }
    private static void initAllFlyghtSeats(int flyghtId, int[][] allFlightSeats){
        String ticketQuery = "";
        Statement statement;
        try{
            ticketQuery = String.format("SELECT fk_flight_ID, fk_user_ID, seatRow, seatColumn FROM tickets\n" +
                    "WHERE fk_flight_ID = %s", flyghtId);
            statement = conn.createStatement();
            ResultSet resultSet =statement.executeQuery(ticketQuery);
            for(int i = 0; i < allFlightSeats.length; i++){
                for (int j = 0; j < allFlightSeats[0].length; j++){
                    allFlightSeats[i][j] = -1;
                }
            }
            while(resultSet.next()){
                int seatRow = resultSet.getInt("seatrow") - 1;
                int seatColumn = resultSet.getInt("seatcolumn") - 1;
                if (resultSet.getInt("fk_user_id") == userId){
                    allFlightSeats[seatRow][seatColumn] = 1;
                }
                else{
                    allFlightSeats[seatRow][seatColumn] = 0;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static boolean isSeatFree(int flyightId, int seatRow, int seatColumn){
        int[][] allSeats = Model.getAllFlyghtSeats(flyightId);
        if (allSeats[seatRow][seatColumn] < 0){
            return true;
        }
        return false;
    }
    private static boolean isFlightValid(int flyightId){
        String getDate = String.format("SELECT departureDate, flight_ID FROM flights WHERE flight_ID = %s", flyightId);
        Statement statement;
        try{
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(getDate);
            resultSet.next();
            Date date = resultSet.getDate(1);
            Date today = new Date();
            if (today.before(date)){
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean orderingTicket(int flyightId, int userId, int seatRow, int seatColumn, boolean isPayed, String passengerFirstName, String passengerSecondName, LocalDateTime passengerBirthDate, char passengerSex){
        String[] valuesTickets = new String[]{
                Integer.toString(flyightId),
                Integer.toString(userId),
                Integer.toString(seatRow),
                Integer.toString(seatColumn),
                Boolean.toString(isPayed),
                "'" + passengerFirstName + "'",
                "'" + passengerSecondName + "'",
                "'" + passengerBirthDate.getDayOfMonth() + "-" + passengerBirthDate.getMonthValue() + "-" + passengerBirthDate.getYear() + "'",
                "'" + passengerSex + "'"
        };
        Statement statement;
        try{
            if (isSeatFree(flyightId, seatRow, seatColumn) && isFlightValid(flyightId)){
                db.insertRow(conn, "tickets", valuesTickets);
                return true;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static int searchingCountryId(String countryName) throws SQLException {
        Statement statement;
        statement = conn.createStatement();
        String searchingCountryId = String.format("SELECT country_id FROM countries WHERE countryname = '%s';", countryName);
        ResultSet set = statement.executeQuery(searchingCountryId);
        set.next();
        return set.getInt("country_id");
    }

    public static int searchingPlaneId(String planeName) throws SQLException {
        Statement statement;
        statement = conn.createStatement();
        String searchingPlaneId = String.format("SELECT plane_id FROM planes WHERE planemodel = '%s';", planeName);
        ResultSet set = statement.executeQuery(searchingPlaneId);
        set.next();
        return set.getInt("plane_id");
    }

    public static boolean checkingFlightNameDuplicates(String flightName) throws SQLException {
        Statement statement;
        statement = conn.createStatement();
        String checkingDuplicates = String.format("SELECT COUNT(*) FROM flights WHERE flightname = '%s';", flightName);
        ResultSet set = statement.executeQuery(checkingDuplicates);
        set.next();
        return set.getInt("count") > 0;
    }

    public static void addFlight(String flightName, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureCountryName, String arrivalCountryName, String planeModel, int price) throws Exception {
        LocalDateTime check = LocalDateTime.now();
        if (departureTime.isBefore(check) || arrivalTime.isBefore(check)) throw new Exception("Date is not valid!");
        if (checkingFlightNameDuplicates(flightName)) throw new Exception("Flight name is duplicated!");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Statement statement;
        statement = conn.createStatement();
        String addFlightQuery = String.format("INSERT INTO flights (flightname,departuredate,arrivaldate,departurecountry_id, arrivalcountry_id, price, fk_plane_id) " +
                    "VALUES ('%s', '%s', '%s', %d, %d, %d, %d);", flightName, departureTime.format(format), arrivalTime.format(format), searchingCountryId(departureCountryName), searchingCountryId(arrivalCountryName), price, searchingPlaneId(planeModel));
        statement.execute(addFlightQuery);
    }

    public static void editFlight(int flightID, String flightName, LocalDateTime departureTime, LocalDateTime arrivalTime, String departureCountryName, String arrivalCountryName, String planeModel, int price) throws SQLException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Statement statement;
        String editFlightQuery = String.format("UPDATE flights SET flightname = '%s', departuredate = '%s', arrivaldate = '%s', departurecountry_id = %d, arrivalcountry_id = %d, price = %d, fk_plane_id = %d " +
                    "WHERE flight_id = %d;", flightName, departureTime.format(format), arrivalTime.format(format), searchingCountryId(departureCountryName), searchingCountryId(arrivalCountryName), price, searchingPlaneId(planeModel), flightID);
        statement = conn.createStatement();
        statement.executeUpdate(editFlightQuery);
    }
    public static void deleteFlight(int flight_id){
        Statement statement;
        try{
            String deleteTicketsQuery = String.format("DELETE FROM tickets  WHERE fk_flight_id = %d;", flight_id);
            String deleteFlightQuery = String.format("DELETE FROM flights  WHERE flight_id = %d;", flight_id);
            statement=conn.createStatement();
            statement.executeUpdate(deleteTicketsQuery);
            statement.executeUpdate(deleteFlightQuery);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static  String[][] filterFlights(int minPrice, int maxPrice,
                                            LocalDateTime minDateTime, LocalDateTime maxDateTime,
                                            String departureCountry, String arrivalCountry) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String[][] filteredFlights = null;

        try {

            StringBuilder filterQuery = new StringBuilder("SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                    "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                    "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                    "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id "+
                    "WHERE 1=1");

            List<Object> parameters = new ArrayList<>();


            if (minPrice >= 0) {
                filterQuery.append(" AND flights.price > ?");
                parameters.add(minPrice);
            }
            if (maxPrice > 0) {
                filterQuery.append(" AND flights.price < ? ");
                parameters.add(maxPrice);
            }
            if (minDateTime != null) {
                filterQuery.append(" AND flights.departureDate > ? ");
                parameters.add(Timestamp.valueOf(minDateTime));
            }
            if (maxDateTime != null) {
                filterQuery.append(" AND flights.departureDate < ? ");
                parameters.add(Timestamp.valueOf(maxDateTime));
            }

            if (departureCountry != null) {
                filterQuery.append(" AND dep_country.countryName = ? ");
                parameters.add(departureCountry);
            }

            if (arrivalCountry != null) {
                filterQuery.append(" AND arr_country.countryName = ? ");
                parameters.add(arrivalCountry);
            }

            preparedStatement = conn.prepareStatement(filterQuery.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            Statement statement = conn.createStatement();
            resultSet = preparedStatement.executeQuery();


            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();


            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();


            filteredFlights =new String[rowCount][columnCount];


            int rowIndex = 0;
            while (resultSet.next()) {
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    filteredFlights[rowIndex][colIndex - 1] = resultSet.getString(colIndex);
                }
                rowIndex++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return filteredFlights;
    }
    public static String[][] sortFlightsBy(String sortBy) {
        List<String[]> resultsList = new ArrayList<>();

        try {
            String query = "";

            switch (sortBy) {
                case "priceUP":
                    query = "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id "+
                            "ORDER BY price DESC";
                    break;
                case "priceDOWN":
                    query = "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id " +
                            "ORDER BY price";
                    break;
                case "countriesDepartureUp":
                    query = "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id " +
                            "ORDER BY dep_country.countryName ASC";
                    break;
                case "countriesDepartureDown":
                    query = "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id " +
                            "ORDER BY dep_country.countryName DESC";
                    break;
                case "countriesArrivalUp":
                    query =  "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id " +
                            "ORDER BY arr_country.countryName ASC";
                    break;
                case "countriesArrivalDown":
                    query =  "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id " +
                            "ORDER BY arr_country.countryName DESC ";
                    break;
                case "ticketsUp":
                    query = "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel, COUNT(tick.fk_flight_ID ) as ticketsSold FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "LEFT JOIN tickets AS tick ON flights.flight_ID = tick.fk_flight_ID "    +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id "+
                            "GROUP BY flights.flight_ID, dep_country.countryname, arr_country.country_ID, plane.planemodel ORDER BY ticketsSold DESC";
                    break;
                case "ticketsDown":
                    query = "SELECT flight_ID, flightname, departureDate,  arrivalDate, dep_country.countryName, arr_country.countryName, price, plane.planemodel, COUNT(tick.fk_flight_ID ) as ticketsSold FROM flights " +
                            "JOIN countries AS dep_country ON flights.departureCountry_ID = dep_country.country_ID " +
                            "JOIN countries AS arr_country ON flights.arrivalCountry_ID = arr_country.country_ID " +
                            "LEFT JOIN tickets AS tick ON flights.flight_ID = tick.fk_flight_ID " +
                            "JOIN planes AS plane ON flights.fk_plane_ID = plane.plane_id "+
                            "GROUP BY flights.flight_ID, dep_country.countryname, arr_country.country_ID, plane.planemodel ORDER BY ticketsSold ASC";
                    break;
                default:
                    System.out.println("Invalid sorting option.");
                    return new String[0][0];
            }

            try (PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    // Создание строки для каждой записи в результате запроса
                    String[] row = new String[resultSet.getMetaData().getColumnCount()];
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        row[i - 1] = resultSet.getString(i);
                    }
                    resultsList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Преобразование списка в двумерный массив
        return resultsList.toArray(new String[0][0]);
    }
    public static void printSortedFlights(String sortBy) {
        String[][] sortedFlights = sortFlightsBy(sortBy);

        if (sortedFlights.length == 0) {
            System.out.println("No data to display.");
            return;
        }
        for (int i = 1; i <= sortedFlights[0].length; i++) {
            System.out.print(sortedFlights[0][i - 1] + "\t\t");
        }
        System.out.println();

        // Вывод данных
        for (int i = 1; i < sortedFlights.length; i++) {
            for (int j = 1; j <= sortedFlights[i].length; j++) {
                System.out.print(sortedFlights[i][j - 1] + "\t\t");
            }
            System.out.println();
        }
    }
    public static String[][] getAllFlights() {
        Statement statement;
        String getAllFlightQuery = "SELECT flights.flight_id, flights.flightname, flights.departuredate, flights.arrivaldate, c1.countryname, c2.countryname, flights.price, pl.planeModel\n" +
                "FROM flights\n" +
                "LEFT JOIN countries c1 ON flights.departurecountry_id = c1.country_ID\n" +
                "LEFT JOIN countries c2 ON flights.arrivalcountry_id = c2.country_ID\n" +
                "LEFT JOIN planes pl ON flights.fk_plane_id = pl.plane_ID;\n";
        String getCountRowsQuery = "SELECT COUNT(*) FROM flights";
        ResultSet resultSet = null;
        String[][] res = null;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(getCountRowsQuery);
            resultSet.next();
            int numberOfRows = resultSet.getInt(1);
            resultSet = statement.executeQuery(getAllFlightQuery);
            int numberOfColumns = resultSet.getMetaData().getColumnCount();
            res = new String[numberOfRows][numberOfColumns];
            for (int i = 0; i < numberOfRows; i++){
                resultSet.next();
                for (int j = 0; j < numberOfColumns; j++) {
                    if (j == 2 || j == 3){ // Date without seconds and milliseconds
                        res[i][j] = String.format(resultSet.getString(j + 1).substring(0, 16));

                    }
                    else{
                        res[i][j] = String.format(resultSet.getString(j + 1));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    //Умоляю сделайте эти методы

    public static String[] getAllCountries() {
        Statement statement;
        String getCountRowsQuery = "SELECT COUNT(*) FROM countries";
        String query = "SELECT * FROM countries";

        String[] res = null;
        ResultSet resultSet = null;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(getCountRowsQuery);
            resultSet.next();
            int numberOfRows = resultSet.getInt(1);

            resultSet = statement.executeQuery(query);
            res = new String[numberOfRows];

            for (int i = 0; i < numberOfRows; i++) {
                resultSet.next();

                res[i] = resultSet.getString(2);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static String[] getAllPlaneNames() {
        Statement statement;
        String getCountRowsQuery = "SELECT COUNT(*) FROM planes";
        String query = "SELECT * FROM planes";

        String[] res = null;
        ResultSet resultSet = null;

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(getCountRowsQuery);
            resultSet.next();
            int numberOfRows = resultSet.getInt(1);

            resultSet = statement.executeQuery(query);
            res = new String[numberOfRows];

            for (int i = 0; i < numberOfRows; i++) {
                resultSet.next();

                res[i] = resultSet.getString(2);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
}
