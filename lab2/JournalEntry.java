import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JournalEntry {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String homeAddress;

    // Конструктор
    public JournalEntry(String lastName, String firstName, LocalDate dateOfBirth, String phoneNumber, String homeAddress) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
    }

    // Перевизначення методу toString для виводу
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
                "Студент: %s %s\nДата народження: %s\nТелефон: %s\nАдреса: %s\n",
                firstName, lastName, dateOfBirth.format(formatter), phoneNumber, homeAddress
        );
    }
}
