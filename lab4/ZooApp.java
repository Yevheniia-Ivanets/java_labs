import java.io.IOException;
import java.util.Scanner;

public class ZooApp {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати тварину");
            System.out.println("2. Вивести список тварин");
            System.out.println("3. Зберегти тварин у файл");
            System.out.println("4. Завантажити тварин із файлу");
            System.out.println("5. Вивести загальну максимальну ємність");
            System.out.println("6. Видалити тварину");
            System.out.println("7. Вийти");

            System.out.print("Виберіть дію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("1. Лев\n2. Зебра\n3. Жираф\n4. Орел");
                    System.out.print("Виберіть тварину: ");
                    int animalChoice = scanner.nextInt();
                
                    try {
                        switch (animalChoice) {
                            case 1 -> {
                                LionCage lionCage = (LionCage) zoo.findCage(LionCage.class);
                                if (lionCage == null) {
                                    lionCage = new LionCage(5);  // Максимум 5 левів
                                    zoo.addCage(lionCage);
                                }
                                lionCage.addAnimal(new Lion());
                                System.out.println("Лева додано!");
                            }
                            case 2 -> {
                                HoofedCage hoofedCage = (HoofedCage) zoo.findCage(HoofedCage.class);
                                if (hoofedCage == null) {
                                    hoofedCage = new HoofedCage(5);  // Максимум 5 зебр/жирафів
                                    zoo.addCage(hoofedCage);
                                }
                                hoofedCage.addAnimal(new Zebra());
                                System.out.println("Зебру додано!");
                            }
                            case 3 -> {
                                HoofedCage hoofedCage = (HoofedCage) zoo.findCage(HoofedCage.class);
                                if (hoofedCage == null) {
                                    hoofedCage = new HoofedCage(5);
                                    zoo.addCage(hoofedCage);
                                }
                                hoofedCage.addAnimal(new Giraffe());
                                System.out.println("Жирафа додано!");
                            }
                            case 4 -> {
                                BirdCage birdCage = (BirdCage) zoo.findCage(BirdCage.class);
                                if (birdCage == null) {
                                    birdCage = new BirdCage(5);  // Максимум 5 орлів
                                    zoo.addCage(birdCage);
                                }
                                birdCage.addAnimal(new Eagle());
                                System.out.println("Орла додано!");
                            }
                            default -> System.out.println("Неправильний вибір!");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                }
                                
                // case 2 -> {
                //     int totalAnimals = zoo.getCages().stream()
                //             .mapToInt(cage -> cage.getAnimals().size())
                //             .sum();
                //     System.out.println("Загальна кількість тварин: " + totalAnimals);
                // }
                
                case 2 -> {
                    // Виведення загальної кількості тварин
                    int totalAnimals = zoo.getCages().stream()
                            .mapToInt(cage -> cage.getAnimals().size())
                            .sum();
                    System.out.println("Загальна кількість тварин: " + totalAnimals);
                
                    // Виведення списку тварин для кожного вольєра
                    System.out.println("Список тварин у кожному вольєрі:");
                    for (Cage cage : zoo.getCages()) {
                        System.out.println("\nВольєр: " + cage.getClass().getSimpleName());
                        
                        // Перевірка на порожність вольєра
                        if (cage.getAnimals().isEmpty()) {
                            System.out.println("Вольєр порожній.");
                        } else {
                            System.out.println("Тварини у вольєрі:");
                            
                            // Перебір тварин у вольєрі
                            for (Object animalObj : cage.getAnimals()) {
                                // Переведення Object до Animal
                                if (animalObj instanceof Animal) {
                                    Animal animal = (Animal) animalObj;
                                    System.out.println("- " + animal.getName());
                                }
                            }
                        }
                    }
                }
                
                case 3 -> {
                    try {
                        zoo.saveToFile("zoo_data.dat");
                        System.out.println("Тварини збережені у файл.");
                    } catch (IOException e) {
                        System.out.println("Помилка збереження: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        zoo.loadFromFile("zoo_data.dat");
                        System.out.println("Тварини завантажені з файлу.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Помилка завантаження: " + e.getMessage());
                    }
                }
                case 5 -> {
                    int totalCapacity = zoo.getCages().stream()
                                          .mapToInt(Cage::getMaxCapacity)
                                          .sum();
                    System.out.println("Загальна максимальна ємність: " + totalCapacity);
                }
                
                case 6 -> {
                    System.out.println("Оберіть тип тварини для видалення:");
                    System.out.println("1. Лев\n2. Зебра\n3. Жираф\n4. Орел");
                    System.out.print("Ваш вибір: ");
                    int animalChoice = scanner.nextInt();
                
                    try {
                        switch (animalChoice) {
                            case 1 -> {
                                LionCage lionCage = (LionCage) zoo.findCage(LionCage.class);
                                if (lionCage != null) {
                                    Lion lionToRemove = lionCage.getAnimals().stream()
                                        .filter(animal -> animal instanceof Lion && animal.getName().equals("Лев"))
                                        .map(animal -> (Lion) animal) // кастинг після перевірки типу
                                        .findFirst()
                                        .orElse(null);
                                    if (lionToRemove != null) {
                                        lionCage.removeAnimal(lionToRemove);
                                        System.out.println("Лева видалено!");
                                    } else {
                                        System.out.println("Лев не знайдений у вольєрі.");
                                    }
                                } else {
                                    System.out.println("Вольєр для левів порожній!");
                                }
                            }
                            case 2 -> {
                                HoofedCage hoofedCage = (HoofedCage) zoo.findCage(HoofedCage.class);
                                if (hoofedCage != null) {
                                    Zebra zebraToRemove = hoofedCage.getAnimals().stream()
                                        .filter(animal -> animal instanceof Zebra && animal.getName().equals("Зебра"))
                                        .map(animal -> (Zebra) animal) // кастинг після перевірки типу
                                        .findFirst()
                                        .orElse(null);
                                    if (zebraToRemove != null) {
                                        hoofedCage.removeAnimal(zebraToRemove);
                                        System.out.println("Зебру видалено!");
                                    } else {
                                        System.out.println("Зебру не знайдено у вольєрі.");
                                    }
                                } else {
                                    System.out.println("Вольєр для копитних порожній!");
                                }
                            }
                            case 3 -> {
                                HoofedCage hoofedCage = (HoofedCage) zoo.findCage(HoofedCage.class);
                                if (hoofedCage != null) {
                                    Giraffe giraffeToRemove = hoofedCage.getAnimals().stream()
                                        .filter(animal -> animal instanceof Giraffe && animal.getName().equals("Жираф"))
                                        .map(animal -> (Giraffe) animal) // кастинг після перевірки типу
                                        .findFirst()
                                        .orElse(null);
                                    if (giraffeToRemove != null) {
                                        hoofedCage.removeAnimal(giraffeToRemove);
                                        System.out.println("Жирафа видалено!");
                                    } else {
                                        System.out.println("Жирафа не знайдений у вольєрі.");
                                    }
                                } else {
                                    System.out.println("Вольєр для копитних порожній!");
                                }
                            }
                            case 4 -> {
                                BirdCage birdCage = (BirdCage) zoo.findCage(BirdCage.class);
                                if (birdCage != null) {
                                    Eagle eagleToRemove = birdCage.getAnimals().stream()
                                        .filter(animal -> animal instanceof Eagle && animal.getName().equals("Орел"))
                                        .map(animal -> (Eagle) animal) // кастинг після перевірки типу
                                        .findFirst()
                                        .orElse(null);
                                    if (eagleToRemove != null) {
                                        birdCage.removeAnimal(eagleToRemove);
                                        System.out.println("Орла видалено!");
                                    } else {
                                        System.out.println("Орел не знайдений у вольєрі.");
                                    }
                                } else {
                                    System.out.println("Вольєр для птахів порожній!");
                                }
                            }
                            default -> System.out.println("Неправильний вибір!");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                }                    
                
                case 7 -> {
                    System.out.println("Вихід...");
                    running = false;
                }
                default -> System.out.println("Неправильний вибір!");
            }
        }
        scanner.close();
    }
}
