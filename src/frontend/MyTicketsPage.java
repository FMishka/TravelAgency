package frontend;

import backend.Model;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MyTicketsPage {
    private View view;

    private JPanel content;
    private JButton homePageButton;
    private JButton myTicketsPageButton;
    private JButton flightsButton;

    private JPanel navigation;
    private JScrollPane scrollPane;
    private JTable table;

    public JPanel getContent() {
        return content;
    }

    public static void setTableRenderer(JTable table, DefaultTableCellRenderer renderer)
    {
        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
    }

    public void refreshTable()  {
        try {
            String[][] rowData = Model.getUsersAllTickets();

            for(String[] row : rowData) {
                row[3] += " " + row[2].charAt(0) + ".";
            }

            String[] columnNames = new String[]{"ticket_ID", "Flight", "firstName", "Passenger", "Departure", "Arrival", "From", "To"};
            table.setModel(new AbstractTableModel() {
                public String getColumnName(int column) {
                    return columnNames[column].toString();
                }

                public int getRowCount() {
                    return rowData.length;
                }

                public int getColumnCount() {
                    return columnNames.length;
                }

                public Object getValueAt(int row, int col) {
                    return rowData[row][col];
                }

                public boolean isCellEditable(int row, int column) {
                    return false;
                }

                public void setValueAt(String value, int row, int col) {
                    rowData[row][col] = value;
                    fireTableCellUpdated(row, col);
                }
            });

            TableColumnModel tcm = table.getColumnModel();
            tcm.removeColumn(tcm.getColumn(2));
            tcm.removeColumn(tcm.getColumn(0));
            table.moveColumn(3, 4);
            table.getColumnModel().getColumn(0).setMaxWidth(100);


            table.setRowHeight(25);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            setTableRenderer(table, centerRenderer);

            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setBackground(new Color(36, 36, 36));
            scrollPane.getViewport().setBackground(new Color(133, 220, 189));


            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(65, 179, 163));
            header.setForeground(new Color(0, 0, 0));
            header.setFont(new Font("Droid Sans", Font.BOLD, 16));
            header.setReorderingAllowed(false);
            header.setResizingAllowed(false);
            header.setPreferredSize(new Dimension(scrollPane.getWidth(), 50));
        } catch (Exception e) {
            return;
        }
    }

    public MyTicketsPage() {
        homePageButton.addActionListener(Controller.navigateHome());
        myTicketsPageButton.addActionListener(Controller.navigateMyTickets());
        flightsButton.addActionListener(Controller.navigateFlights());

        refreshTable();
    }
}