package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaneLayout {
    private JTable table;
    private JButton cancel;
    private JPanel content;
    private JButton back;
    private JButton buy;
    public JPanel buttonPanel;


    public void refresh(int flightId) {
        int[][] data = Model.getAllFlyghtSeats(flightId);

        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(data.length, data[0].length));

        System.out.println(data.length);
        System.out.println(data[0].length);

        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[0].length; j++) {
                Button button = new Button((i + 1) + " " + (char)('A' + j));
                button.setPreferredSize(new Dimension(20, 10));
                button.setFocusable(false);

                if(data[i][j] != -1) {
                    button.setBackground(Color.RED);
                    button.setEnabled(false);
                } else {
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(button.getBackground() != Color.green)
                                button.setBackground(Color.green);
                            else button.setBackground(Color.white);
                        }
                    });
                }

                buttonPanel.add(button);
            }
        }
    }

    public PlaneLayout() {
        back.addActionListener(Controller.navigateFlightInfo());
        buy.addActionListener(Controller.selectSeats(this));
    }

    public JPanel getContent() {
        return content;
    }
}
