package Parking;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Parking.Utils.getInRanger;

public class Menu {
    private String title = null;
    private List<MenuItem> menuItems = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public Menu() {
        this.title = null;
    }

    public Menu(String title) {
        this.title = title;
    }

    public boolean isEmpty() {
        return title == null;
    }

    public void add(String content) {
        if (!isEmpty()) {
            MenuItem temp = new MenuItem(content);
            menuItems.add(temp);
        } else {
            System.out.println("The Menu is not exists");
        }
    }

    public int run() throws ParseException {
        int selection = 0;
        if (!isEmpty()) {
            System.out.println("*** " + title + " ***");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i).getContent());
            }
            System.out.print("Enter: ");
            selection = getInRanger(menuItems.size(), 1);
        } else {
            System.out.println("No Menu!");
        }
        return selection;
    }
}

class MenuItem {
    private String content;

    public MenuItem(String item) {
        content = item;
    }

    public String getContent() {
        return content;
    }
}
