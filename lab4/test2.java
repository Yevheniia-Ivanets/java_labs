import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ZooSerializationTest {

    private Zoo zoo;
    private LionCage lionCage;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        lionCage = new LionCage(5);
        zoo.addCage(lionCage);
    }

    @Test
    void testSaveToFile() {
        try {
            zoo.saveToFile("zoo_data.dat");
            File file = new File("zoo_data.dat");
            assertTrue(file.exists(), "Файл не був збережений");
        } catch (IOException e) {
            fail("Помилка збереження в файл: " + e.getMessage());
        }
    }

    @Test
    void testLoadFromFile() {
        try {
            zoo.saveToFile("zoo_data.dat");
            Zoo newZoo = new Zoo();
            newZoo.loadFromFile("zoo_data.dat");
            assertEquals(zoo.getCages().size(), newZoo.getCages().size(), "Кількість вольєрів після завантаження з файлу не співпадає");
        } catch (IOException | ClassNotFoundException e) {
            fail("Помилка завантаження з файлу: " + e.getMessage());
        }
    }

    @Test
    void testLoadFromNonExistentFile() {
        Zoo newZoo = new Zoo();
        Exception exception = assertThrows(IOException.class, () -> {
            newZoo.loadFromFile("non_existent_file.dat");
        });
        assertTrue(exception.getMessage().contains("не вдалося знайти файл"), "Повідомлення про помилку при завантаженні не правильне");
    }
}
