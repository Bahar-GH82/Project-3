import java.util.ArrayList;
import java.util.Scanner;

public class Flights {
    ArrayList<Flight> table=new ArrayList<>();
    ArrayList<Flight> book=new ArrayList<>();
    ArrayList<Flight> search=new ArrayList<>();


    // This method shows the list of flights to the user.

    public void flights() {
        Flight flight = new Flight("XW-12", "Yaza", "Tehran", "1401-12-10", "12:30", 700000, 51);
        table.add(flight);
        flight = new Flight("WZ-15", "Mashhad", "Ahvaz", "1401-12-11", "08:00", 900000, 245);
        table.add(flight);
        flight = new Flight("BG-22", "Shiraz", "Tabriz", "1401-12-12", "22:30", 1100000, 12);
        table.add(flight);
        flight = new Flight("LS-45", "Yazd", "Kish", "1401-12-13", "4:30", 850000, 140);
        table.add(flight);
        flight = new Flight("FX-13", "Tehran", "Esfahan", "1401-12-14", "11:00", 1000000, 50);
        table.add(flight);
        flight = new Flight("ZA-38", "Tehran", "Yazd", "1401-12-15", "21:00", 700000, 51);
        table.add(flight);
        flight = new Flight("TM-58", "Ahvaz", "Mashhad", "1401-12-16", "10:30", 900000, 245);
        table.add(flight);
        flight = new Flight("SD-74", "tabriz", "Shiraz", "1401-12-17", "00:00", 1100000, 12);
        table.add(flight);
        flight = new Flight("UP-24", "Kish", "Yazd", "1401-12-18", "14:15", 850000, 140);
        table.add(flight);
        flight = new Flight("QR-88", "Esfahan", "Tehran", "1401-12-19", "23:00", 700000, 51);
        table.add(flight);
    }

    public void see(){
        for (Flight flight : table) {
            System.out.println(flight);
        }
    }


    //This method can add flight with Admin.
    public void addFlight(){

        Scanner input=new Scanner(System.in);
        System.out.println(":::::::::: *Add Flights* ::::::::::");
        System.out.println(".................................");
        System.out.println("Please enter the flightId:");
        String flightId;
        String Origin;
        String Destination;
        String Date;
        String Time;
        float Price;
        int seats;
        flightId=input.nextLine();
        System.out.println("Please enter the Origin:");
        Origin=input.nextLine();
        System.out.println("Please enter the Destination:");
        Destination=input.nextLine();
        System.out.println("Please enter the Date:");
        Date=input.nextLine();
        System.out.println("Please enter the Time:");
        Time=input.nextLine();
        System.out.println("Please enter the Price:");
        Price=input.nextFloat();
        System.out.println("Please enter the Seats:");
        seats=input.nextInt();
        Flight flight=new Flight(flightId,Origin,Destination,Date,Time,Price,seats);
        table.add(flight);
        for (int i = 0; i < table.size(); i++) {
            System.out.println(table.get(i));
        }


    }

    //Admin can update flights with this method.
    public void updateFlight(){
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter new flightId:");
        String flightId=input.nextLine();
        for (Flight flight:table){
            if(flight.getFlightId().equals(flightId)){
                System.out.println("Please enter new Origin:");
                String Origin=input.next();
                flight.setOrigin(Origin);
                System.out.println("Please enter new Destination:");
                String Destination=input.next();
                flight.setDestination(Destination);
                System.out.println("Please enter new Date:");
                String Date=input.next();
                flight.setDate(Date);
                System.out.println("Please enter new Time:");
                String Time=input.next();
                flight.setTime(Time);
                System.out.println("Please enter new Price:");
                float Price=input.nextFloat();
                flight.setPrice(Price);
                System.out.println("Please enter new Seats:");
                int seats=input.nextInt();
                flight.setSeats(seats);
                System.out.println("*The new flight "+flightId+" updated successful*" );

            }
        }
    }



    //with this method Admin can remove flights.
    public void removeFlights(){
        Scanner input=new Scanner(System.in);
        System.out.println("flight id of you want to remove:");
        String flightId=input.nextLine();

        for (int i = 0; i < table.size(); i++) {
            Flight flights=table.get(i);
            if (flights.getFlightId().equals(flightId)){
                table.remove(i);
            }
        }
    }

    //The user can search flights that want with this method.
    public void searchFlight() {
        Scanner input = new Scanner(System.in);
        System.out.println("1.origin");
        System.out.println("if you want to search in this base enter number else enter '0'");
        int ans1= input.nextInt();
        input.nextLine();
        if (ans1==1) {
            System.out.println("origin:");
            String origin = input.nextLine();
            for (int i = 0; i < table.size(); i++) {
                if (table.get(i) != null) {
                    Flight flight1 = table.get(i);
                    if (flight1.getOrigin().equals(origin)) {
                        search.add(flight1);
                    }
                }
            }
        }
        System.out.println("2.Destination");
        System.out.println("if you want to search in this base enter number else enter '0'");
        int ans2= input.nextInt();
        input.nextLine();
        if (ans2==2){
            System.out.println("Destination:");
            String destination=input.nextLine();
            for (int i = 0; i < search.size(); i++) {
                Flight flight2=search.get(i);
                System.out.println(search.get(i));
                if (flight2.getDestination().equals(destination)) {
                    System.out.println("if"+flight2.getFlightId());
                }
                else {
                    System.out.println("else"+flight2.getFlightId());
                    search.remove(flight2);
                }
            }
        }
    }

    public void BookingTicket() {
        Scanner input=new Scanner(System.in);

        System.out.println("please enter the flightId that you want book:");
        String flightId = input.nextLine();
        for (int i = 0; i < table.size(); i++) {
            Flight flight2 = table.get(i);
            if (flight2.getFlightId().equals(flightId)) {
                book.add(flight2);
                flight2.setSeats(flight2.getSeats() - 1);
                break;
            }
        }
    }
    public void ticketCancellation(){
        Scanner input=new Scanner(System.in);
        System.out.println("flight id that you want to cancel:");
        String id=input.nextLine();
        for (int i = 0; i < table.size(); i++) {
            Flight flight3=table.get(i);
            if (flight3.getFlightId().equals(id)){
                book.remove(flight3);
                break;
            }
        }
    }

    public void bookedTickets(){
        System.out.println(book.size());
        for (int i = 0; i < book.size(); i++) {
            System.out.println(book.get(i));
        }
    }
}
