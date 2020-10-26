package Parking;

import static Parking.Utils.*;

public class Motorbike extends Vehicle {

    public Motorbike(String licenseNumber, String model, int parkingNumber, String date, boolean monthlyPass) {
        super(licenseNumber, model, parkingNumber, date, monthlyPass);
    }

    public double fee(String dateReturn) {
        double money=0;
        if(super.isMonthlyPass()){
            money=0;
        }else{
            int numOfDays = dateDiff(dateReturn);
            money=4 + (numOfDays * 3);
        }
        return money;
    }

    @Override
    public String toString() {
        String result = "Type: Motorbike\n";
        result += "License Number: " + super.getLicenseNumber() + "\n";
        result += "Model: " + super.getModel() + "\n";
        result += "Parking Number: " + super.getParkingSpot() + "\n";
        result += "Parking Date: " + super.getDate() + "\n";
        result += "Monthly Pass: ";
        if (super.isMonthlyPass()) {
            result += "Yes\n";
        } else {
            result += "No\n";
        }
        result += "Fee: " + fee(super.getDate()) + "\n";
        return result;
    }
}
