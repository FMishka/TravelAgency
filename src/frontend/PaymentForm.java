package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;

public class PaymentForm {
    private JPanel mainPanel;
    private JTextField price;
    private JTextField creditCard;
    private JTextField expireDate;
    private JPasswordField cvv;
    private JPanel topPanel;
    private JButton back;
    private JButton confirmPayment;
    private JPanel bottomPanel;
    private JPanel content;
    private JCheckBox savePaymentData;
    private JTextField cardHolder;

    public PaymentForm() {
        cvv.setInputVerifier(new CvvInputVerifier());
        creditCard.setInputVerifier(new CreditCardNumberVerifier());
        expireDate.setInputVerifier(new ExpireDateInputVerifier());
        cardHolder.setInputVerifier(new CardholderInputVerifier());

        back.addActionListener(Controller.previousTicket());
        confirmPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Model.isCorrectCreditCardDetails(creditCard.getText(), cardHolder.getText(), expireDate.getText(), Integer.parseInt(cvv.getText()))) {
                        for(OrderTicket ticketForm : View.ticketsList) {
                            Model.orderingTicket(View.flightInfo.curFlightId, Model.userId,
                                    Integer.parseInt(ticketForm.seatName.getText().split(" ")[0]),
                                    ticketForm.seatName.getText().split(" ")[1].charAt(0) - 'A',
                                    true, ticketForm.firstName.getText(), ticketForm.secondName.getText(),
                                    LocalDate.parse(ticketForm.birthDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(),
                                    ticketForm.maleRadioButton.isSelected() ? 'M' : 'F', Integer.parseInt(ticketForm.passport.getText()));
                        }

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
