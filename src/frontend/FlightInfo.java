package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class FlightInfo {
    private JPanel content;
    private JButton back;
    private JTextField flightId;
    private JTextField flightName;
    private JTextField depDate;
    private JTextField depCountry;
    private JTextField arrDate;
    private JTextField arrCountry;
    private JTextField price;
    private JTextField plane;
    private JPanel mainPanel;
    private JButton edit;
    private JPanel bottomPanel;
    private JButton delete;
    private JPanel topPanel;
    private JButton buy;
    private JButton confirm;
    private JButton cancel;
    private final JTextField[] fieldsArray;

    private String[] data;
    public int curFlightId;

    public void setData(String[] data) {
        this.data = data;
        curFlightId = Integer.parseInt(data[0]);

//        flightId.setText(data[0]);
//        flightName.setText(data[1]);
//        depDate.setText(data[2]);
//        arrDate.setText(data[3]);
//        depCountry.setText(data[4]);
//        arrCountry.setText(data[5]);
//        price.setText(data[6]);
//        plane.setText(data[7]);

        for(int i = 0; i < fieldsArray.length; i++) {
            fieldsArray[i].setText(data[i]);
        }
    }

    public JPanel getContent() {
        //flightname.setText(data[1]);

        return content;
    }

    public FlightInfo() {
        fieldsArray = new JTextField[]{flightId, flightName, depDate, arrDate, depCountry, arrCountry, price, plane};
        cancel.setVisible(false);
        confirm.setVisible(false);
        data = new String[8];
        back.addActionListener(Controller.backButton());
        mainPanel.addMouseListener(Controller.backMouseButton(this));
        topPanel.addMouseListener(Controller.backMouseButton(this));
        bottomPanel.addMouseListener(Controller.backMouseButton(this));
        content.addMouseListener(Controller.backMouseButton(this));
        for(Component component : mainPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton(this));
        for(Component component : bottomPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton(this));
        for(Component component : topPanel.getComponents())
            component.addMouseListener(Controller.backMouseButton(this));

        edit.addActionListener(Controller.changeEditMode(this, true));
        cancel.addActionListener(Controller.changeEditMode(this, false));
        delete.addActionListener(Controller.deleteButton(this));
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int id = Integer.parseInt(flightId.getText());
//                String name = flightName.getName();
//                LocalDateTime departureTime = LocalDateTime.parse(depDate.getText().replace(' ', 'T'));
//                LocalDateTime arrivalTime = LocalDateTime.parse(arrDate.getText().replace(' ', 'T'));
//                int departureCountryId


                String date = depDate.getText();
                date = date.replace(' ', 'T');
                LocalDateTime localDateTime = LocalDateTime.parse(date);

                System.out.println(localDateTime);
            }
        });
    }

    public void updateAdminStatus(boolean isAdmin) {
        if(isAdmin) {
            delete.setVisible(true);
            edit.setVisible(true);
        } else {
            delete.setVisible(false);
            edit.setVisible(false);
        }
    }

    public void editMode(boolean editMode) {
        cancel.setVisible(editMode);
        confirm.setVisible(editMode);

        back.setVisible(!editMode);
        buy.setVisible(!editMode);
        edit.setVisible(!editMode);
        delete.setVisible(!editMode);

        for(int i = 0; i < fieldsArray.length; i++) {
            fieldsArray[i].setEditable(editMode);
            fieldsArray[i].setText(data[i]);
        }
    }
}
