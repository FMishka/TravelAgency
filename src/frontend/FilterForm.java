package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.stream.Stream;

public class FilterForm {
    private JPanel mainPanel;
    private JFormattedTextField depDateFrom;
    private JPanel content;
    private JButton back;
    private JButton confirm;
    private JFormattedTextField depDateTo;
    private JComboBox<String> countryFrom;
    private JComboBox<String> countryTo;
    private JFormattedTextField priceFrom;
    private JFormattedTextField priceTo;

    public FilterForm() {
        Utility.setDateTimeOrNoneFormat(depDateFrom);
        Utility.setDateTimeOrNoneFormat(depDateTo);
        Utility.setPriceOrNoneFormat(priceFrom);
        Utility.setPriceOrNoneFormat(priceTo);

        ((JLabel)countryFrom.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        countryFrom.setModel(new DefaultComboBoxModel<String>(Stream.concat(Arrays.stream(new String[]{"-"}), Arrays.stream(Controller.countries)).toArray(String[]::new)));
        ((JLabel)countryTo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        countryTo.setModel(new DefaultComboBoxModel<String>(Stream.concat(Arrays.stream(new String[]{"-"}), Arrays.stream(Controller.countries)).toArray(String[]::new)));

        back.addActionListener(Controller.navigateFlights());
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);

                String[][] tableData = Model.filterFlights(
                        priceFrom.getText().equals("-") ? -1 : Integer.parseInt(priceFrom.getText()),
                        priceTo.getText().equals("-") ? -1 : Integer.parseInt(priceTo.getText()),
                        depDateFrom.getText().equals("-") ? null : LocalDateTime.parse(depDateFrom.getText(), dateTimeFormatter),
                        depDateTo.getText().equals("-") ? null : LocalDateTime.parse(depDateTo.getText(), dateTimeFormatter),
                        countryFrom.getSelectedItem().toString().equals("-") ? null : countryFrom.getSelectedItem().toString(),
                        countryTo.getSelectedItem().toString().equals("-") ? null : countryTo.getSelectedItem().toString()
                );

                View.flightsPage.refreshTable(tableData);
                View.goToFlightsPage();
            }
        });
    }

    public JPanel getContent() {
        return content;
    }
}
