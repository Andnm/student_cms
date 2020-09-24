package Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatch {
    public static void main(String[] args) {
        System.out.print("Nhap Double: ");
        double so1 = getDouble();
        System.out.println("So Double: " + so1);
    }


    public static double getDouble() {
        boolean check = true;
        String in = null;
        double number = 0;
        while (check) {
            Scanner input = new Scanner(System.in);
            in = input.nextLine();
            if (!in.contains("f")) {
                try {
                    number = Double.parseDouble(in);
                    check = false;
                } catch (NumberFormatException e) {
                    System.out.print("Not Number Format. Re-enter: ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.print("Not Number Format. Re-enter: ");
            }
        }
        return number;
    }
}
