package backend;


import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        Model.initConnection();
        LocalDateTime lc = LocalDateTime.now().minusYears(1);
        LocalDateTime lc1 = LocalDateTime.now().plusYears(1);
        String[][] str = Model.filterFlights(100, 1000, lc, lc1, "Tel Aviv (TLV)", "");
        for(int i = 0; i < str.length; i++){
            for (int j = 0; j < str[0].length; j++){
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }
    }
}