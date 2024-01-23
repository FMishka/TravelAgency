package frontend;

import javax.swing.*;

public class OrderTicket {
    private JPanel mainPanel;
    private JTextField flightName;
    JTextField firstName;
    JTextField secondName;
    JTextField birthDate;
    JTextField passport;
    private JTextField seatName;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton back;
    private JPanel content;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton forward;
    private JTextField ticketNumber;

    public OrderTicket(String flight, String seat, int curNum, int totalNum) {
        ticketNumber.setText("Ticket " + (curNum + 1) + "/" + totalNum);

        flightName.setText(flight);
        seatName.setText(seat);

        birthDate.setInputVerifier(new DateInputVerifier());
        birthDate.setText("2000-01-01");

        maleRadioButton.setSelected(true);

        forward.addActionListener(Controller.nextTicket(this));
        back.addActionListener(Controller.previousTicket());
    }

    public JPanel getContent() {
        return content;
    }
}
