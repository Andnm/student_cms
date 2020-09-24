package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Test {
    public static String[] convertToDate(String date) {
        String[] dateArray = date.split("-");
        return dateArray;
    }

    public static void main(String[] args) throws IOException {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        System.out.println(date);
        String dateString = date.toString();
        System.out.println(dateString);
        String[] dateArray = convertToDate(dateString);

        System.out.println("Day: " + dateArray[2] + " Month: " + dateArray[1] + " Year: " + dateArray[0]);


        try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("1");
            sb.append(',');
            sb.append("Nguyen Van A");
            sb.append(",");
            sb.append(true);
            sb.append('\n');

            sb.append("2");
            sb.append(',');
            sb.append("Long");
            sb.append(",");
            sb.append(false);
            sb.append('\n');

            sb.append("3");
            sb.append(',');
            sb.append("Hieu");
            sb.append(",");
            sb.append(true);
            sb.append('\n');
            writer.write(sb.toString());


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


        String csvFile = "test.csv";
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Human> humanList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                boolean fml = Boolean.parseBoolean(data[2]);

                Human temp = new Human(id, name, fml);
                humanList.add(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new File("filemoi.csv"))) {
            writer.write("DAY LA BAN MOI!\n");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("DONEEEEEE");

        String dataInput = "DATA from " + csvFile + ": \n";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                System.out.println("Line: "+line);
                dataInput += line+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Data Input: "+dataInput);

        dataInput += "\nDATA from filemoi: \n";
        try (BufferedReader br = new BufferedReader(new FileReader("filemoi.csv"))) {
            while ((line = br.readLine()) != null){
                dataInput += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(new File("OUTPUT_FINAL.txt"))) {
            writer.write(dataInput);
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("XONG!");
    }

    static class Human {
        private int id;
        private String name;
        private boolean fml;

        Human(int id, String name, boolean fml) {
            this.id = id;
            this.name = name;
            this.fml = fml;
        }

        @Override
        public String toString() {
            String result = "Human{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", have family: ";
            if (fml) {
                result += "Yes";
            } else {
                result += "No";
            }
            result += "}";
            return result;
        }
    }
}

