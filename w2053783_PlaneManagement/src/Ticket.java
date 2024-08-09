import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//Class representing a ticket.
public class Ticket {
    //Attributes used in ticket class.
    private String row;
    private int seat;
    private int price;
    private Person Person;
    //Constructor
    public Ticket() {
    }
    //Getter and setter methods to row, seat, prices, and person.
    public String getRow() {
        return row;
    }
    public void setRow(String row) {
        this.row = row;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Person getPerson() {
        return Person;
    }
    public void setPerson(Person person) {
        Person = person;
    }
    //Method use to print information of ticket. Call in PlaneManagement class.
    public void ticketInfo(){
        System.out.println("Row: "+row);
        System.out.println("Seat: "+seat);
        System.out.println("Price: £"+price);
        Person.printPerson();
    }
    //Method to create the text file in PlaneManagement class.
    public void save(){
        //Error handling for creating file.
        try{
            //Path to save the file.
            String fileName = "tickets/"+ row + seat +".txt";
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            boolean file_created = file.createNewFile();
            //create the file writer and store data in the text file.
            if(file_created){
                FileWriter writer = new FileWriter(fileName);
                writer.write("___Ticket Information___\n");
                writer.write("Row: "+row+"\n");
                writer.write("Seat: "+seat+"\n");
                writer.write("Price: £"+price+"\n");
                writer.write("\n");
                writer.write("___Personal Details___\n");
                writer.write("Name: "+Person.getName()+" "+ Person.getSurname()+"\n");
                writer.write("Email: "+Person.getEmail());

                writer.close();
            }
        }catch (IOException e){
            System.out.println("Error: File Not Created.");
        }
    }
}
