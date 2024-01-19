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

    private String[] data;

    public void setData(String[] data) {
        this.data = data;

        flightId.setText(data[0]);
        flightName.setText(data[1]);
        depDate.setText(data[2]);
        arrDate.setText(data[3]);
        depCountry.setText(data[4]);
        arrCountry.setText(data[5]);
        price.setText(data[6]);
        plane.setText(data[7]);

        //edit.setVisible(false);
    }

    public JPanel getContent() {
        //flightname.setText(data[1]);

        return content;
    }

    public FlightInfo() {
        data = new String[8];
        back.addActionListener(Controller.backButton());
        mainPanel.addMouseListener(Controller.backMouseButton());
        for(Component component : mainPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton());
    }
}
