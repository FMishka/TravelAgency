package backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){
        Model model = new Model();
        Model.addPaymentData(3, "4580010554876947", "02/12", "Shimon Roytman", "300");

        String[] arrStr = Model.getUserPaymentData(3);

        for(String s: arrStr){
            System.out.println(s);
        }
    }
}