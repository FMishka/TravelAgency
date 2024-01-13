import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        DbFunctions db=new DbFunctions();
        // db connection using postgres
        Connection conn=db.connection("TravelAgency","postgres","admin");
//        db.createTable(conn,"employee");
//        db.insert_row(conn,"employee","GK","Voyager-1");
        //db.update_name(conn,"employee","GK","RGK");
        //db.search_by_name(conn,"employee","RGK");
        //db.delete_row_by_name(conn,"employee","RGK");
        //db.delete_row_by_id(conn,"employee",4);
        //db.read_data(conn,"employee");
        //db.delete_table(conn,"employee");

        String password = new String(Files.readAllBytes(Paths.get("password.txt")), StandardCharsets.UTF_8);
        conn = db.connection("TravelAgency","postgres",password);

        String[] fields = new String[]{
                "user_id",
                "login",
                "userpassword",
                "isadmin",
                "passport"
        };

        String[] values = new String[]{
                "2",
                "'login'",
                "123",
                "false",
                "123456789"
        };

        db.insertRow(conn, "users", values);

        Model model = new Model();

    }
}