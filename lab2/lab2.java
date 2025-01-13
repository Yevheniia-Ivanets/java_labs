// 1. Напишіть консольну програму, яка дозволяє створювати сутність "Запис в журналі куратора".
// Для цього потрібно:
//  організувати введення даних з командної строки і передати результат введення у відповідну сутність;
//  перевіряти на правильність введення даних (зберігаючи правильно введені) і в разі повної коректності всіх даних – передати їх до
// відповідного класу в моделі; якщо дані не відповідають необхідному формату, то запропонувати повторне введення.
//  відображати всі записи журналу.
// Сутність "Запис в журналі куратора" описана наступним набором:
//  прізвище студента;
//  ім'я студента;
//  дата народження студента;
//  телефон студента;
//  домашня адреса (вулиця, будинок, квартира).

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lab2 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final List<JournalEntry> journalEntries = new ArrayList<>();

    // Main Menu
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Curator's Journal ===");
            System.out.println("1. Add Journal Entry");
            System.out.println("2. Display All Entries");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = readIntInput();
            switch (choice) {
                case 1 -> addJournalEntry();
                case 2 -> displayEntries();
                case 3 -> {
                    System.out.println("Goodbye my dear!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add a journal entry
    private static void addJournalEntry() {
        System.out.println("\n--- Add New Journal Entry ---");

        String lastName = readNonEmptyString("Enter student's last name: ");
        String firstName = readNonEmptyString("Enter student's first name: ");
        LocalDate dob = readDate("Enter date of birth (dd/MM/yyyy): ");
        String phoneNumber = readPhoneNumber("Enter student's phone number (+1234567890): ");
        String homeAddress = readAddress("Enter home address (street, house, apartment): ");

        JournalEntry entry = new JournalEntry(lastName, firstName, dob, phoneNumber, homeAddress);
        journalEntries.add(entry);

        System.out.println("Journal entry added successfully!");
    }

    // Display all entries
    private static void displayEntries() {
        System.out.println("\n--- Journal Entries ---");
        if (journalEntries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (JournalEntry entry : journalEntries) {
                System.out.println(entry);
                System.out.println("------------------------------");
            }
        }
    }

    // Input validation methods
    private static String readNonEmptyString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) break;
            System.out.println("Input cannot be empty. Please try again.");
        }
        return input;
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }
    }

    private static String readPhoneNumber(String prompt) {
        String phonePattern = "\\+\\d{10,15}";
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(phonePattern)) return input;
            System.out.println("Invalid phone number format. Use +1234567890 format.");
        }
    }

    private static int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }

    private static String readAddress(String prompt) {
        String addressPattern = "\\w+\\,\\d+\\,\\d+";
    
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
    
            if (input.matches(addressPattern)) {
                return input;
            } else {
                System.out.println("Invalid address format. Use format 'Street,House,Apartment'.");
            }
        }
    }
    
}

