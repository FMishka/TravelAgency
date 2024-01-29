package frontend;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public abstract class View {
    static JFrame mainFrame;

    static final AuthForm authForm = new AuthForm();
    static final HomePage homePage = new HomePage();
    static final MyTicketsPage myTicketsPage = new MyTicketsPage();
    static final FlightsPage flightsPage = new FlightsPage();
    static final FlightInfo flightInfo = new FlightInfo();
    static final FlightEdit flightEdit = new FlightEdit();
    static final FlightAdd flightAdd = new FlightAdd();
    static final PlaneLayout planeLayout = new PlaneLayout();
    static final PaymentForm paymentForm = new PaymentForm();
    static final ReturnFlights returnFlights = new ReturnFlights();
    static final ReturnFlightInfo returnFlightInfo = new ReturnFlightInfo();
    static final ReturnPlaneLayout returnPlaneLayout = new ReturnPlaneLayout();
    static final TicketInfo ticketInfo = new TicketInfo();
    static final FilterForm filterForm = new FilterForm();
    static OrderTicket[] ticketsList;
    static int curTicketIndex = 0;

    static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static void setSize(int width, int height) {
        mainFrame.setSize(width, height);
        mainFrame.setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
    }

    public static void setAdminStatus(boolean isAdmin) {
        flightInfo.setAdminStatus(isAdmin);
        flightsPage.setAdminStatus(isAdmin);
    }

    public static void goToHomePage() {
        setSize(1000, 800);
        mainFrame.setContentPane(homePage.getContent());
        mainFrame.revalidate();
    }

    public static void goToMyTicketsPage() {
        myTicketsPage.refreshTable();
        setSize(1000, 800);
        mainFrame.setContentPane(myTicketsPage.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightsPage() {
        setSize(1000, 800);
        mainFrame.setContentPane(flightsPage.getContent());
        mainFrame.revalidate();
    }

    public static void goToAuthForm() {
        setSize(400, 350);
        mainFrame.setContentPane(authForm.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightInfo(String[] data) {
        setSize(600, 700);
        flightInfo.setData(data);
        mainFrame.setContentPane(flightInfo.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightInfo() {
        setSize(600, 700);
        mainFrame.setContentPane(flightInfo.getContent());
        mainFrame.revalidate();
    }

    public static void goToReturnFlightInfo(String[] data) {
        setSize(600, 700);
        returnFlightInfo.setData(data);
        mainFrame.setContentPane(returnFlightInfo.getContent());
        mainFrame.revalidate();
    }

    public static void goToReturnFlightInfo() {
        setSize(600, 700);
        mainFrame.setContentPane(returnFlightInfo.getContent());
        mainFrame.revalidate();
    }

    public static void goToTicketInfo() {
        setSize(600, 750);
        mainFrame.setContentPane(ticketInfo.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightEdit(String[] data) {
        setSize(600, 700);
        flightEdit.setData(data);
        mainFrame.setContentPane(flightEdit.getContent());
        mainFrame.revalidate();
    }

    public static void goToFlightAdd() {
        setSize(600, 700);
        mainFrame.setContentPane(flightAdd.getContent());
        mainFrame.revalidate();
    }

    public static void goToPlaneLayout(int flightId) {
        setSize(600, 700);
        planeLayout.refresh(flightId);
        mainFrame.setContentPane(planeLayout.getContent());
        mainFrame.revalidate();
    }

    public static void goToReturnPlaneLayout(int flightId) {
        setSize(600, 700);
        returnPlaneLayout.refresh(flightId);
        mainFrame.setContentPane(returnPlaneLayout.getContent());
        mainFrame.revalidate();
    }

    public static void goToFilterForm() {
        setSize(600, 700);
        mainFrame.setContentPane(filterForm.getContent());
        mainFrame.revalidate();
    }

    public static void goToOrderTicket(ArrayList<String> selectedSeats) {
        curTicketIndex = 0;

        setSize(600, 700);

        String flightName = flightInfo.getFlightName();
        int ticketsNum = selectedSeats.size();

        ticketsList = new OrderTicket[ticketsNum];

        for(int i = 0; i < ticketsNum; i++) {
            ticketsList[i] = new OrderTicket(flightName, selectedSeats.get(i), i, ticketsNum);
        }


        mainFrame.setContentPane(ticketsList[0].getContent());
        mainFrame.revalidate();
    }

    public static void goToReturnFlights() {
        setSize(1000, 800);
        mainFrame.setContentPane(returnFlights.getContent());
        mainFrame.revalidate();
    }

    public static void goToNextTicket() {
        curTicketIndex++;
        if(curTicketIndex == ticketsList.length) {
            int returnTicketsNumber = 0;
            for(OrderTicket orderTicket : ticketsList) {
                if(orderTicket.needReturnTicketCheckBox.isSelected()) returnTicketsNumber++;
            }

            if(returnTicketsNumber > 0) {
                returnFlights.refreshTable(flightInfo.getData()[4], flightInfo.getData()[5],
                        LocalDateTime.parse(flightInfo.getData()[3], DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")));

                if(returnFlights.tableIsEmpty) {
                    showMessageDialog(null, "There is no return flight!", "Error", JOptionPane.ERROR_MESSAGE);
                    curTicketIndex--;
                } else {
                    returnPlaneLayout.setAmountToChoose(returnTicketsNumber);
                    goToReturnFlights();
                }
            } else {
                paymentForm.refresh();
                setSize(600, 700);
                mainFrame.setContentPane(paymentForm.getContent());
                mainFrame.revalidate();
            }
        } else {
            setSize(600, 700);
            mainFrame.setContentPane(ticketsList[curTicketIndex].getContent());
            mainFrame.revalidate();
        }
    }

    public static void goToPreviousTicket() {
        if(curTicketIndex == 0) {
            setSize(600, 700);
            mainFrame.setContentPane(planeLayout.getContent());
            mainFrame.revalidate();
            return;
        }

        curTicketIndex--;
        setSize(600, 700);
        mainFrame.setContentPane(ticketsList[curTicketIndex].getContent());
        mainFrame.revalidate();
    }

    public static void goToPaymentForm() {
        setSize(600, 700);
        mainFrame.setContentPane(paymentForm.getContent());
        mainFrame.revalidate();
    }

    public static void init() {
        mainFrame = new JFrame("Travel Agency");
        mainFrame.setContentPane(authForm.getContent());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        mainFrame.setIconImage(new ImageIcon(Objects.requireNonNull(View.class.getResource("icons/planeIcon.png"))).getImage());
        setSize(400, 350);
    }

    public static void refreshFlightsPage() {
        flightsPage.refreshTable();
    }
}
