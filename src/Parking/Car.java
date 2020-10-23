package Parking;

public class Car extends Vehicle implements VehicleFee{

    public Car(String licenseNumber, String modelNumber, int parkingNumber, String date, boolean monthlyPass) {
        super(licenseNumber, modelNumber, parkingNumber, date, monthlyPass);
    }

    @Override
    public double fee(String[] dateReturn) {
        return 0;
    }
}
