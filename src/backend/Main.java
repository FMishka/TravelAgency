package backend;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {

        Model.initConnection();
        String[][] str = Model.getUsersAllTickets(2);
        for (int i = 0; i < str.length; i++){
            for (int j = 0; j < str[0].length; j++){
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }

    }
}