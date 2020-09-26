package Parking;

public class IncomeEarning {
    private String[] date;
    private double money;

    public IncomeEarning(String[] date, double money) {
        if (date.length > 0) {
            for (int i = 0; i < date.length; i++) {
                this.date[i] = date[i];
            }
            this.money = money;
        } else {
            System.out.println("Fail to create Income Earning: Date empty!");
        }
    }
}
