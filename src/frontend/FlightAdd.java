package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static frontend.Utility.setPlaceholder;
import static javax.swing.JOptionPane.showMessageDialog;

public class FlightAdd {
    private JPanel mainPanel;
    private JTextField flightName;
    private JTextField arrDate;
    private JTextField price;
    private JTextField depDate;
    private JComboBox<String> depCountry;
    private JComboBox<String> arrCountry;
    private JComboBox<String> plane;
    private JPanel topPanel;
    private JButton back;
    private JPanel bottomPanel;
    private JButton confirm;
    private JPanel content;

    public void reset() {
        setPlaceholder(flightName, "AZ-000");
        setPlaceholder(price, "999");

        depCountry.setSelectedItem(Controller.countries[0]);
        arrCountry.setSelectedItem(Controller.countries[0]);
        plane.setSelectedItem(Controller.planes[0]);

        depDate.setText("2000-01-01 00:00");
        arrDate.setText("2000-01-01 00:00");
    }

    public FlightAdd() {
        back.addActionListener(Controller.navigateFlights());

        depCountry.setModel(new DefaultComboBoxModel<String>(Controller.countries));
        arrCountry.setModel(new DefaultComboBoxModel<String>(Controller.countries));
        plane.setModel(new DefaultComboBoxModel<String>(Controller.planes));

        depDate.setInputVerifier(new DateTimeInputVerifier());
        arrDate.setInputVerifier(new DateTimeInputVerifier());

        reset();

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = flightName.getText();
                    LocalDateTime departureTime = LocalDateTime.parse(depDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime arrivalTime = LocalDateTime.parse(arrDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    String departureCountry = depCountry.getSelectedItem().toString();
                    String arrivalCountry = arrCountry.getSelectedItem().toString();
                    String planeName = plane.getSelectedItem().toString();
                    int flightPrice = Integer.parseInt(price.getText());

                    Model.addFlight(name, departureTime, arrivalTime, departureCountry, arrivalCountry, planeName, flightPrice);
                    showMessageDialog(null, "Flight added", "Success", JOptionPane.INFORMATION_MESSAGE);

                    View.refreshFlightsPage();
                    View.goToFlightsPage();

                    reset();
                } catch (Exception ex) {
                    showMessageDialog(null, ex.getMessage(), "Invalid data", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getContent() {
        return content;
    }
}
