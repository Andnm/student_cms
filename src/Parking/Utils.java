package Parking;

import java.text.ParseException;
import java.util.Scanner;

public class Utils {
    public static String[] convertToDate(String date) {
        return date.split("-");
    }

    public static int getInRanger(int max, int min) throws ParseException {
        Scanner input = new Scanner(System.in);
        int selection = 0;
        do {
            selection = getInt();
            if (selection > max || selection < min) {
                System.out.print("Invalid selection, try again: ");
            }
        } while (selection > max || selection < min);
        return selection;
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
            } else {
                System.out.print("Not Number Format. Re-enter: ");
            }
        }
        return number;
    }

    public static int getInt() {
        int result = 0;
        int check = 0;
        do {
            double number = getDouble();
            if (((int) number % number) == 0) {
                result = (int) number;
                check = 1;
            } else {
                System.out.print("It is not Integer! Re-Enter: ");
            }
        } while (check == 0);
        return result;
    }

    public static boolean hasOrNot() {
        Scanner input = new Scanner(System.in);
        boolean has = false;
        boolean check = false;
        do {
            String function = input.nextLine();
            if (function.equals("Y") || function.equals("N") || function.equals("y") || function.equals("n")) {
                has = function.equals("Y") || function.equals("y");
                check = true;
            } else {
                System.out.print("The answer must be Y or N. Re-Enter: ");
                check = false;
            }
        } while (!check);
        return has;
    }

}
