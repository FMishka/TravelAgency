package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

public class FlightsPage {
    private JPanel content;
    private JTable table;
    private JPanel navigation;
    private JButton homePageButton;
    private JButton anotherPageButton;
    private JButton flightsButton;
    private JPanel mainPanel;

    public FlightsPage() {
        homePageButton.addActionListener(Controller.navigateHome());
        anotherPageButton.addActionListener(Controller.navigateAnother());
        flightsButton.addActionListener(Controller.navigateFlights());

        //System.out.println(Arrays.deepToString(Model.getAllFlights()));
        table = new JTable(Model.getAllFlights(), new String[]{"flight_ID", "flightname", "departureDate", "arrivalDate", "departureCountry_ID", "arrivalCountry_ID", "price", "fk_plane_ID"});
        mainPanel.add(table);
    }

    public JPanel getContent() {
        return content;
    }
}
