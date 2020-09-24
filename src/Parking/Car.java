package Parking;

public class Car extends Vehicle implements VehicleFee{

    public Car(String licenseNumber, String modelNumber, int parkingNumber, String date, boolean monthlyPass) {

    }

    @Override
    public double fee(String[] dateReturn) {
        return 0;
    }
}
