package frontend;

import javax.swing.*;

public class ReturnFlightInfo {
    private JPanel mainPanel;
    private JTextField flightId;
    private JTextField flightName;
    private JTextField depCountry;
    private JTextField arrDate;
    private JTextField arrCountry;
    private JTextField price;
    private JTextField plane;
    private JTextField depDate;
    private JPanel topPanel;
    private JButton back;
    private JPanel bottomPanel;
    private JButton confirm;
    private JPanel content;

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

        for(int i = 0; i < fieldsArray.length; i++) {
            fieldsArray[i].setText(data[i]);
        }

        price.setText(price.getText() + " ₪" );
    }

    public ReturnFlightInfo() {
        fieldsArray = new JTextField[]{flightId, flightName, depDate, arrDate, depCountry, arrCountry, price, plane};

        data = new String[8];
        back.addActionListener(Controller.navigateReturnFlights());


        confirm.addActionListener(Controller.navigateReturnPlaneLayout(this));
    }
}
