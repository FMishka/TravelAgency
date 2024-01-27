package frontend;

import backend.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public abstract class Controller {
    public static final int ADMIN = 1;
    public static final int USER = 0;

    public static final String[] countries = Model.getAllCountries();
    public static final String[] planes = Model.getAllPlaneNames();

    static int curFlightId;


    public static void authSubmit(AuthForm authForm) {
        switch (Model.validateCredentials(authForm.login.getText(), authForm.password.getText())) {
            case USER:
                showMessageDialog(null, "You have successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                View.setAdminStatus(false);
                View.goToHomePage();
                break;
            case ADMIN:
                showMessageDialog(null, "You have successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                View.setAdminStatus(true);
                View.goToFlightsPage();
                break;
            default:
                showMessageDialog(null, "Wrong login or password", "Error", JOptionPane.ERROR_MESSAGE);
                authForm.login.setText("");
                authForm.password.setText("");
        }
    }

    public static ActionListener authSubmitButton(AuthForm authForm) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authSubmit(authForm);
            }
        };
    }

    public static KeyListener authSubmitKey(AuthForm authForm) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == '\n') authSubmit(authForm);
            }
        };
    }

    public static ActionListener navigateHome() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToHomePage();
            }
        };
    }

    public static ActionListener navigateMyTickets() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToMyTicketsPage();
            }
        };
    }

    public static ActionListener navigateFlights() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToFlightsPage();
            }
        };
    }

    public static ActionListener navigateReturnFlights() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToReturnFlights();
            }
        };
    }

    public static ActionListener navigateFlightInfo() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToFlightInfo();
            }
        };
    }

    public static ActionListener navigateReturnFlightInfo() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToReturnFlightInfo();
            }
        };
    }

    public static ActionListener navigateFlightAdd() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToFlightAdd();
            }
        };
    }

    public static ActionListener navigateFlightEdit(FlightInfo flightInfo) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToFlightEdit(flightInfo.getData());
            }
        };
    }

    public static ActionListener navigatePlaneLayout(FlightInfo flightInfo) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToPlaneLayout(flightInfo.curFlightId);
            }
        };
    }

    public static ActionListener navigateReturnPlaneLayout(ReturnFlightInfo returnFlightInfo) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToReturnPlaneLayout(returnFlightInfo.curFlightId);
            }
        };
    }

    public static ActionListener logout() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToAuthForm();
            }
        };
    }


    public static MouseAdapter tableMouseClick(JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Point point = event.getPoint();
                int row = table.rowAtPoint(point);

                if (event.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    //System.out.println(table.getModel().getValueAt(row, 0).toString());

                    String[] data = new String[table.getModel().getColumnCount()];
                    for(int i = 0; i < table.getModel().getColumnCount(); i++) {
                        data[i] = table.getModel().getValueAt(row, i).toString();
                    }

                    curFlightId = Integer.parseInt(data[0]);
                    View.goToFlightInfo(data);
                }
            }
        };
    }

    public static MouseAdapter tableHeaderMouseClick(JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Point point = event.getPoint();
                int column = table.getTableHeader().columnAtPoint(point);

                System.out.println(column);
                FlightsPage flights = View.flightsPage;

                switch (column) {
                    case 0:
                        if(flights.getTableSortBy().equals("flightnameUP")) {
                            flights.setTableSortBy("flightnameDOWN");
                        } else flights.setTableSortBy("flightnameUP");
                        flights.refreshTable();
                        break;
                    case 1:
                        if(flights.getTableSortBy().equals("departuredateUP")) {
                            flights.setTableSortBy("departuredateDOWN");
                        } else flights.setTableSortBy("departuredateUP");
                        flights.refreshTable();
                        break;
                    case 2:
                        if(flights.getTableSortBy().equals("countriesDepartureUp")) {
                            flights.setTableSortBy("countriesDepartureDown");
                        } else flights.setTableSortBy("countriesDepartureUp");
                        flights.refreshTable();
                        break;
                    case 3:
                        if(flights.getTableSortBy().equals("arrivaldateUP")) {
                            flights.setTableSortBy("arrivaldateDOWN");
                        } else flights.setTableSortBy("arrivaldateUP");
                        flights.refreshTable();
                        break;
                    case 4:
                        if(flights.getTableSortBy().equals("countriesArrivalUp")) {
                            flights.setTableSortBy("countriesArrivalDown");
                        } else flights.setTableSortBy("countriesArrivalUp");
                        flights.refreshTable();
                        break;
                    case 5:
                        if(flights.getTableSortBy().equals("priceUP")) {
                            flights.setTableSortBy("priceDOWN");
                        } else flights.setTableSortBy("priceUP");
                        flights.refreshTable();
                        break;
                    case 6:
                        if(flights.getTableSortBy().equals("ticketsUp")) {
                            flights.setTableSortBy("ticketsDown");
                        } else flights.setTableSortBy("ticketsUp");
                        flights.refreshTable();
                        break;
                }
            }
        };
    }

    public static MouseAdapter returnTableMouseClick(JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Point point = event.getPoint();
                int row = table.rowAtPoint(point);

                if (event.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    String[] data = new String[table.getModel().getColumnCount()];

                    for(int i = 0; i < table.getModel().getColumnCount(); i++) {
                        data[i] = table.getModel().getValueAt(row, i).toString();
                    }


                    View.goToReturnFlightInfo(data);
                }
            }
        };
    }

    public static MouseAdapter ticketsTableMouseClick(JTable table) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Point point = event.getPoint();
                int row = table.rowAtPoint(point);

                if (event.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    View.ticketInfo.refresh(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
                    View.goToTicketInfo();
                }
            }
        };
    }

    public static ActionListener backButton() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToFlightsPage();
            }
        };
    }

    public static MouseAdapter backMouseButton() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == 4) {
                    View.goToFlightsPage();
                }
            }
        };
    }

    public static ActionListener deleteButton(FlightInfo flightInfo) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmDialog = JOptionPane.showConfirmDialog(null, "Delete flight (id=" + flightInfo.curFlightId + ")?", "Are you sure?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if(confirmDialog == JOptionPane.YES_OPTION) {
                    Model.deleteFlight(flightInfo.curFlightId);
                    View.refreshFlightsPage();
                    View.goToFlightsPage();
                }
            }
        };
    }

    public static ActionListener selectSeats(PlaneLayout planeLayout) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedSeats = new ArrayList<String>();
                for (Component component : planeLayout.buttonPanel.getComponents()) {
                    Button button = null;
                    if (component instanceof Button) {
                        button = (Button) component;

                        if (button.getBackground() == Color.green && button.isEnabled()) {
                            System.out.println(button.getLabel());
                            selectedSeats.add(button.getLabel());
                        }
                    }
                }

                if(selectedSeats.isEmpty()) {
                    showMessageDialog(null, "No seats selected", "Select seats", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                View.paymentForm.setTotalPrice(selectedSeats.size() * Integer.parseInt(View.flightInfo.getData()[6]));
                View.goToOrderTicket(selectedSeats);
            }
        };
    }

    public static ActionListener selectReturnSeats(ReturnPlaneLayout returnPlaneLayout) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedSeats = new ArrayList<String>();
                for (Component component : returnPlaneLayout.buttonPanel.getComponents()) {
                    Button button = null;
                    if (component instanceof Button) {
                        button = (Button) component;

                        if (button.getBackground() == Color.green && button.isEnabled()) {
                            selectedSeats.add(button.getLabel());
                        }
                    }
                }

                if(selectedSeats.isEmpty()) {
                    showMessageDialog(null, "No seats selected", "Select seats", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if(selectedSeats.size() != returnPlaneLayout.getAmountToChoose()) {
                    showMessageDialog(null, "Selected " + selectedSeats.size() + " seats, required " + returnPlaneLayout.getAmountToChoose(),
                            "Wrong amount of seats selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    View.paymentForm.setTotalPrice(View.ticketsList.length * Integer.parseInt(View.flightInfo.getData()[6]) +
                            selectedSeats.size() * Integer.parseInt(View.returnFlightInfo.getData()[6]));

                    View.paymentForm.setReturnSeats(selectedSeats);
                    View.goToPaymentForm();
                }
            }
        };
    }

    public static ActionListener nextTicket(OrderTicket orderTicket) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Model.isFlightNotGone(curFlightId);
                    Model.isNameCorrect(orderTicket.firstName.getText());
                    Model.isNameCorrect(orderTicket.secondName.getText());
                    Model.isBirthdayCorrect(LocalDate.parse(orderTicket.birthDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay());

                    try {
                        Model.isPassportCorrect(Integer.parseInt(orderTicket.passport.getText()));
                    } catch (Exception ex) {
                        throw new Exception("Passport entered incorrectly!");
                    }

                    View.goToNextTicket();
                } catch (Exception ex) {
                    showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    public static ActionListener previousTicket() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.goToPreviousTicket();
            }
        };
    }
}
