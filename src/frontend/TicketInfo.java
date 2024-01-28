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
    private JTextField depDate;
    private JTextField depCountry;
    private JTextField firstName;
    private JButton back;
    private JPanel content;
    private JTextField seat;
    private JTextField passport;
    private JTextField price;

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
        depDate.setText(data[4]);
        arrDate.setText(data[5]);
        depCountry.setText(data[6]);
        arrCountry.setText(data[7]);
        seat.setText(data[8] + " " + (char)('A' + Integer.parseInt(data[9])));
        passport.setText(data[10]);
        price.setText(data[11]);
    }

    public JPanel getContent() {
        return content;
    }
}
