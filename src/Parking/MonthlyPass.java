package Parking;

public class MonthlyPass {
    private String[] date;
    private String licenseNumber;

    public MonthlyPass(String[] date, String licenseNumber) {
        if (date.length > 0) {
            for (int i = 0; i < date.length; i++) {
                this.date[i] = date[i];
            }
            this.licenseNumber = licenseNumber;
        } else {
            System.out.println("Fail to create Monthly Pass: Date empty!");
        }
    }
}
