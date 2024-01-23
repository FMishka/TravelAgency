package backend;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        Model.initConnection();
        Model model = new Model();
    }
}