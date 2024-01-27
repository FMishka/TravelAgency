package frontend;

import backend.Model;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightsPage {
    private JPanel content;
    private JTable table;
    private JPanel navigation;
    private JButton homePageButton;
    private JButton myTicketsPageButton;
    private JButton flightsButton;
    private JScrollPane scrollPane;
    private JButton addFlightButton;
    private JButton refresh;
    private String tableSortBy = "";

    public void setTableSortBy(String filter) {
        tableSortBy = filter;
    }

    public String getTableSortBy() {
        return tableSortBy;
    }

    public static void setTableRenderer(JTable table, DefaultTableCellRenderer renderer)
    {
        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
    }

    public void refreshTable() {
        String[][] rowData;
        if(tableSortBy.isEmpty()) {
            rowData = Model.getAllFlights();
        } else {
            rowData = Model.sortFlightsBy(tableSortBy);
        }

        String[] columnNames = new String[]{"flight_ID", "flightname", "departureDate", "arrivalDate", "departureCountry", "arrivalCountry", "price", "fk_plane_ID"};
        table.setModel(new AbstractTableModel() {
            public String getColumnName(int column) { return columnNames[column].toString(); }
            public int getRowCount() { return rowData.length; }
            public int getColumnCount() { return columnNames.length; }
            public Object getValueAt(int row, int col) { return rowData[row][col]; }
            public boolean isCellEditable(int row, int column) { return false; }
            public void setValueAt(String value, int row, int col) {
                rowData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        });

        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(tcm.getColumn(7));
        tcm.removeColumn(tcm.getColumn(6));
        tcm.removeColumn(tcm.getColumn(0));
        table.moveColumn(2, 3);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        table.getColumnModel().getColumn(0).setPreferredWidth(100);
//        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.setRowHeight(25);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
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
    }

    public FlightsPage() {
        homePageButton.addActionListener(Controller.navigateHome());
        myTicketsPageButton.addActionListener(Controller.navigateMyTickets());
        flightsButton.addActionListener(Controller.navigateFlights());
        addFlightButton.addActionListener(Controller.navigateFlightAdd());

        table.addMouseListener(Controller.tableMouseClick(table));
        table.getTableHeader().addMouseListener(Controller.tableHeaderMouseClick(table));
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableSortBy = "";
                refreshTable();
            }
        });

        refreshTable();
    }

    public JPanel getContent() {
        return content;
    }

    public void setAdminStatus(boolean isAdmin) {
        addFlightButton.setVisible(isAdmin);
    }
}
