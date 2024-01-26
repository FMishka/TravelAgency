package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnPlaneLayout {
    private JPanel content;
    private JButton buy;
    private JButton back;
    public JPanel buttonPanel;
    private int amountToChoose;

    public void setAmountToChoose(int amountToChoose) {
        this.amountToChoose = amountToChoose;
    }

    public int getAmountToChoose() {
        return amountToChoose;
    }

    public void refresh(int flightId) {
        int[][] data = Model.getAllFlyghtSeats(flightId);

        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(data.length, data[0].length));

        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[0].length; j++) {
                Button button = new Button((i + 1) + " " + (char)('A' + j));
                button.setPreferredSize(new Dimension(20, 10));
                button.setFocusable(false);

                if(data[i][j] == 0) {
                    button.setBackground(Color.RED);
                    button.setEnabled(false);
                } else if(data[i][j] == -1) {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(button.getBackground() != Color.green)
                                button.setBackground(Color.green);
                            else button.setBackground(Color.white);
                        }
                    });
                } else if(data[i][j] == 1) {
                    button.setBackground(Color.GREEN);
                    button.setEnabled(false);
                }

                buttonPanel.add(button);
            }
        }
    }

    public ReturnPlaneLayout() {
        back.addActionListener(Controller.navigateReturnFlightInfo());
        buy.addActionListener(Controller.selectReturnSeats(this));
    }

    public JPanel getContent() {
        return content;
    }
}
