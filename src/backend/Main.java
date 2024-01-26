package backend;


import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {


        Model model = new Model();
        Model.initConnection();
        //Model.printSortedFlights("ticketsDown");
        String departureCountryName = "Tel Aviv (TLV)";
        String arrivalCountryName = "New York (JFK)";
        LocalDateTime arrivalDateTime = LocalDateTime.parse("2024-02-15T05:00");
        System.out.println(arrivalDateTime);



        String[][] matchingRows = Model.checkingBackFlihgts(departureCountryName, arrivalCountryName , arrivalDateTime);

        System.out.println(Arrays.deepToString(matchingRows));

        //Model model = new Model();


    }
}