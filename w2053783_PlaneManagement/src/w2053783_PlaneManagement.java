import java.util.*;
//Main class for managing plan operations.
public class w2053783_PlaneManagement{
    // Arrays to represent seat availability in each row.
    static int[] seatLineA = new int[14];
    static int[] seatLineB = new int[12];
    static int[] seatLineC = new int[12];
    static int[] seatLineD = new int[14];
    // Array to store ticket objects.
    static Ticket[] tickets = new Ticket[52];
    //Main method that doing all the operations.
    public static void main(String[] args){
        System.out.println("Welcome to the Plane Management application");
        //Menu option loop.
        while (true) {
            boolean valid = false;
            //Display menu options to user.
            System.out.println("""
             **************************************************
             *                  MENU OPTIONS                  *
             **************************************************
                   1) Buy a seat
                   2) Cancel a seat
                   3) Find first available seat
                   4) Show seating plan
                   5) Print tickets information and total sales
                   6) Search ticket
                   0) Quit
             **************************************************""");
            //Validate the Input. Loop until correct input.
            while(!valid){
                try{
                    //Prompt user to select an option.
                    System.out.print("Please Select an option: ");
                    Scanner input = new Scanner(System.in);
                    int menu_option = input.nextInt();
                    //Perform operations base on user input.
                    switch (menu_option) {
                        case 1:
                            buy_seat();
                            valid = true;
                            break;
                        case 2:
                            cancel_seat();
                            valid = true;
                            break;
                        case 3:
                            find_first_available();
                            valid = true;
                            break;
                        case 4:
                            show_seating_plan();
                            valid = true;
                            break;
                        case 5:
                            print_ticket_info();
                            valid = true;
                            break;
                        case 6:
                            search_ticket();
                            valid = true;
                            break;
                        case 0:
                            System.out.println("Program terminated.");
                            input.close();
                            return;
                        default:
                            //Display if user enter number which not in menu Option.
                            System.out.println("Invalid Option");
                    }
                } catch (Exception e) {
                    //Display when user input incorrect option. Like letters and symbols.
                    System.out.println("Error: Enter Integer Option.");
                }
            }
        }
    }
    //Method to buy a seat.
    public static void buy_seat(){
        System.out.println("___Buy a Seat___");
        //validate the user input.
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter row letter: ");
            String row_letter = scan.next().toUpperCase();
            System.out.print("Enter seat number: ");
            int seat_number = scan.nextInt();
            //Check that user input the correct row and seat number.
            if ((row_letter.equals("A") || row_letter.equals("D")) && seat_number <= 14) {
                //Check the row letter and seat number is available.
                if (row_letter.equals("A") && seatLineA[seat_number - 1] == 0) {
                    System.out.println("Seat Available.");
                    //mark seat as sold.
                    seatLineA[seat_number - 1] = 1;
                    //Call the details method that ask personal information.
                    details(row_letter, seat_number);
                    System.out.println("Successfully brought the seat!!!");
                    break;
                }
                else if (row_letter.equals("D") && seatLineD[seat_number - 1] == 0) {
                    System.out.println("Seat Available.");
                    seatLineD[seat_number - 1] = 1;
                    details(row_letter, seat_number);
                    System.out.println("Successfully brought the seat!!!");
                    break;
                }
                else {
                    System.out.println("Seat Not available.");
                    break;
                }
            }
            else if ((row_letter.equals("B") || row_letter.equals("C")) && seat_number <= 12) {
                if (row_letter.equals("B") && seatLineB[seat_number - 1] == 0) {
                    System.out.println("Seat Available.");
                    seatLineB[seat_number - 1] = 1;
                    details(row_letter, seat_number);
                    System.out.println("Successfully brought the seat!!!");
                    break;
                }
                else if (row_letter.equals("C") && seatLineC[seat_number - 1] == 0) {
                    System.out.println("Seat Available.");
                    seatLineC[seat_number - 1] = 1;
                    details(row_letter, seat_number);
                    System.out.println("Successfully brought the seat!!!");
                    break;
                }
                else {
                    System.out.println("Seat Not available.");
                    break;
                }
            }
            else {
                //Displays if  user enter incorrect row or seat.
                System.out.println(" Not valid Seat. ");
            }
        }
    }
    //Method to get personal information and Save the ticket.
    public static void details(String row, int seat){
        Scanner scan3 = new Scanner(System.in);
        System.out.println("---Enter Your Personal Info---");
        System.out.print("Name: ");
        String p_name = scan3.nextLine();
        System.out.print("Surname: ");
        String p_surname = scan3.nextLine();
        System.out.print("E-mail: ");
        String p_email = scan3.nextLine();

        Person person = new Person(p_name,p_surname,p_email);
        //method calling for add ticket in the array.
        addTickets(person,row,seat);
    }
    //Method to determine ticket prices.
    public static int prices(String row, int seat){
        int price = 0;
        if (seat>=1 && seat<=5){
            price = 200;
        }
        else if (seat>=6 && seat<=9){
            price = 150;
        }
        else if(row.equals("A")||row.equals("D") && (seat>=10 && seat <= 14)){
            price = 180;
        }
        else if(row.equals("B")||row.equals("C") && (seat>=10 && seat <= 12)){
            price = 180;
        }
        return price;
    }
    //Method to add purchased tickets to the array and save the ticket information in text file.
    public static void addTickets(Person person, String row, int seat){
        Ticket ticket = new Ticket();
        ticket.setRow(row);
        ticket.setSeat(seat);
        ticket.setPrice(prices(row, seat));
        ticket.setPerson(person);
        //loop that check the ticket array.
        for (int i = 0; i<tickets.length-1; i++){
            if(tickets[i] == null){
                tickets[i] = ticket;
                //call the method from Ticket to save the file.
                ticket.save();
                break;
            }
        }
    }
    //Method to cancel a seat.
    public static void cancel_seat(){
        System.out.println("___Cancel a Seat___");
        //validate part same as buy_seat method.
        while(true) {
            Scanner scan2 = new Scanner(System.in);
            System.out.print("Enter row letter: ");
            String row_letter = scan2.next().toUpperCase();
            System.out.print("Enter seat number: ");
            int seat_number = scan2.nextInt();

            if ((row_letter.equals("A")|| row_letter.equals("D")) && seat_number<=14){
                if(row_letter.equals("A") && seatLineA[seat_number-1]==1) {
                    //Mark sold seat as available.
                    seatLineA[seat_number - 1] = 0;
                    //Method calling to remove ticket form array.
                    cancelTicket(row_letter,seat_number);
                    System.out.println("Successfully cancelled the seat!!!");
                    break;
                }
                else if(row_letter.equals("D") && seatLineD[seat_number-1]==1){
                    seatLineD[seat_number-1] = 0;
                    cancelTicket(row_letter,seat_number);
                    System.out.println("Successfully cancelled the seat!!!");
                    break;
                }
                else{
                    System.out.println("Seat Available.");
                    break;
                }
            }
            else if ((row_letter.equals("B")|| row_letter.equals("C")) && seat_number<=12) {
                if (row_letter.equals("B") && seatLineB[seat_number - 1] == 1) {
                    seatLineB[seat_number - 1] = 0;
                    cancelTicket(row_letter,seat_number);
                    System.out.println("Successfully cancelled the seat!!!");
                    break;
                }
                else if (row_letter.equals("C") && seatLineC[seat_number - 1] == 1) {
                    seatLineC[seat_number - 1] = 0;
                    cancelTicket(row_letter,seat_number);
                    System.out.println("Successfully cancelled the seat!!!");
                    break;
                }
                else{
                    System.out.println("Seat Available.");
                    break;
                }
            }
            else{
                System.out.println("Not Valid Seat.");
            }
        }
    }
    //Method to remove ticket from the array.
    public static void cancelTicket(String row, int seat){
        for (int i = 0; i<tickets.length-1; i++){
            if (tickets[i] != null){
                if(tickets[i].getRow().equals(row) && tickets[i].getSeat() == seat){
                    tickets[i] = null;
                    break;
                }
            }
        }
    }
    //Method to find the first available seat.
    public static void find_first_available(){
        boolean done = true;
        System.out.println("___First Available Seat___");
        //Check each row array which index is zero.
        for(int i = 0; i<seatLineA.length;i++){
            if(seatLineA[i]==0){
                System.out.println("Seat Number A"+(i+1));
                //make this false not access to next.
                done = false;
                break;
            }
        }
        for (int i = 0; i < seatLineB.length; i++) {
            if (seatLineB[i] == 0 && done) {
                System.out.println("Seat Number B" + (i + 1));
                done = false;
                break;
            }
        }
        for (int i = 0; i < seatLineC.length; i++) {
            if (seatLineC[i] == 0 && done) {
                System.out.println("Seat Number C" + (i + 1));
                done = false;
                break;
            }
        }
        for(int i = 0; i<seatLineD.length;i++){
            if(seatLineD[i]==0 && done){
                System.out.println("Seat Number D"+(i+1));
                break;
            }
        }
    }
    //Method to show the seat plan.
    public static void show_seating_plan(){
        System.out.println("___Seating Plan___");
        //check each rows with enhanced for loop and if seat is 0 print it as O.
        for(int i : seatLineA){
            if (i==0){
                System.out.print("O ");
            }else{
                System.out.print("X ");
            }
        }
        System.out.println();
        for(int i : seatLineB){
            if (i==0){
                System.out.print("O ");
            }else{
                System.out.print("X ");
            }
        }
        System.out.println();
        for(int i : seatLineC){
            if (i==0){
                System.out.print("O ");
            }else{
                System.out.print("X ");
            }
        }
        System.out.println();
        for(int i : seatLineD){
            if (i==0){
                System.out.print("O ");
            }else{
                System.out.print("X ");
            }
        }
        System.out.println();
    }
    //Method to print the tickets and get the total sales.
    public static void print_ticket_info(){
        int total = 0;
        //use for loop to get index of row arrays and get the total prices.
        for(int i = 0 ; i <= 4; i++){
            if(seatLineA[i]==1 || seatLineB[i]==1 || seatLineC[i]==1 || seatLineD[i]==1){
                total = total + 200;
            }
        }
        for(int i = 5 ; i <= 8; i++){
            if(seatLineA[i]==1 || seatLineB[i]==1 || seatLineC[i]==1 || seatLineD[i]==1){
                total = total + 150;
            }
        }
        for(int i = 9 ; i <= 11; i++){
            if(seatLineA[i]==1 || seatLineB[i]==1 || seatLineC[i]==1 || seatLineD[i]==1){
                total = total + 180;
            }
        }
        for(int i = 12 ; i <= 13; i++){
            if(seatLineA[i]==1 || seatLineD[i]==1){
                total = total + 180;
            }
        }
        //print the tickets' information.
        System.out.println("___Ticket Information and Total Sales___");

        for (int i = 0; i < tickets.length ; i++) {
            try {
                System.out.println("--" + tickets[i].getRow() + tickets[i].getSeat() + "--");
                tickets[i].ticketInfo();
            }catch (Exception ignored){
            }
        }
        System.out.println("Total Price is: £"+total);
    }
    //Method to search ticket.
    public static void search_ticket() {
        System.out.println("___Search ticket___");
        //loop use to validate like in buy seat method and cancel seat.
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter row letter: ");
            String row_letter = scanner.next().toUpperCase();
            System.out.print("Enter seat number: ");
            int seat_number = scanner.nextInt();

            if ((row_letter.equals("A") || row_letter.equals("D")) && seat_number <= 14) {
                //Check the tickets array and print the ticket information.
                for (int i = 0; i < tickets.length - 1; i++) {
                    if (tickets[i] != null) {
                        if (tickets[i].getRow().equals(row_letter) && tickets[i].getSeat() == seat_number) {
                            System.out.println("Seat Not Available!!!");
                            System.out.println("--Ticket Info--");
                            System.out.println("Row: " + tickets[i].getRow());
                            System.out.println("Seat: " + tickets[i].getSeat());
                            System.out.println("Price: £" + tickets[i].getPrice());
                            System.out.println("Name: " + tickets[i].getPerson().getName() + " " + tickets[i].getPerson().getSurname());
                            System.out.println("Email: " + tickets[i].getPerson().getEmail());
                            return;
                        }
                    }
                }
                System.out.println("Seat Available");
                break;
            }
            else if ((row_letter.equals("B") || row_letter.equals("C")) && seat_number <= 12) {
                for (int i = 0; i < tickets.length - 1; i++) {
                    if (tickets[i] != null) {
                        if (tickets[i].getRow().equals(row_letter) && tickets[i].getSeat() == seat_number) {
                            System.out.println("Seat Not Available!!!");
                            System.out.println("--Ticket Info--");
                            System.out.println("Row: " + tickets[i].getRow());
                            System.out.println("Seat: " + tickets[i].getSeat());
                            System.out.println("Price: £" + tickets[i].getPrice());
                            System.out.println("Name: " + tickets[i].getPerson().getName() + " " + tickets[i].getPerson().getSurname());
                            System.out.println("Email: " + tickets[i].getPerson().getEmail());
                            return;
                        }
                    }
                }
                System.out.println("Seat Available");
                break;
            }
            else {
                System.out.println("Not a valid ticket.");
            }
        }
    }
}