import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeList {
    private List<Bike> bikeList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int bikeIDNumber = 1; //keep track of bike ID

    public static void main(String[] args){
        BikeList registry = new BikeList();
        registry.displayMainMenu();
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("*************");
            System.out.println("* Main Menu *");
            System.out.println("*************");
            System.out.println("1. List Bikes");
            System.out.println("2. Add a new Bike");
            System.out.println("3. Remove a Bike");
            System.out.println("4. Change Bike attributes");
            System.out.println("5. Debug: Dump objects (toString)");
            System.out.println("6. Exit");
            System.out.print(">");
            int choice=scanner.nextInt();
            while(choice>6 || choice<1){
                System.out.print("Invalid input. Enter a number between 1 and 6");
                System.out.println(">");
                choice=scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    displayAllBikes();
                    break;
                case 2:
                    addNewBike();
                    break;
                case 3:
                    deleteBike();
                    break;
                case 4:
                case 5:
                case 6:
                    System.out.println("BYE!");
                    return;
                default:
                    System.out.println("Invalid Input. Enter a number between 1 and 6");
            }
        }
    }

    private void displayAllBikes() {
        if (bikeList.isEmpty()) {
            System.out.println("No bikes registered");
        } else {
            System.out.println("******************");
            System.out.println("* List of Bikes: *");
            System.out.println("******************");
            System.out.printf("%-3s %-16s %-10s %-11s %-7s %-10s %n", "ID", "Owner,", "Type,", "Serial,", "Brake,", "Wheel Size");
            for (Bike bike : bikeList) {
                System.out.printf("%-3s %-16s %-10s %-11s %-7s %-10s%n",
                        bike.getBikeID(),
                        bike.getOwnerName() + ",",
                        bike.getType() + ",",
                        bike.getSerial() + ",",
                        bike.getBrake() + ",",
                        bike.getWheelSize());
            }
        }
    }

    private void addNewBike() {
        System.out.println("Enter Bike owner name:        ");
        String ownerName = scanner.next();
        System.out.println("Enter Bike type:              ");
        String type = scanner.next();
        System.out.println("Enter Bike's serial number:   ");
        String serial = scanner.next();
        System.out.println("Enter Bike's brake type:      ");
        String brake = scanner.next();
        System.out.println("Enter Bike's wheel size:      ");
        String wheelSize = scanner.next();

        Bike newBike = new Bike(bikeIDNumber++, ownerName, type, serial, brake, wheelSize);
        bikeList.add(newBike);


    }

    private void deleteBike() {
        if (bikeList.isEmpty()) {
            System.out.println("No bikes to delete.");
            return;
        }

        displayAllBikes();
        System.out.print("Enter ID (0 to cancel): ");
        int id = getValidIntInput(0, bikeIDNumber - 1);

        if (id == 0) {
            return;
        }

        Bike bikeToRemove = findBikeById(id);

        if (bikeToRemove != null) {
            bikeList.remove(bikeToRemove);
            System.out.println("Bike removed successfully.");
        } else {
            System.out.println("Bike ID not found. Please try again.");
            deleteBike();
        }
    }

    public void alterBike(){

    }




    //*******************Helper Functions*******************
    private Bike findBikeById(int id) {
        for (Bike bike : bikeList) {
            if (bike.getBikeID() == id) {
                return bike;
            }
        }
        return null;
    }

    private int getValidIntInput(int min, int max) {

        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
            }
        }
    }
}