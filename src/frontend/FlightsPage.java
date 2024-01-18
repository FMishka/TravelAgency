package frontend;

import backend.Model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlightsPage {
    private JPanel content;
    private JTable table;
    private JPanel navigation;
    private JButton homePageButton;
    private JButton anotherPageButton;
    private JButton flightsButton;

    public static void setTableRenderer(JTable table, DefaultTableCellRenderer renderer)
    {
        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
    }

    public FlightsPage() {
        homePageButton.addActionListener(Controller.navigateHome());
        anotherPageButton.addActionListener(Controller.navigateAnother());
        flightsButton.addActionListener(Controller.navigateFlights());

        // table = new JTable(Model.getAllFlights(),
        // new String[]{"flight_ID", "flightname", "departureDate", "arrivalDate", "departureCountry_ID", "arrivalCountry_ID", "price", "fk_plane_ID"});

        String[][] rowData = Model.getAllFlights();
        String[] columnNames = new String[]{"flight_ID", "flightname", "departureDate", "arrivalDate", "departureCountry_ID", "arrivalCountry_ID", "price", "fk_plane_ID"};
        table.setModel(new AbstractTableModel() {
            public String getColumnName(int column) { return columnNames[column].toString(); }
            public int getRowCount() { return rowData.length; }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) { return rowData[row][col]; }
            public boolean isCellEditable(int row, int column) { return true; }
            public void setValueAt(String value, int row, int col) {
                rowData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        setTableRenderer(table, centerRenderer);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Point point = event.getPoint();
                int row = table.rowAtPoint(point);

                System.out.println(row);
            }
        });
    }

    public JPanel getContent() {
        return content;
    }
}
