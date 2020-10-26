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

    public Parking(final int numOfSpots) {
        if (numOfSpots > 0) {
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
                submenu1.add("   Car");
                submenu1.add("   Motorbike");
                submenu1.add("   Cancel");
                this.numOfSpots = numOfSpots;
            } else {
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
        if ((numOfSpots - usedSpots) <= 0) {
            System.out.println("This parking has no space. Sorry and see you later.");
        } else {
            Scanner sc = new Scanner(System.in);

            System.out.println("Please, input information about this car");

            boolean check = true;
            String licenseNumber = "";
            while(check){
                System.out.print("License number: ");
                licenseNumber = sc.nextLine();
                if(findVehicle(licenseNumber)){
                    check = true;
                    System.out.println("This license number has already existed. Please input again!");
                }else{
                    check = false;
                }
            }

            System.out.print("Model: ");
            String modelNumber = sc.nextLine();
            int parkingNumber = usedSpots + 1;
            System.out.print("Date: ");
            String date = getDate();

            boolean isMonthlyPass = false;
            for (int i = 0; i < monthlyPass.size(); i++) {
                if (monthlyPass.get(i).getLicenseNumber().contains(licenseNumber)) {
                    isMonthlyPass = true;
                    break;
                } else {
                    isMonthlyPass = false;
                }
            }

            if (isMonthlyPass) {
                System.out.println("Monthly Pass: Yes");
            } else {
                System.out.println("Monthly Pass: No");
            }

            vehicles.add(new Car(licenseNumber, modelNumber, parkingNumber, date, isMonthlyPass));
            usedSpots++;
        }
    }

    public void parkMotorbike() {
        if ((numOfSpots - usedSpots) <= 0) {
            System.out.println("This parking has no space. Sorry and see you later.");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please, input information about this motorbike");

            boolean check = true;
            String licenseNumber = "";
            while(check){
                System.out.print("License number: ");
                licenseNumber = sc.nextLine();
                if(findVehicle(licenseNumber)){
                    check = true;
                    System.out.println("This license number has already existed. Please input again!");
                }else{
                    check = false;
                }
            }

            System.out.print("Model: ");
            String modelNumber = sc.nextLine();

            int parkingNumber = usedSpots + 1;

            System.out.print("Date: ");
            String date = getDate();

            boolean isMonthlyPass = false;
            for (int i = 0; i < monthlyPass.size(); i++) {
                if (monthlyPass.get(i).getLicenseNumber().contains(licenseNumber)) {
                    isMonthlyPass = true;
                    break;
                } else {
                    isMonthlyPass = false;
                }
            }

            if (isMonthlyPass) {
                System.out.println("Monthly Pass: Yes");
            } else {
                System.out.println("Monthly Pass: No");
            }

            vehicles.add(new Motorbike(licenseNumber, modelNumber, parkingNumber, date, isMonthlyPass));
            usedSpots++;
        }
    }

    /**
     * グェンタンヴィン
     *
     * @param licenseNumber
     * @return
     */
    //2. Return Vehicle
    public final boolean findVehicle(String licenseNumber) {
        boolean isResult = false;
        for (Vehicle x : vehicles) {
            if (x.getLicenseNumber().contains(licenseNumber)) {
                isResult = true;
                break;
            } else {
                isResult = false;
            }
        }
        return isResult;
    }

    public void returnVehicle() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter license Number: ");
        String licenseNumber = input.nextLine();
        if (findVehicle(licenseNumber)) {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getLicenseNumber().contains(licenseNumber)) {
                    double money = vehicles.get(i).fee(vehicles.get(i).getDate());
                    String date = vehicles.get(i).getDate();
                    String time = date + " to " + getToday();
                    incomeEarnings.add(new IncomeEarning(time, money));
                    vehicles.remove(i);
                    System.out.println("Returned Vehicle!");
                    usedSpots--;
                    break;
                }
            }
        } else {
            System.out.println("This Vehicle is not exsits!");
        }
    }

    //3. List Parked Vehicle
    public void listParkedVehicle() {
        if (vehicles.size() > 0) {
            for (int i = 0; i < vehicles.size(); i++) {
                    System.out.println(vehicles.get(i).toString());
            }
        } else {
            System.out.println("No Vehicle to show!");
        }
    }

    //4. Buy Monthly Pass
    public void buyMonthlyPass() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter License Number: ");
        String licenseNumber = input.nextLine();

        if (checkMonthlyPass(licenseNumber)) {
            for (int i = 0; i < monthlyPass.size(); i++) {
                if (monthlyPass.get(i).getLicenseNumber().contains(licenseNumber)) {
                    String date = monthlyPass.get(i).getDate();
                    String[] newMonth = convertStringToDate(date);
                    if(dateDiffFuture(date)<28){
                        System.out.print("Do you want to buy for the next Month? (Y/N): ");
                        boolean check = hasOrNot();
                        if (check) {
                            int month = Integer.parseInt(newMonth[1]);
                            String newDate = newMonth[0] + "-" + month + "-" + "28";
                            monthlyPass.remove(i);
                            monthlyPass.add(new MonthlyPass(newDate, licenseNumber));
                            System.out.println("Purchased!");
                            String time = newDate + " to " + getToday();
                            incomeEarnings.add(new IncomeEarning(time, 120));
                        }
                    }
                }

                for(int j = 0; j < vehicles.size(); j++){
                    if(vehicles.get(j).getLicenseNumber().contains(licenseNumber)){
                        vehicles.get(j).setMonthlyPass(true);
                        break;
                    }
                }
            }
        } else {
            System.out.print("Enter Month: ");
            boolean check = false;
            int month = getInRanger(12, 1);
            String monthly = "2020-" + month + "-28";
            if (dateDiffFuture(monthly) < 15) {
                System.out.print("It was half of the month, do you want to buy it for next month? (Y/N): ");
                boolean decision = hasOrNot();
                if (decision) {
                    month++;
                    monthly = "2000-" + month + "-01";
                    monthlyPass.add(new MonthlyPass(monthly, licenseNumber));
                    System.out.println("Purchased!");
                    String time = monthly + " to " + getToday();
                    incomeEarnings.add(new IncomeEarning(time, 120));
                }else{
                    monthlyPass.add(new MonthlyPass(monthly, licenseNumber));
                    System.out.println("Purchased!");
                    String time = monthly + " to " + getToday();
                    incomeEarnings.add(new IncomeEarning(time, 120));
                }
            } else {
                monthlyPass.add(new MonthlyPass(monthly, licenseNumber));
                System.out.println("Purchased!");
                String time = monthly + " to " + getToday();
                incomeEarnings.add(new IncomeEarning(time, 120));
            }
        }
    }

    public final boolean checkMonthlyPass(String licenseNumber) {
        boolean result = false;
        for (int i = 0; i < monthlyPass.size(); i++) {
            if (monthlyPass.get(i).getLicenseNumber().equals(licenseNumber)) {
                result = true;
                break;
            } else {
                result = false;
            }
        }
        return result;
    }

    //5. Display Income Earning Status
    public final void incomeEarnings() {
        for (int i = 0; i < incomeEarnings.size(); i++) {
            System.out.println(incomeEarnings.get(i).toString());
        }
    }

    //6. Close Parking
    public boolean closeParking() {
        boolean choice = false;
        if (vehicles.size() > 0) {
            System.out.println("There are still vehicles in this parking.");
            choice = false;
        } else {
                System.out.println("See you later!!");
                choice=true;
        }
        return choice;
    }

    //7. Exit Program
    public boolean exitParkingApp() {
        System.out.print("Are you sure to exit parking app? ");
        boolean choice = hasOrNot();
        if (choice) {
            saveDataFile();
            System.out.println("All data has been saved successfully");
        } else {
            System.out.println("The Program is continue...");
        }
        return choice;
    }

    public boolean loadDataFile() {

        String csvFile = "Vehicle.csv";
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
                usedSpots++;
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
            System.out.println("Saved File Vehicle.csv!");
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
            System.out.println("Saved File MonthlyPass.csv!");
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
            System.out.println("Saved File Income.csv!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Save to REPORT FINAL FILE(Report.txt)
        String line = "";
        String dataInput = "Vehicle List Data:\n";
        try (BufferedReader br = new BufferedReader(new FileReader("Vehicle.csv"))) {
            while ((line = br.readLine()) != null) {
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
            System.out.println("Done!");
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
