package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

import static backend.Model.deleteFlight;
import static backend.Model.editFlight;

public class Main {
    public static void main(String[] args) throws SQLException {

        Model.initConnection();

        Model model = new Model();



    }
}