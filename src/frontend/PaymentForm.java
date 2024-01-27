package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class PaymentForm {
    private JPanel mainPanel;
    private JTextField price;
    private JFormattedTextField creditCard;
    private JFormattedTextField expireDate;
    private JPasswordField cvv;
    private JPanel topPanel;
    private JButton back;
    private JButton confirmPayment;
    private JPanel bottomPanel;
    private JPanel content;
    private JCheckBox savePaymentData;
    private JTextField cardHolder;
    private ArrayList<String> returnSeats;

    public void setReturnSeats(ArrayList<String> returnSeats) {
        this.returnSeats = returnSeats;
    }

    public void refresh() {
        String[] paymentData = new String[0];
        try {
            paymentData = Model.getUserPaymentData(Model.userId);
            System.out.println(Arrays.toString(paymentData));
        } catch (SQLException e) {
        }

        creditCard.setText(paymentData[1]);
        expireDate.setText(paymentData[2]);
        cardHolder.setText(paymentData[3]);
        cvv.setText(paymentData[4]);
    }

    public PaymentForm() {
        Utility.setCreditCardFormat(creditCard);
        Utility.setCardholderFormat(cardHolder);
        Utility.setExpireDateFormat(expireDate);
        Utility.setCvvFormat(cvv);

        back.addActionListener(Controller.previousTicket());
        confirmPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String creditCardValue = creditCard.getText().substring(0, 4) + creditCard.getText().substring(5, 9)
                            + creditCard.getText().substring(10, 14) + creditCard.getText().substring(15, 19);

                    if(Model.isCorrectCreditCardDetails(creditCardValue, cardHolder.getText(), expireDate.getText(), Integer.parseInt(cvv.getText()))) {
                        for(OrderTicket ticketForm : View.ticketsList) {
                            Model.orderingTicket(View.flightInfo.curFlightId, Model.userId,
                                    Integer.parseInt(ticketForm.seatName.getText().split(" ")[0]),
                                    ticketForm.seatName.getText().split(" ")[1].charAt(0) - 'A' + 1,
                                    true, ticketForm.firstName.getText(), ticketForm.secondName.getText(),
                                    LocalDate.parse(ticketForm.birthDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(),
                                    ticketForm.maleRadioButton.isSelected() ? 'M' : 'F', Integer.parseInt(ticketForm.passport.getText()));
                        }


                        int returnTicketIndex = 0;
                        for(OrderTicket ticketForm : View.ticketsList) {


                            if(ticketForm.needReturnTicketCheckBox.isSelected()) {
                                String seat = returnSeats.get(returnTicketIndex);
                                returnTicketIndex++;

                                int seatRow = Integer.parseInt(seat.split(" ")[0]);
                                int seatColumn = seat.split(" ")[1].charAt(0) - 'A' + 1;

                                Model.orderingTicket(View.returnFlightInfo.curFlightId, Model.userId,
                                        seatRow,
                                        seatColumn,
                                        true, ticketForm.firstName.getText(), ticketForm.secondName.getText(),
                                        LocalDate.parse(ticketForm.birthDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(),
                                        ticketForm.maleRadioButton.isSelected() ? 'M' : 'F', Integer.parseInt(ticketForm.passport.getText()));
                            }
                        }


                        if(savePaymentData.isSelected())
                            Model.addPaymentData(Model.userId, creditCardValue, expireDate.getText(), cardHolder.getText(), cvv.getText());

                        View.goToFlightsPage();
                    } else throw new Exception("Invalid credit card details");
                } catch (Exception ex) {
                    showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }

    public void setTotalPrice(int totalPrice) {
        price.setText(String.valueOf(totalPrice) + " ₪" );
    }

    public JPanel getContent() {
        return content;
    }
}
