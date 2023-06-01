import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightsFile {
    private RandomAccessFile rFile;
    private final int FIX_SIZE = 20;
    Flight flight;

    ArrayList<Flight> table = new ArrayList<>();

    public FlightsFile(RandomAccessFile rFile) {
        this.rFile = rFile;
    }

    public void write(Flight flight) throws IOException {
        rFile.writeChars(fixString(flight.getFlightId()));
        rFile.writeChars(fixString(flight.getOrigin()));
        rFile.writeChars(fixString(flight.getDestination()));
        rFile.writeChars(fixString(flight.getDate()));
        rFile.writeChars(fixString(flight.getTime()));
        rFile.writeInt(flight.getSeats());
        rFile.writeDouble(flight.getPrice());
    }

    public void read() throws IOException {

        String flightId = readFixString();
        System.out.println("Id:" + flightId);
        String origin = readFixString();
        System.out.println("origin:" + origin);
        String destination = readFixString();
        System.out.println("destination:" + destination);
        String date = readFixString();
        System.out.println("date:" + date);
        String time = readFixString();
        System.out.println("time:" + time);
        int seats = rFile.readInt();
        System.out.println("seats:" + seats);
        double price = rFile.readDouble();
        System.out.println("price:" + price);
    }

    public void readFile() throws IOException {
        for (int i = 0; i < rFile.length(); i += 212) {
            rFile.seek(i);
            read();
        }
    }

    public void flights() throws IOException {

        flight = new Flight("XW-12", "Yazd", "Tehran", "1401-12-10", "12:30", 700000, 51);
        write(flight);
        flight = new Flight("WZ-15", "Mashhad", "Ahvaz", "1401-12-11", "08:00", 900000, 245);
        write(flight);
        flight = new Flight("BG-22", "Shiraz", "Tabriz", "1401-12-12", "22:30", 1100000, 12);
        write(flight);
        flight = new Flight("LS-45", "Yazd", "Kish", "1401-12-13", "4:30", 850000, 140);
        write(flight);
        flight = new Flight("FX-13", "Tehran", "Esfahan", "1401-12-14", "11:00", 1000000, 50);
        write(flight);
        flight = new Flight("ZA-38", "Tehran", "Yazd", "1401-12-15", "21:00", 700000, 51);
        write(flight);
        flight = new Flight("TM-58", "Ahvaz", "Mashhad", "1401-12-16", "10:30", 900000, 245);
        write(flight);
        flight = new Flight("SD-74", "tabriz", "Shiraz", "1401-12-17", "00:00", 1100000, 12);
        write(flight);
        flight = new Flight("UP-24", "Kish", "Yazd", "1401-12-18", "14:15", 850000, 140);
        write(flight);
        flight = new Flight("QR-88", "Esfahan", "Tehran", "1401-12-19", "23:00", 700000, 51);
        write(flight);
        rFile.seek(0);
        for (int i = 0; i < 10; i++) {
            read();
        }
    }


    public String fixString(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() < FIX_SIZE) {
            sBuilder.append(" ");
        }
        s = sBuilder.toString();
        return s.substring(0, FIX_SIZE);
    }

    public String readFixString() throws IOException {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < FIX_SIZE; i++) {
            tmp.append(rFile.readChar());
        }
        return tmp.toString().trim();
    }

    public void addFlight() throws IOException {

        Scanner input = new Scanner(System.in);
        System.out.println(":::::::::: *Add Flights* ::::::::::");
        System.out.println(".................................");
        System.out.println("Please enter the flightId:");
        String flightId;
        String Origin;
        String Destination;
        String Date;
        String Time;
        double Price;
        int seats;
        flightId = input.nextLine();
        System.out.println("Please enter the Origin:");
        Origin = input.nextLine();
        System.out.println("Please enter the Destination:");
        Destination = input.nextLine();
        System.out.println("Please enter the Date:");
        Date = input.nextLine();
        System.out.println("Please enter the Time:");
        Time = input.nextLine();
        System.out.println("Please enter the Price:");
        Price = input.nextDouble();
        System.out.println("Please enter the Seats:");
        seats = input.nextInt();
        Flight flight2 = new Flight(flightId, Origin, Destination, Date, Time, Price, seats);
        flights();
        write(flight2);
        rFile.seek(2120);
        read();
        System.out.println("If you want to add another flight please enter 0 else enter 1");
        String answer = input.next();
        while (answer.equals("0")) {
            System.out.println("Please enter the flightId:");
            flightId = input.nextLine();
            System.out.println("Please enter the Origin:");
            Origin = input.nextLine();
            System.out.println("Please enter the Destination:");
            Destination = input.nextLine();
            System.out.println("Please enter the Date:");
            Date = input.nextLine();
            System.out.println("Please enter the Time:");
            Time = input.nextLine();
            System.out.println("Please enter the Price:");
            Price = input.nextDouble();
            System.out.println("Please enter the Seats:");
            seats = input.nextInt();
            Flight flight = new Flight(flightId, Origin, Destination, Date, Time, Price, seats);
            write(flight);
            int pos = 2120;
            rFile.seek(pos += 212);
            read();
            System.out.println("If you want to add another flight please enter 0");
            answer = input.next();
        }

    }

    public void updateFlight() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println(":::::::::: *Update Flights* ::::::::::");
        System.out.println(".................................");
        System.out.println("Please enter flightId:");
        String flightId = input.nextLine();
        if (flight.getFlightId().equals(flightId)) {
            System.out.println("Please enter new Origin:");
            String Origin = input.next();
            flight.setOrigin(Origin);
            System.out.println("Please enter new Destination:");
            String Destination = input.next();
            flight.setDestination(Destination);
            System.out.println("Please enter new Date:");
            String Date = input.next();
            flight.setDate(Date);
            System.out.println("Please enter new Time:");
            String Time = input.next();
            flight.setTime(Time);
            System.out.println("Please enter new Price:");
            double Price = input.nextDouble();
            flight.setPrice(Price);
            System.out.println("Please enter new Seats:");
            int seats = input.nextInt();
            flight.setSeats(seats);
            System.out.println("*The new flight " + flightId + " updated successful*");
            write(flight);
            rFile.seek(2120);
            read();
        }
    }

    public void removeFlights() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("flight id of you want to remove:");
        String flightId =input.nextLine();

    }


    public void BookingTicket() throws IOException {
        Scanner input=new Scanner(System.in);

        System.out.println("please enter the flightId that you want book:");
        String flightId = input.nextLine();
        for (int i = 0; i < rFile.length(); i++)
            if (flight.getFlightId().equals(flightId)) {
                write(flight);
                rFile.seek(2120);
                read();
            }

    }


    public void ticketCancellation() throws IOException{
        Scanner input=new Scanner(System.in);
        System.out.println("flight id that you want to cancel:");
        String id=input.nextLine();
        for (int i = 0; i < rFile.length(); i++) {
            if (flight.getFlightId().equals(id)){
                table.remove(flight);
                break;
            }
        }
    }

    public void bookedTickets() throws IOException {
        System.out.println(rFile.getFilePointer());
        System.out.println();
    }
}
