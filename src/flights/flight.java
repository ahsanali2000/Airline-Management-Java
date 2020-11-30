package flights;
import accounts.customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class flight {
    public int id;
    public String from;
    public String to;
    public int noOfSeats;
    public String departure;

    public flight(int id, String from, String to, String departure, int noOfSeats){
        this.id = id;
        this.from = from;
        this.to = to;
        this.noOfSeats = noOfSeats;
        this.departure = departure;
    }
    public static void cancelBooking(int ticketNumber) throws IOException {
        File my_Obj = new File("src/database/bookings.txt");
        Scanner my_Reader = new Scanner(my_Obj);
        boolean flag = false;
        int counter=0;
        int counterForList=0;
        String datanew="ticketNo,flightID,seatNo\n";
        while(my_Reader.hasNextLine()){
            String data = my_Reader.nextLine();
            if (counter!=0) {
                int noTicket = Integer.parseInt(data.split(",")[0]);
                if (ticketNumber == noTicket) {
                    flag = true;
                    String[] abc = data.split(",")[2].split("--");
                    for (int i=0; i<abc.length;i++){
                        closeBooking(Integer.parseInt(data.split(",")[1]), Integer.parseInt(abc[i]));
                    }
                } else {
                    datanew +=data+"\n";
                }
            }
            counter=1;

        }
        if(flag){
            FileWriter writerBookings = new FileWriter("src/database/bookings.txt");
            System.out.println(datanew);
            writerBookings.write(datanew);
            writerBookings.close();
        }
    }
    public static boolean closeBooking(int flightID, int Seatnumber) throws IOException {
        File myObj = new File("src/database/flights/"+flightID+".txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] allSeats= data.split(",");
            String[] currentSeat = allSeats[Seatnumber-1].split("--");
            if (currentSeat.length>1){
                allSeats[Seatnumber-1] = currentSeat[0];
                String allString ="";
                for(int i = 0; i < allSeats.length; i++) {
                    allString=allString+allSeats[i]+",";
                }
                FileWriter writer = new FileWriter("src/database/flights/"+flightID+".txt");
                writer.write(allString);
                writer.close();
                return true;
            }
        }
        return false;
    }
    public static int doBookSeat(customer obj,int flightID, String Seatnumber) throws IOException {
        File myObj_ = new File("src/database/bookings.txt");
        Random rand = new Random();
        int bookingID = rand.nextInt(1000000);
        Scanner myReader_ = new Scanner(myObj_);
        boolean flag = true;
        int counter=0;
        while (myReader_.hasNextLine()) {
            String data = myReader_.nextLine();
            if(counter!=0) {
                int onTicket = Integer.parseInt(data.split(",")[0]);
                if (bookingID == onTicket) {
                    flag = false;
                    break;
                }
            }
            counter+=1;
        }
        if(flag) {
            FileWriter writer_ = new FileWriter("src/database/bookings.txt", true);
            writer_.write(bookingID + "," + flightID + "," + Seatnumber.replaceAll(",","--") + "\n");
            writer_.close();
            String[] listSeatNo = Seatnumber.split(",");
            for (int i=0; i<listSeatNo.length;i++){
                bookSeat(obj,flightID,Integer.parseInt(listSeatNo[i]));
            }
            return bookingID;
        }
        else{
            doBookSeat(obj,flightID,Seatnumber);
            return bookingID;
        }
    }

    public static boolean bookSeat(customer obj,int flightID, int Seatnumber) throws IOException {


        File myObj = new File("src/database/flights/"+flightID+".txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] allSeats= data.split(",");
            String[] currentSeat = allSeats[Seatnumber-1].split("--");
            if (currentSeat.length==1){
                allSeats[Seatnumber-1] = currentSeat[0]+"--"+obj.username;
                String allString ="";
                for(int i = 0; i < allSeats.length; i++) {
                    allString=allString+allSeats[i]+",";
                }
                FileWriter writer = new FileWriter("src/database/flights/"+flightID+".txt");
                writer.write(allString);
                writer.close();
                return true;
            }
        }
        return false;
    }
    public static String[] allFlights() throws FileNotFoundException {
        File myObj = new File("src/database/flights.txt");
        Scanner myReader = new Scanner(myObj);
        String output = "";
        int counter=1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (counter!=1){
                output = output + data +" ";
            }
            counter=0;
        }
        String[] outputArray = output.split(" ");

        return outputArray;
    }

    public static flight search(int id) throws FileNotFoundException {
        File myObj = new File("src/database/flights.txt");
        Scanner myReader = new Scanner(myObj);
        int counter=1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (counter!=1){
                String idNow = data.split(",")[0];
                if (Integer.parseInt(idNow)==id) {
                    String from = data.split(",")[1];
                    String to = data.split(",")[2];
                    int noOfSeats = Integer.parseInt(data.split(",")[4]);
                    String departure = data.split(",")[3];
                    return new flight(id, from, to, departure, noOfSeats);
                }
            }
            counter=0;
        }
        return null;
    }
    public static flight createFlight(String from, String to, String departure, int noOfSeats) throws IOException {
        from=from.replaceAll(" ","_");
        to=to.replaceAll(" ","_");
        departure=departure.replaceAll(" ","_");
        Random rand = new Random();
        int flightID = rand.nextInt(1000000);
        File myObj = new File("src/database/flights.txt");
        Scanner myReader = new Scanner(myObj);
        boolean flag = true;
        int counter=1;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (counter!=1) {
                String id = data.split(",")[0];
                if (Integer.parseInt(id) == flightID) {
                    flag = false;
                    break;
                }
            counter=0;
            }
        }
        flight obj = new flight(flightID,from, to,departure, noOfSeats);
        if(flag) {
            FileWriter writer = new FileWriter("src/database/flights.txt", true);
            writer.write(obj.id + "," + obj.from + "," + obj.to +","+obj.departure+","+ obj.noOfSeats+"\n");
            writer.close();
            FileWriter writer1 = new FileWriter("src/database/flights/"+obj.id+".txt");
            for (int i=1;i<= obj.noOfSeats ;i++){
                writer1.write(i+",");
            }
            writer1.close();
            return obj;
        }
        else{
            return null;
        }
    }

    public static String[] searchTicket(int ticketNo) throws FileNotFoundException {
        flight obj_flight = null;
        String username = "";
        File myObj_ = new File("src/database/bookings.txt");
        Random rand = new Random();
        int bookingID = rand.nextInt(1000000);
        Scanner myReader_ = new Scanner(myObj_);
        int flight_id = 0;int seat_no=0;
        boolean flag = false;
        int counter=0;
        while (myReader_.hasNextLine()) {
            String data = myReader_.nextLine();
            if(counter!=0) {
                int onTicket = Integer.parseInt(data.split(",")[0]);
                if (bookingID == onTicket) {
                    flight_id = Integer.parseInt(data.split(",")[1]);
                    seat_no = Integer.parseInt(data.split(",")[2]);
                    flag = true;
                    break;
                }
            }
            counter+=1;
        }
        if (flag){
            obj_flight = search(flight_id);

            File myObj11 = new File("src/database/flights/"+flight_id+".txt");
            Scanner myReader11 = new Scanner(myObj11);
            while (myReader11.hasNextLine()){
                String data = myReader11.nextLine();
                String[] allSeats= data.split(",");
                String[] currentSeat = allSeats[seat_no-1].split("--");
                if (currentSeat.length==2){
                    username=currentSeat[1];
                }
            }
        }
        String[] allTOreturn = {username,ticketNo+"",flight_id+"",obj_flight.from,obj_flight.to,obj_flight.departure,seat_no+""};
        return allTOreturn;
    }
}
