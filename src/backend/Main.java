package backend;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static backend.Model.*;

public class Main {
    public static void main(String[] args) throws Exception {


        Model model = new Model();
        Model.initConnection();

        String[][] str = Model.sortFlightsBy("arrivaldateDOWN");
        for(int i = 0; i < str.length; i++){
            for (int j = 0; j < str[0].length; j++){
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }
    }
}