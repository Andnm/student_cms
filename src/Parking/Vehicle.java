package Parking;

public class Vehicle {
    private String licenseNumber = null;
    private String model = null;
    private int parkingNumber = 0;
    private String date = null;
    private boolean monthlyPass = false;

    public Vehicle() {
        licenseNumber = null;
        model = null;
        parkingNumber = 0;
        date = null;
        monthlyPass = false;
    }

    public Vehicle(final String licenseNumber, final String model, final int parkingNumber, final String date, final boolean monthlyPass) {
        if (licenseNumber != null && model != null && parkingNumber != 0) {
            this.licenseNumber = licenseNumber;
            this.model = model;
            this.parkingNumber = parkingNumber;
            this.date = date;
            this.monthlyPass = monthlyPass;
        } else {
            this.licenseNumber = null;
            this.model = null;
            this.parkingNumber = 0;
            this.date = null;
            this.monthlyPass = false;
            System.out.println("Fail to create this Vehicle");
        }
    }

    @Deprecated
    public Vehicle(final Vehicle other) {
    }

    public final int getParkingSpot() {
        return parkingNumber;
    }

    public void setParkingSpot(final int parkingNumber) {
        this.parkingNumber = parkingNumber;
        System.out.println("Successful Changed!\n");
    }

    public final String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(final String licenseNumber) {
        if (licenseNumber != null) {
            this.licenseNumber = licenseNumber;
        } else {
            System.out.println("Unsuccessful Changed!\n");
        }
    }

    public void setModel(final String model) {
        if (model != null) {
            this.model = model;
            System.out.println("Successful Changed!\n");
        } else {
            System.out.println("Unsuccessful Changed!\n");
        }
    }

    public final String getModel() {
        return model;
    }

    public final String getDate(){
        return date;
    }

    public final boolean isMonthlyPass() {
        return monthlyPass;
    }
}
