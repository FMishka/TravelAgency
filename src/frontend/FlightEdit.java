package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class FlightEdit {
    private JPanel content;
    private JPanel mainPanel;
    private JTextField flightId;
    private JTextField flightName;
    private JTextField arrDate;
    private JTextField price;
    private JTextField depDate;
    private JPanel bottomPanel;
    private JButton confirm;
    private JPanel topPanel;
    private JButton cancel;
    private JComboBox<String> depCountry;
    private JComboBox<String> arrCountry;
    private JComboBox<String> plane;

    public JPanel getContent() {
        return content;
    }

    public void setData(String[] data) {
        flightId.setText(data[0]);
        flightName.setText(data[1]);
        depDate.setText(data[2]);
        arrDate.setText(data[3]);
        depCountry.setModel(new DefaultComboBoxModel<String>(Controller.countries));
        depCountry.setSelectedItem(data[4]);
        arrCountry.setModel(new DefaultComboBoxModel<String>(Controller.countries));
        arrCountry.setSelectedItem(data[5]);

        price.setText(data[6]);

        plane.setModel(new DefaultComboBoxModel<String>(Controller.planes));
        plane.setSelectedItem(data[7]);

        //System.out.println(Arrays.toString(data));
    }

    public FlightEdit() {
        mainPanel.addMouseListener(Controller.backMouseButton());
        topPanel.addMouseListener(Controller.backMouseButton());
        bottomPanel.addMouseListener(Controller.backMouseButton());
        content.addMouseListener(Controller.backMouseButton());
        for(Component component : mainPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton());
        for(Component component : bottomPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton());
        for(Component component : topPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton());

        depDate.setInputVerifier(new DateTimeInputVerifier());
        arrDate.setInputVerifier(new DateTimeInputVerifier());

        cancel.addActionListener(Controller.navigateFlightInfo());

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(flightId.getText());
                    String name = flightName.getText();
                    LocalDateTime departureTime = LocalDateTime.parse(depDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime arrivalTime = LocalDateTime.parse(arrDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    String departureCountry = depCountry.getSelectedItem().toString();
                    String arrivalCountry = arrCountry.getSelectedItem().toString();
                    String planeName = plane.getSelectedItem().toString();
                    int flightPrice = Integer.parseInt(price.getText());

                    Model.editFlight(id, name, departureTime, arrivalTime, departureCountry, arrivalCountry, planeName, flightPrice);
                    showMessageDialog(null, "Flight edited", "Success", JOptionPane.INFORMATION_MESSAGE);
                    View.refreshFlightsPage();
                    View.goToFlightInfo(new String[]{flightId.getText(), flightName.getText(), depDate.getText(), arrDate.getText(),
                            depCountry.getSelectedItem().toString(), arrCountry.getSelectedItem().toString(), price.getText(), plane.getSelectedItem().toString()});
                } catch (Exception ex) {
                    showMessageDialog(null, ex.getMessage(), "Invalid data", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
