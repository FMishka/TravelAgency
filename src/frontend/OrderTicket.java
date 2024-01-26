package frontend;

import javax.swing.*;

public class OrderTicket {
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton back;
    private JPanel content;
    private JButton forward;
    JTextField ticketNumber;
    JTextField flightName;
    JTextField firstName;
    JFormattedTextField secondName;
    JFormattedTextField birthDate;
    JFormattedTextField passport;
    JTextField seatName;
    JRadioButton maleRadioButton;
    JRadioButton femaleRadioButton;
    public OrderTicket(String flight, String seat, int curNum, int totalNum) {
        ticketNumber.setText("Ticket " + (curNum + 1) + "/" + totalNum);

        flightName.setText(flight);
        seatName.setText(seat);

        Utility.setDateFormat(birthDate, Utility.DEFAULT_DATETIME);
        Utility.setPassportFormat(passport);
        Utility.setNameFormat(firstName);
        Utility.setNameFormat(secondName);

        maleRadioButton.setSelected(true);

        forward.addActionListener(Controller.nextTicket(this));
        back.addActionListener(Controller.previousTicket());
    }

    public JPanel getContent() {
        return content;
    }
}
