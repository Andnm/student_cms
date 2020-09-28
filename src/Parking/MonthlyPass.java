package Parking;

public class MonthlyPass {
    private String date;
    private String licenseNumber;

    public MonthlyPass(String date, String licenseNumber) {
        if (date != null && licenseNumber != null) {
            this.date = date;
            this.licenseNumber = licenseNumber;
        } else {
            System.out.println("Fail to create Monthly Pass: Date empty!");
        }
    }

    public String getDate() {
        return date;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
}
