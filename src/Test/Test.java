package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class Test {
    public static String[] convertToDate(String date) {
        String[] dateArray = date.split("-");
        return dateArray;
    }

    public static void main(String[] args) {
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
            sb.append("31");
            sb.append('\n');

            sb.append("2");
            sb.append(',');
            sb.append("Long");
            sb.append(",");
            sb.append("20");
            sb.append('\n');

            sb.append("3");
            sb.append(',');
            sb.append("Hieu");
            sb.append(",");
            sb.append("1");
            sb.append('\n');
            writer.write(sb.toString());

            System.out.println("done!");

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
                int age = Integer.parseInt(data[2]);

                Human temp = new Human(id, name, age);
                humanList.add(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Human human : humanList) {
            System.out.println(human.toString());
        }
    }
}

class Human {
    private int id;
    private String name;
    private int age;

    Human(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
