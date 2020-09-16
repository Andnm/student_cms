package Parking;

public class Parking {
    private String filename = null;
    private int numOfSpots = 0;
    private int usedSpots = 0;
    private Menu mainmenu;
    private Menu submenu1;
    private boolean isClosed = false;

    public Parking() {
        filename = null;
        mainmenu = new Menu();
        submenu1 = new Menu();
        usedSpots = 0;
        numOfSpots = 0;
    }

    public Parking(final String filename, final int numOfSpots) {
        if (filename != null && numOfSpots > 0) {

        }
    }

    @Deprecated
    public Parking(final Parking other){}

    public final boolean isEmpty() {
        return filename == null;
    }

    public final void parkingStatus() {
        System.out.println(" ******* Available Spots: " + (numOfSpots - usedSpots));
    }

    public void parkVehicle() {

    }

    public void returnVehicle() {

    }

    public void listParkedVehicle() {

    }

    public boolean closeParking() {
        return true;
    }

    public boolean exitParkingApp() {
        return true;
    }

    public boolean loadDataFile() {
        return true;
    }

    public void saveDataFile() {

    }

    public void run(){

    }
}
