package Parking;

public class IncomeEarning {
    private String date;
    private double money;

    public IncomeEarning(String date, double money) {
        if (date != null) {
            this.date = date;
            this.money = money;
            //test
        } else {
            System.out.println("Fail to create Income Earning: Date empty!");
        }
    }

    public String getDate() {
        return date;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        String result = date + ": " + "$" + money + "\n";
        return result;
    }
}
