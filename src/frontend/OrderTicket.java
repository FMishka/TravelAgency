package frontend;

import javax.swing.*;

public class OrderTicket {
    private JPanel mainPanel;
    private JTextField flightName;
    private JTextField firstName;
    private JTextField secondName;
    private JTextField birthDate;
    private JTextField passport;
    private JTextField seatName;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton back;
    private JPanel content;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JButton forward;
    private JTextField ticketNumber;

    public OrderTicket(String flight, String seat, int curNum, int totalNum) {
        ticketNumber.setText("Ticket " + (curNum + 1) + "/" + totalNum);

        flightName.setText(flight);
        seatName.setText(seat);

        birthDate.setInputVerifier(new DateInputVerifier());
        birthDate.setText("2000-01-01");

        forward.addActionListener(Controller.nextTicket());
        back.addActionListener(Controller.previousTicket());
    }

    public JPanel getContent() {
        return content;
    }
}
