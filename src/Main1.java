import ir.maktabsharif.enums.EventStatus;
import ir.maktabsharif.enums.ReservationStatus;
import ir.maktabsharif.model.Event;
import ir.maktabsharif.model.Reservation;
import ir.maktabsharif.service.EventService;
import ir.maktabsharif.service.ReservationService;

import java.time.LocalDate;
import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventService eventService = new EventService();
        ReservationService reservationService = new ReservationService();
        while (true) {
            menu();
            int operator = scanner.nextInt();
            switch (operator) {
                case 1:
                    System.out.println("Enter Title");
                    String title = scanner.next();
                    System.out.println("Enter Location");
                    String location = scanner.next();
                    System.out.println("Enter Capacity");
                    Integer capacity = scanner.nextInt();
                    System.out.println("Enter Ticket Price : $");
                    Double ticketPrice = scanner.nextDouble();
                    Event event = new Event(title, location, capacity, 0, ticketPrice, EventStatus.ACTIVE);
                    eventService.create(event);
                    break;
                case 2:
                    eventService.showAll();
                    break;
                case 3:
                    System.out.println("Enter Event ID");
                    Long updateId = scanner.nextLong();
                    System.out.println("Enter New Title");
                    String newTitle = scanner.next();
                    System.out.println("Enter New Location");
                    String newLocation = scanner.next();
                    System.out.println("Enter New Capacity");
                    Integer newCapacity = scanner.nextInt();
                    System.out.println("Enter New Ticket Price : $");
                    Double newTicketPrice = scanner.nextDouble();
                    Event newEvent = new Event(newTitle, newLocation, newCapacity, 0, newTicketPrice, EventStatus.ACTIVE);
                    eventService.update(newEvent, updateId);
                    break;
                case 4:
                    System.out.println("Enter Event Id");
                    eventService.cancel(scanner.nextLong());
                    break;
                case 5:
                    System.out.println("Enter Customer Name");
                    String customerName = scanner.next();
                    System.out.println("Enter Customer Phone");
                    String customerPhone = scanner.next();
                    System.out.println("Enter Event ID");
                    Long eventId = scanner.nextLong();
                    System.out.println("Enter Ticket Count");
                    Integer ticketCount = scanner.nextInt();
                    Reservation reservation = new Reservation(customerName, customerPhone, eventId, ticketCount, LocalDate.now(), ReservationStatus.ACTIVE);
                    reservationService.create(reservation);
                    break;
                case 6:
                    System.out.println("Enter Reservation ID");
                    reservationService.cancel(scanner.nextLong());
                    break;
                case 7:
                    reservationService.showAll();
                    break;
                case 8:
                    System.out.println("*** Reservation List ***");
                    List<Reservation> reservationList = reservationService.showAll();
                    System.out.println();
                    System.out.println("*** Event List ***");
                    List<Event> eventList = eventService.showAll();
                    Long activeEvents = eventList.stream().filter(event1 -> event1.getStatus() == EventStatus.ACTIVE).count();
                    Optional<Event> mostExpensiveEvent = eventList.stream().max(Comparator.comparingDouble(Event::getTicketPrice));
                    OptionalDouble averageTicketPrices = eventList.stream().mapToDouble(Event::getTicketPrice).average();
                    List<Reservation> activeReservationList = reservationList.stream().filter(reservation1 -> reservation1.getStatus() == ReservationStatus.ACTIVE).toList();
                    List<Event> fullyBookedEvent = eventList.stream().filter(event1 -> event1.getCapacity() == event1.getReservedCount()).toList();
                    System.out.println();
                    System.out.println("Number Of Active Events: " +activeEvents);
                    System.out.println();
                    System.out.println("Most Expensive Event: "+mostExpensiveEvent);
                    System.out.println();
                    System.out.println("Average Ticket Prices: "+averageTicketPrices);
                    System.out.println();
                    System.out.println("Active Reservation List: "+activeReservationList);
                    System.out.println();
                    System.out.println("Fully Booked Events: "+fullyBookedEvent );
                    break;
                case 9:
                    System.out.println("*** Have A Nice Day ***");
                    return;
                default:
                    System.out.println("You Entered Wrong Number!!!");
                    return;
            }
        }

    }

    public static void menu() {
        System.out.println("---MENU---");
        System.out.println("1. Create Event");
        System.out.println("2. Show All Event");
        System.out.println("3. Update Event");
        System.out.println("4. Cancel Event");
        System.out.println("5. Create Reservation");
        System.out.println("6. Cancel Reservation");
        System.out.println("7. Show All Reservations");
        System.out.println("8. Reports");
        System.out.println("9. Exit");
        System.out.println("ENTER YOUR NUMBER...");
    }
}
