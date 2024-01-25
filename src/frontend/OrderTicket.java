package frontend;

import frontend.inputVerifiers.DateInputVerifier;

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
    JTextField secondName;
    JTextField birthDate;
    JTextField passport;
    JTextField seatName;
    JRadioButton maleRadioButton;
    JRadioButton femaleRadioButton;
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
