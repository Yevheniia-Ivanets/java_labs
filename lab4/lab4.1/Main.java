//Var 2
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Scanner scanner = new Scanner(System.in);

        // Initialize cages
        zoo.addCage(new LionCage(3));
        zoo.addCage(new UngulateCage(5));
        zoo.addCage(new BirdCage(2));

        while (true) {
            System.out.println("\n=== Zoo Management System ===");
            System.out.println("1. Add Animal");
            System.out.println("2. Remove Animal");
            System.out.println("3. Show All Animals");
            System.out.println("4. Save to File");
            System.out.println("5. Load from File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    // case 1 -> addAnimal(zoo, scanner);
                    // case 2 -> removeAnimal(zoo, scanner);
                    case 3 -> displayAllAnimals(zoo);
                    case 4 -> saveZoo(zoo);
                    case 5 -> zoo = loadZoo();
                    case 6 -> {
                        System.out.println("Exiting... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // private static void addAnimal(Zoo zoo, Scanner scanner) throws Exception {
    //     System.out.print("Enter animal type (Lion, Zebra, Giraffe, Eagle): ");
    //     String type = scanner.nextLine().trim().toLowerCase();
    //     System.out.print("Enter animal name: ");
    //     String name = scanner.nextLine().trim();
    
    //     Animal animal = switch (type) {
    //         case "lion" -> new Lion(name);
    //         case "zebra" -> new Zebra(name);
    //         case "giraffe" -> new Giraffe(name);
    //         case "eagle" -> new Eagle(name);
    //         default -> throw new Exception("Invalid animal type.");
    //     };
    
    //     for (Cage<? extends Animal> cage : zoo.getCages()) {
    //         String cageName = cage.getClass().getSimpleName().toLowerCase();
    //         if (cageName.contains(type)) {
    //             cage.addAnimal(animal);
    //             System.out.println("Animal added!");
    //             return;
    //         }
    //     }
    //     throw new Exception("No suitable cage found.");
    // }
    

    // private static void removeAnimal(Zoo zoo, Scanner scanner) throws Exception {
    //     System.out.print("Enter animal type (Lion, Zebra, Giraffe, Eagle): ");
    //     String type = scanner.nextLine().trim();
    //     System.out.print("Enter animal name: ");
    //     String name = scanner.nextLine().trim();

    //     for (Cage<? extends Animal> cage : zoo.getCages()) {
    //         if (cage.getClass().getSimpleName().startsWith(type + "Cage")) {
    //             for (Animal animal : cage.getAnimals()) {
    //                 if (animal.getName().equalsIgnoreCase(name)) {
    //                     cage.removeAnimal(animal);
    //                     System.out.println("Animal removed!");
    //                     return;
    //                 }
    //             }
    //         }
    //     }
    //     throw new Exception("Animal not found.");
    // }

    private static void displayAllAnimals(Zoo zoo) {
        System.out.println("\n--- All Animals ---");
        for (Cage<? extends Animal> cage : zoo.getCages()) {
            System.out.println("Cage: " + cage.getClass().getSimpleName());
            for (Animal animal : cage.getAnimals()) {
                System.out.println(" - " + animal);
            }
        }
    }

    private static void saveZoo(Zoo zoo) throws Exception {
        zoo.saveToFile("zoo_data.ser");
        System.out.println("Zoo data saved!");
    }

    private static Zoo loadZoo() throws Exception {
        System.out.println("Loading zoo from file...");
        return Zoo.loadFromFile("zoo_data.ser");
    }
}
