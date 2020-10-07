package Parking;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static Parking.Utils.*;

public class Parking {
    private String filename = null;
    private int numOfSpots = 0;
    private int usedSpots = 0;
    private Menu mainmenu;
    private Menu submenu1;
    private boolean isClosed = false;
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<MonthlyPass> monthlyPass = new ArrayList<>();
    ArrayList<IncomeEarning> incomeEarnings = new ArrayList<>();

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

    public Parking(final String filename, final int numOfSpots) {
        if (filename != null && numOfSpots > 0) {
            if (loadDataFile(filename)) {
                mainmenu = new Menu("********* Parking Menu *********");
                mainmenu.add("Park Vehicle");
                mainmenu.add("Return Vehicle");
                mainmenu.add("List Parked Vehicle");
                mainmenu.add("Buy Monthly Pass");
                mainmenu.add("Income Earning");
                mainmenu.add("Close Parking");
                mainmenu.add("Exit Program");
                submenu1 = new Menu("   Select type of the Vehicle: ");
                submenu1.add("   Car");
                submenu1.add("   Motorbike");
                submenu1.add("   Cancel");
                this.numOfSpots = numOfSpots;
            }
        }
    }

    //1. Park Vehicle
    public void parkCar() {
        //TODO
    }

    public void parkMotorbike() {
        //TODO
    }

    /**
     * グェンタンヴィン
     * @param licenseNumber
     * @return
     */
    //2. Return Vehicle
    public final boolean findVehicle(String licenseNumber) {
        boolean isResult = true;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your vehicle license plates: ");
        licenseNumber = input.nextLine().toUpperCase();
        for (Vehicle x : vehicles) {
            if (!x.getLicenseNumber().contains(licenseNumber)){
                isResult = false;
                System.out.println("Your vehicle is not exist");
            }
            else {
                isResult = true;
            }
        }
        return isResult;
    }

    public void returnVehicle() {
    }

    //3. List Parked Vehicle
    public void listParkedVehicle() {

    }

    //4. Buy Monthly Pass
    public void buyMonthlyPass() {
        //Nap Date vao String Array [3] phan tu: vi du: Nhap ngay: arr[2]=getInt();    Nhap Thang: arr[1]=getInt();    Nhap Nam: arr[0]=getInt();
    }

    public final boolean checkMonthlyPass(String licenseNumber) {
        return true;
    }

    //5. Display Income Earning Status
    public final void incomeEarnings() {
    }

    //6. Close Parking
    public boolean closeParking() {
        return true;
    }

    //7. Exit Program
    public boolean exitParkingApp() {
        return true;
    }

    public boolean loadDataFile(String filename) {


        String csvFile = filename;
        String line = "";
        String cvsSplitBy = ",";
        boolean check = false;
        //Vehicle List
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Vehicle tempVehicle = null;
                String[] data = line.split(cvsSplitBy);
                String licenseNumber = data[0];
                String modelNumber = data[1];
                int parkingNumber = Integer.parseInt(data[2]);
                String date = data[3];
                boolean monthlyPass = Boolean.parseBoolean(data[4]);
                if (data[5].equals("C")) {
                    tempVehicle = new Motorbike(licenseNumber, modelNumber, parkingNumber, date, monthlyPass);
                } else {
                    tempVehicle = new Car(licenseNumber, modelNumber, parkingNumber, date, monthlyPass);
                }
                vehicles.add(tempVehicle);
            }
            check = true;
        } catch (IOException e) {
            e.printStackTrace();
            check = false;
            return check;
        }

        //MonthlyPass
        csvFile = "MonthlyPass.csv";
        line = "";
        cvsSplitBy = ":";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Vehicle temp = null;
                String[] data = line.split(cvsSplitBy);
                String date = data[0];
                String licenseNumber = data[1];
                MonthlyPass tempMonthPass = new MonthlyPass(date, licenseNumber);
                monthlyPass.add(tempMonthPass);
            }
            check = true;
        } catch (IOException e) {
            e.printStackTrace();
            check = false;
            return check;
        }
        //Income
        csvFile = "Income.csv";
        line = "";
        cvsSplitBy = ":";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Vehicle temp = null;
                String[] data = line.split(cvsSplitBy);
                String date = data[0];
                double money = Double.parseDouble(data[1]);
                IncomeEarning tempIncome = new IncomeEarning(date, money);
                incomeEarnings.add(tempIncome);
            }
            check = true;
        } catch (IOException e) {
            e.printStackTrace();
            check = false;
            return check;
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
        try (PrintWriter writer = new PrintWriter(new File("MonthlyPass.csv"))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < monthlyPass.size(); i++) {
                sb.append(monthlyPass.get(i).getDate() + ":");
                sb.append(monthlyPass.get(i).getLicenseNumber() + "\n");
            }
            writer.write(sb.toString());
            System.out.println("Saved File!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Save Income Earning List(Income.csv)
        try (PrintWriter writer = new PrintWriter(new File("Income.csv"))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < incomeEarnings.size(); i++) {
                sb.append(incomeEarnings.get(i).getDate() + ":");
                sb.append(incomeEarnings.get(i).getMoney() + "\n");
            }
            writer.write(sb.toString());
            System.out.println("Saved File!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Save to REPORT FINAL FILE(Report.txt)
        String line = "";
        String dataInput = "Vehicle List Data:\n";
        try (BufferedReader br = new BufferedReader(new FileReader("Vehicle.csv"))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Line: " + line);
                dataInput += line + "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        dataInput += "\nMonthly Pass Data: \n";
        try (BufferedReader br = new BufferedReader(new FileReader("MonthlyPass.csv"))) {
            while ((line = br.readLine()) != null) {
                dataInput += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataInput += "\nIncome Earning Data: \n";
        try (BufferedReader br = new BufferedReader(new FileReader("Income.csv"))) {
            while ((line = br.readLine()) != null) {
                dataInput += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new File("OUTPUT_FINAL.txt"))) {
            writer.write(dataInput);
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        parkingStatus();
        boolean check = true;
        do {
            int option = mainmenu.run();
            switch (option) {
                case 1:
                    boolean subCheck = true;
                    do {
                        int subOption = submenu1.run();
                        switch (subOption) {
                            case 1:
                                parkCar();
                                break;
                            case 2:
                                parkMotorbike();
                                break;
                            case 3:
                                subCheck = false;
                                break;
                        }
                    } while (subCheck);
                    break;
                case 2:
                    returnVehicle();
                    break;
                case 3:
                    listParkedVehicle();
                    break;
                case 4:
                    buyMonthlyPass();
                    break;
                case 5:
                    incomeEarnings();
                    break;
                case 6:
                    closeParking();
                    break;
                case 7:
                    exitParkingApp();
                    System.out.print("Exiting the App. Please confirm (Y/N): ");
                    boolean exit = hasOrNot();
                    if (exit) {
                        check = false;
                        System.out.println("Bye!");
                    } else {
                        System.out.println("Continuing the program!");
                    }
                    break;
            }
        } while (check);
    }
}
