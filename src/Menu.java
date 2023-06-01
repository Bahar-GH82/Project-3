import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Menu {
    public static int printMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("""
                              ::::::::::::::::::::::::::::::::::::::::::::
                                 WELCOME TO AIRLINE RESERVATION SYSTEM   \s
                              ::::::::::::::::::::::::::::::::::::::::::::
                               .............. MENU OPTIONS ..............
                                 <1> Sign in
                                 <2> Sign up""");
        int ans=input.nextInt();
        if (ans!=1 && ans!=2){
            System.out.println("try again");
            ans=input.nextInt();
        }
        return ans;
    }

    //This method is the main method for running the program.
    public static void userAndPassword() throws IOException {
        Scanner input=new Scanner(System.in);
        Passenger[] passengers=new Passenger[1000];
        int flag=0,nouFound=0;
        String userName,password;
        int ans=printMenu();

        while (true){
            if (ans==2){
                passengers [flag]=new Passenger();
                System.out.println("Your username:");
                userName=input.nextLine();
                passengers[flag].setUserName(userName);

                System.out.println("Your password:");
                password=input.nextLine();
                passengers[flag].setPassword(password);
                flag++;
                System.out.println("Okay!");
                ans=printMenu();

            } else if (ans==1) {
                System.out.println("Your username:");
                userName = input.nextLine();
                System.out.println("Your password:");
                password = input.nextLine();

                if (userName.equals("Admin") && password.equals("Admin")) {
                    adminOptions(adminMenu());
                } else {
                    for (int i = 0; i < passengers.length; i++) {
                        if (passengers[i] != null) {
                            System.out.println(passengers[i].getUserName() + passengers[i].getPassword());
                            if (passengers[i].getPassword().equals(password) && passengers[i].getUserName().equals(userName)) {
                                passengerAnswers(passengerMenu());
                                nouFound++;
                            }
                        }
                    }
                    if (nouFound == 0) {
                        System.out.println("not found");
                    }
                }
            }
            else {
                System.out.println("TRY AGAIN:");
                ans=printMenu();
            }
        }
    }

    public static int adminMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("""
                              :::::::::::::::::::::::::::::::::::
                                     ADMIN MENU OPTIONS
                              :::::::::::::::::::::::::::::::::::
                               .................................
                                  <1> Add
                                  <2> Update
                                  <3> Remove
                                  <4> Flight schedules
                                  <5> Sign out""");
        int ans=input.nextInt();
        if (ans!=1 && ans!=2 && ans!=3 && ans!=4 && ans!=5){
            System.out.println("try again");
            ans=input.nextInt();
        }
        return ans;
    }

    public static void adminOptions(int ans) throws IOException {
        Flights flights=new Flights();
        RandomAccessFile rFile=new RandomAccessFile("FlightsFile.dat","rw");
        FlightsFile sFile = new FlightsFile(rFile);
//        flights.flights();
        while (ans!=5){
            switch (ans){
                case 1:
//                    flights.addFlight();
                    sFile.addFlight();
                    break;
                case 2:
//                    flights.updateFlight();
                    sFile.updateFlight();
                    break;
                case 3:
//                    flights.removeFlights();
                    sFile.removeFlights();
                    break;
                case 4:
//                    flights.see();
                    sFile.flights();
                    break;
                default:
                    System.out.println("please try again:");
                    adminMenu();
                    break;
            }
            ans=adminMenu();
        }
        userAndPassword();
    }

    public static int passengerMenu(){
        Scanner input=new Scanner(System.in);
        System.out.println("""
                              ::::::::::::::::::::::::::::::::::::::
                                     PASSENGER MENU OPTIONS
                              ::::::::::::::::::::::::::::::::::::::
                               ....................................
                                  <1> Change password
                                  <2> Search flight tickets
                                  <3> Booking ticket
                                  <4> Ticket cancellation
                                  <5> Booked tickets
                                  <6> Add charge
                                  <0> Sign out""");
        int ans=input.nextInt();
        if (ans!=1 && ans!=2 && ans!=3 && ans!=4 && ans!=5 && ans!=6 && ans!=0){
            System.out.println("try again");
            ans=input.nextInt();
        }
        return ans;
    }

    public static void passengerAnswers(int ans) throws IOException{
        Passenger passenger=new Passenger();
        Flights flights=new Flights();
        RandomAccessFile rFile=new RandomAccessFile("FlightsFile.dat","rw");
        FlightsFile sFile = new FlightsFile(rFile);
        Scanner input=new Scanner(System.in);
//        flights.flights();
        while (ans!=0) {
            switch (ans) {

                case 1:
                    System.out.println("Please enter new password:");
                    String password = input.nextLine();
                    passenger.changePassword(password);
                    break;
                case 2:
//                    flights.searchFlight();
                    break;

                case 3:
//                    flights.see();
//                    flights.BookingTicket();
                    sFile.flights();
                    sFile.BookingTicket();
                    break;
                case 4:
//                   flights.ticketCancellation();
                    sFile.ticketCancellation();
                    break;
                case 5:
//                   flights.bookedTickets();
                    sFile.bookedTickets();
                    break;
                case 6:
                    System.out.println("please enter your price to charge:");
                    float charge = input.nextFloat();
                    passenger.addCharge(charge);
                    break;
                default:
                    System.out.println("Please try again:");
                    passengerMenu();
                    break;
            }
            ans=passengerMenu();
        }
        userAndPassword();
    }
}
