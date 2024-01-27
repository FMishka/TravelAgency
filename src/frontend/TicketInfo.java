package frontend;

import backend.Model;

import javax.swing.*;
import java.util.Arrays;

public class TicketInfo {
    private JPanel mainPanel;
    private JTextField ticketId;
    private JTextField flightName;
    private JTextField secondName;
    private JTextField arrDate;
    private JTextField arrCountry;
    private JTextField price;
    private JTextField plane;
    private JTextField firstName;
    private JPanel topPanel;
    private JButton back;
    private JPanel content;

    public TicketInfo() {
        back.addActionListener(Controller.navigateMyTickets());
    }

    public void refresh(int id) {
        String[] data = new String[0];
        try {
            data = Model.getMoreInfoAboutTicket(id);
            System.out.println(Arrays.toString(data));
        } catch (Exception ignored) {}

        ticketId.setText(data[0]);
        flightName.setText(data[1]);
        firstName.setText(data[2]);
        secondName.setText(data[3]);

    }

    public JPanel getContent() {
        return content;
    }
}
