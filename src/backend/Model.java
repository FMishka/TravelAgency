package backend;
import frontend.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Model {
    private static DbFunctions db;
    private static Connection conn;


    public Model() {
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

        View.init();    //Initializing GUI
    }


    //Этот метод должен возвращать -1 если креды невалидные, 0 если они валидные и пользователь не админ и 1 если креды валидные и это админ
    public static int validateCredentials(String login, String password) {
        Statement statement;

        String user = String.format("SELECT * FROM users WHERE login = '%s' AND userpassword = '%s'", login, password);
        String admin = String.format("SELECT isAdmin FROM users WHERE login = '%s' AND userpassword = '%s'", login, password);
        boolean isAdmin = false;
        boolean isAuthCorrect = false;
        try{
            ResultSet resultSet;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(user);
            if (resultSet.next()){
                isAuthCorrect = true;
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

    public static boolean isCorrectCreditCardDetails(String cardNumber, String expireDate, int cvv){
        return cardNumber.length() == 16 && expireDate.matches("\\d{2}/\\d{2}") && cvv > 99 && cvv < 1000;
    }

    public static String aesEncrypt(String element){
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

    public void addFlight(String flightName, LocalDateTime departureTime, LocalDateTime arrivalTime, int departureCountryId, int arrivalCountryId, int planeId, int price) {

    }

    //Тут лютый говнокод
    public static String[][] getAllFlights() {
        Statement statement;
        String query = "SELECT * FROM flights";

        ResultSet resultSet = null;
        String[][] res = null;
        ArrayList<String[]> resArrayList = new ArrayList<>();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            int numberOfColumns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String[] tmp = new String[numberOfColumns];

                for (int j = 0; j < numberOfColumns; j++) {
                    tmp[j] = resultSet.getString(j + 1);
                }

                resArrayList.add(tmp);
            }

            int numberOfRows = resArrayList.size();

            res = new String[numberOfRows][numberOfColumns];
            for(int i = 0; i < numberOfRows; i++) {
                res[i] = resArrayList.get(i);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }
}
