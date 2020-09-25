package Parking;

import java.io.*;
import java.util.ArrayList;

public class Parking {
    private String filename = null;
    private int numOfSpots = 0;
    private int usedSpots = 0;
    private Menu mainmenu;
    private Menu submenu1;
    private boolean isClosed = false;
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<String> MonthlyPass= new ArrayList<>();
    ArrayList<String> incomeEarnings= new ArrayList<>();

    public Parking() {
        filename = null;
        mainmenu = new Menu();
        submenu1 = new Menu();
        usedSpots = 0;
        numOfSpots = 0;
    }


    @Deprecated
    public Parking(final Parking other) {
    }

    public final boolean isEmpty() {
        return filename == null;
    }

    public final void parkingStatus() {
        System.out.println(" ******* Available Spots: " + (numOfSpots - usedSpots));
    }

    //1. Park Vehicle
    public Parking(final String filename, final int numOfSpots) {
        if (filename != null && numOfSpots > 0) {
            if (loadDataFile()) {
                mainmenu = new Menu("********* Parking Menu *********");
                mainmenu.add("Park Vehicle");
                mainmenu.add("Return Vehicle");
                mainmenu.add("List Parked Vehicle");
                mainmenu.add("Buy Monthly Pass");
                mainmenu.add("Income Earning");
                mainmenu.add("Close Parking");
                mainmenu.add("Exit Program");
                submenu1 = new Menu("   Select type of the Vehicle: ");
                submenu1.add("Car");
                submenu1.add("Motorbike");
                submenu1.add("Cancel");
            }
        }
    }

    //2. Return Vehicle
    public final boolean findVehicle(String licenseNumber) {
        return true;
    }
    public void returnVehicle() {

    }

    //3. List Parked Vehicle
    public void listParkedVehicle() {

    }

    //4. Buy Monthly Pass
    public void buyMonthlyPass() {
    }
    public final boolean checkMonthlyPass(String licenseNumber) {
        return true;
    }

    //5. Display Income Earning Status
    public final void incomeEarnings(){}

    //6. Close Parking
    public boolean closeParking() {
        return true;
    }

    //7. Exit Program
    public boolean exitParkingApp() {
        return true;
    }

    public boolean loadDataFile() {
        String csvFile = "Vehicle.csv";
        String line = "";
        String cvsSplitBy = ",";
        boolean check = false;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                Vehicle temp = null;
                String[] data = line.split(cvsSplitBy);
                String licenseNumber = data[0];
                String modelNumber = data[1];
                int parkingNumber = Integer.parseInt(data[2]);
                String date = data[3];
                boolean monthlyPass = Boolean.parseBoolean(data[4]);
                if (data[5].equals("C")) {
                    temp = new Motorbike(licenseNumber, modelNumber, parkingNumber, date, monthlyPass);
                } else {
                    temp = new Car(licenseNumber, modelNumber, parkingNumber, date, monthlyPass);
                }
                vehicles.add(temp);
            }
            check = true;
        } catch (IOException e) {
            e.printStackTrace();
            check = false;
        }
        return check;
    }

    public void saveDataFile() {
        //Save Vehicle List(Vehicle.csv)
        try (PrintWriter writer = new PrintWriter(new File("Vehicle.csv"))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vehicles.size(); i++) {
                sb.append(vehicles.get(i).getLicenseNumber() + ",");
                sb.append(vehicles.get(i).getModel() + ",");
                sb.append(vehicles.get(i).getParkingSpot() + ",");
                sb.append(vehicles.get(i).getDate() + ",");
                sb.append(vehicles.get(i).isMonthlyPass() + ",");
                if (vehicles.get(i) instanceof Car) {
                    sb.append("C\n");
                } else {
                    sb.append("M\n");
                }
            }
            writer.write(sb.toString());
            System.out.println("Saved File!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Save MonthlyPass List(MonthlyPass.csv)
        //TODO

        //Save Income Earning List(Income.csv)
        //TODO

        //Save to REPORT FINAL FILE(Report.txt)
        //TODO
    }

    public void run() {
        //TODO
    }
}
