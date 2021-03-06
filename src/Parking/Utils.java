package Parking;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utils {
    public static String[] convertStringToDate(String date) {
        return date.split("-");
    }

    public static int getInRanger(int max, int min) {
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

    public static int dateDiff(String value1) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int dateDiff = 0;
        try {
            Date date1 = simpleDateFormat.parse(value1);
            Date date2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            long getDiff = date2.getTime() - date1.getTime();
            long dateDiffLong = (getDiff / (24 * 60 * 60 * 1000));
            dateDiff = (int) dateDiffLong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateDiff;
    }

    public static int dateDiffFuture(String value1) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int dateDiff = 0;
        try {
            Date date1 = simpleDateFormat.parse(value1);
            Date date2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            long getDiff = date1.getTime() - date2.getTime();
            long dateDiffLong = (getDiff / (24 * 60 * 60 * 1000));
            dateDiff = (int) dateDiffLong;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateDiff;
    }

    public static String getToday() {
        String newDate = "";
        try {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            newDate = simpleDateFormat.format(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String getDate() {
        Scanner input = new Scanner(System.in);
        boolean check = true;
        String date = null;
        while (check) {
            date = input.nextLine();
            if (date.matches("(20[0-2][0-9])[-](0[1-9]|1[0-2])[-](0[1-9]|1[0-9]|2[0-9]|3[0-1])")) {
                if (isDateValid(date) && (dateDiff(date) >= 0)) {
                    check = false;
                } else {
                    System.out.print("Invalid date! Check the days in the month. Re-enter: ");
                }
            } else {
                System.out.print("Incorrect Format of Date (yyyy-mm-dd), Re-enter: ");
            }
        }
        return date;
    }

    public static boolean isDateValid(String date) {
        final String DATE_FORMAT = "yyyy-MM-dd";
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
