import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        RandomAccessFile rFile=new RandomAccessFile("FlightsFile.dat","rw");
        FlightsFile sFile = new FlightsFile(rFile);

//            sFile.flights();
//            sFile.addFlight();
//            sFile.BookingTicket();
//            sFile.updateFlight();
//            sFile.removeFlights();
        try {
            Menu.userAndPassword();
        } catch (Exception e) {
            System.err.println("invalid");
        }
    }



    }
