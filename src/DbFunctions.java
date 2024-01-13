import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DbFunctions {

    //Создает либо пересоздает таблицы и заполняет их начальными данными
    public void initDB(Connection conn) {
        Statement statement;
        try {
            String query = new String(Files.readAllBytes(Paths.get("DB_init_script.sql")), StandardCharsets.UTF_8);
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Database initialized");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection connection(String dbname, String user, String pass){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void insertRow(Connection conn,String tableName, String[] arrTableFields, String[] arrValues){
        Statement statement;
        try {
            String query = "";
            if (arrTableFields != null){
                String strTableFields = String.join(",", arrTableFields);
                String strValues = String.join(",", arrValues);
                query = String.format("insert into %s(%s) values(%s);",tableName,strTableFields,strValues);
            }
            else{
                String strValues = String.join(",", arrValues);
                query = String.format("insert into %s values(%s);",tableName,strValues);
            }
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void insertRow(Connection conn,String tableName, String[] arrValues){
        insertRow(conn, tableName, null, arrValues);
    }
    public void readData(Connection conn, String table_name) {
        System.out.println("Reading data from " + table_name);
        String query = String.format("SELECT * FROM %s", table_name);

        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("address") + " ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateName(Connection conn, String table_name, String old_name, String new_name){
        Statement statement;
        try {
            String query=String.format("update %s set name='%s' where name='%s'",table_name,new_name,old_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchByName(Connection conn, String table_name, String name){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s where name= '%s'",table_name,name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchById(Connection conn, String table_name, int id){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s where empid= %s",table_name,id);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteRowByName(Connection conn, String table_name, String name){
        Statement statement;
        try{
            String query=String.format("delete from %s where name='%s'",table_name,name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void deleteRowById(Connection conn, String table_name, int id){
        Statement statement;
        try{
            String query=String.format("delete from %s where empid= %s",table_name,id);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteTable(Connection conn, String table_name){
        Statement statement;
        try {
            String query= String.format("drop table %s",table_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}