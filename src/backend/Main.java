package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.io.*;

public class Main {
    public static void main(String[] args){
        //Model model = new Model();
        System.out.println(Model.isCorrectRequisites("4580010554876947", "02/27", 123));
    }
}