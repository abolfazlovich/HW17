import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            menu();
            int operator = scanner.nextInt();
            switch (operator){
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

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
    public static void menu(){
        System.out.println("---MENU---");
        System.out.println("1. Create Event");
        System.out.println("2. Show All Event");
        System.out.println("3. Update Event");
        System.out.println("4. Cancel Event");
        System.out.println("5. Create Reservation");
        System.out.println("5. Cancel Reservation");
        System.out.println("7. Show All Reservations");
        System.out.println("8. Reports");
        System.out.println("9. Exit");
        System.out.println("ENTER YOUR NUMBER...");
    }
}
