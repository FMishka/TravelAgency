package frontend;

import javax.swing.*;
import java.awt.*;

public class FlightInfo {
    private JPanel content;
    private JButton back;
    private JTextField flightId;
    private JTextField flightName;
    private JTextField depDate;
    private JTextField depCountry;
    private JTextField arrDate;
    private JTextField arrCountry;
    private JTextField price;
    private JTextField plane;
    private JPanel mainPanel;
    private JButton edit;
    private JPanel bottomPanel;
    private JButton delete;
    private JPanel topPanel;
    private JButton buy;

    private final JTextField[] fieldsArray;

    private String[] data;
    public int curFlightId;

    public JPanel getContent() {
        return content;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
        curFlightId = Integer.parseInt(data[0]);

//        flightId.setText(data[0]);
//        flightName.setText(data[1]);
//        depDate.setText(data[2]);
//        arrDate.setText(data[3]);
//        depCountry.setText(data[4]);
//        arrCountry.setText(data[5]);
//        price.setText(data[6]);
//        plane.setText(data[7]);

        for(int i = 0; i < fieldsArray.length; i++) {
            fieldsArray[i].setText(data[i]);
        }
    }


    public FlightInfo() {
        fieldsArray = new JTextField[]{flightId, flightName, depDate, arrDate, depCountry, arrCountry, price, plane};

        data = new String[8];
        back.addActionListener(Controller.navigateFlights());
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

        edit.addActionListener(Controller.editButton(this));
        delete.addActionListener(Controller.deleteButton(this));
    }

    public void updateAdminStatus(boolean isAdmin) {
        delete.setVisible(isAdmin);
        edit.setVisible(isAdmin);
    }
}
