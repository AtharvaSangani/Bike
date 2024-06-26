/*
 **************************
 * Topic: SFU Bike Registry
 * Description: A bike parking registry which stores all the information about bikes that are currently
              registered to SFU parking. Bikes are distinguished by their unique bike ID in the system.
 * Author: Atharva Sangani
 * Student No.: 301549083
 * Date last modified :30 May 2024
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeList {
    private List<Bike> bikeList = new ArrayList<>();//store all the bikes with attributes
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
            while(choice>6 || choice<1){//getting a valid input
                System.out.println("Invalid input. Enter a number between 1 and 6");
                System.out.print(">");
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
                    alterBike();
                    break;
                case 5:
                    displayBikesToString();
                    break;
                case 6:
                    System.out.println("BYE!");
                    return;
                default:
                    System.out.println("Invalid Input. Enter a number between 1 and 6");
            }
        }
    }

    //Description: Display all bikes in registry
    //Precondition: registry is not empty
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

    //Description: Add a new bike to the registry
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

    //Description: Delete a bike from the registry
    //Precondition: registry is not empty
    private void deleteBike() {
        if (bikeList.isEmpty()) {
            System.out.println("No bikes to delete.");
            return;
        }

        displayAllBikes();
        System.out.print("Enter ID (0 to cancel): ");
        int id = scanner.nextInt();
        while(id>bikeIDNumber-1 || id<0){//getting a valid input
            System.out.println("Invalid input. Enter a number between 0 and "+(bikeIDNumber-1));
            System.out.print(">");
            id=scanner.nextInt();
        }

        if (id == 0) {
            return;
        }

        Bike bikeToRemove = findBikeById(id);

        while (bikeToRemove == null) {
            System.out.println("Bike ID not found. Please try again.");
            id = scanner.nextInt();
            while(id>bikeIDNumber-1 || id<0){//getting a valid input
                System.out.println("Invalid input. Enter a number between 1 and "+(bikeIDNumber-1));
                System.out.print(">");
                id=scanner.nextInt();
            }
            bikeToRemove = findBikeById(id);
        }

        if (id!=0){
            bikeList.remove(bikeToRemove);
            System.out.println("Bike removed successfully.");
        }
        else
            return;

    }

    //Description: Alter a bike attribute.
    //Precondition: registry is not empty
    public void alterBike(){
        if (bikeList.isEmpty()){
            System.out.println("No bikes registered");
            return;
        }//return if the registry is empty
        displayAllBikes();

        System.out.println("Enter ID (0 to cancel)");
        System.out.print(">");

        int id = scanner.nextInt();
        while(id>bikeIDNumber-1 || id<0){//getting a valid input
            System.out.println("Invalid input. Enter a number between 0 and "+(bikeIDNumber-1));
            System.out.print(">");
            id=scanner.nextInt();
        }

        if (id == 0) {
            return;
        }
        Bike bikeToUpdate = findBikeById(id);

        while (bikeToUpdate == null) {
            System.out.println("Bike ID not found. Please try again.");
            id = scanner.nextInt();
            while(id>bikeIDNumber-1 || id<0){//getting a valid input
                System.out.println("Invalid input. Enter a number between 1 and "+(bikeIDNumber-1));
                System.out.print(">");
                id=scanner.nextInt();
            }
            bikeToUpdate = findBikeById(id);
        }

        String attribute = "";//variable to store the attribute input
        boolean validInput = false;//variable to validate the input

        while (!validInput) {//validates input if it is either owner, type, serial, brake, or wheel size.
            System.out.println("Which Attribute? ");
            System.out.print(">");
            attribute = scanner.nextLine().trim().toLowerCase();

            switch (attribute) {
                case "owner":
                case "type":
                case "serial":
                case "brake":
                case "wheel size":
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        switch (attribute){

            case "owner":
                System.out.println("New Value");
                System.out.print(">");
                bikeToUpdate.setOwnerName(scanner.next());
                System.out.println("Saved!");
                break;

            case "type":
                System.out.println("New Value");
                System.out.print(">");
                bikeToUpdate.setType(scanner.next());
                System.out.println("Saved!");
                break;

            case "serial":
                System.out.println("New Value");
                System.out.print(">");
                bikeToUpdate.setSerial(scanner.next());
                System.out.println("Saved!");
                break;

            case "brake":
                System.out.println("New Value");
                System.out.print(">");
                bikeToUpdate.setBrake(scanner.next());
                System.out.println("Saved!");
                break;

            case "wheel size":
                System.out.println("New Value");
                System.out.print(">");
                bikeToUpdate.setWheelSize(scanner.next());
                System.out.println("Saved!");
                break;
        }
    }

    //Description: Display Bike objects as strings
    //Precondition: registry is not empty.
    private void displayBikesToString() {
        if (bikeList.isEmpty()) {
            System.out.println("No bikes registered.");
        } else {
            for (Bike bike : bikeList) {
                System.out.println(bike.toString());
            }
        }
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
}