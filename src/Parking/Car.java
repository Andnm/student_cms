package Parking;

import static Parking.Utils.dateDiff;

public class Car extends Vehicle {

    public Car(String licenseNumber, String model, int parkingNumber, String date, boolean monthlyPass) {
        super(licenseNumber, model, parkingNumber, date, monthlyPass);
    }

    public double fee(String dateReturn) {
        double money=0;
        if(super.isMonthlyPass()){
            money=0;
        }else{
            int numOfDays = dateDiff(dateReturn);
            money=6 + (numOfDays * 3);
        }
        return money;
    }

    @Override
    public String toString() {
        String result="Type: Car\n";
        result+="License Number: "+super.getLicenseNumber()+"\n";
        result+="Model: "+super.getModel()+"\n";
        result+="Parking Number: "+super.getParkingSpot()+"\n";
        result+="Parking Date: "+super.getDate()+"\n";
        result+="Monthly Pass: ";
        if(super.isMonthlyPass()){
            result+="Yes\n";
        }else{
            result+="No\n";
        }
        result+="Fee: "+fee(super.getDate())+"\n";
        return result;
    }
}
