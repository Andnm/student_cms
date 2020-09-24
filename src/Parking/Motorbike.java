package Parking;

public class Motorbike extends Vehicle implements VehicleFee {

    public Motorbike(String licenseNumber, String modelNumber, int parkingNumber, String date, boolean monthlyPass) {
        super(licenseNumber, modelNumber, parkingNumber, date, monthlyPass);
    }

    @Override
    public double fee(String[] dateReturn) {
        return 0;
    }
}
