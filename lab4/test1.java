import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;
    private LionCage lionCage;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        lionCage = new LionCage(5); // Вольєр для левів з максимальним лімітом 5
        zoo.addCage(lionCage);
    }

    @Test
    void testAddAnimalToCage() {
        Lion lion = new Lion();
        lionCage.addAnimal(lion);
        assertEquals(1, lionCage.getAnimals().size(), "Тварина не була додана до вольєра");
    }

    @Test
    void testRemoveAnimalFromCage() {
        Lion lion = new Lion();
        lionCage.addAnimal(lion);
        lionCage.removeAnimal(lion);
        assertEquals(0, lionCage.getAnimals().size(), "Тварина не була видалена з вольєра");
    }

    @Test
    void testRemoveNonExistentAnimal() {
        Lion lion = new Lion();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            lionCage.removeAnimal(lion);
        });
        assertEquals("Тварина Lion не знайдена у вольєрі!", exception.getMessage(), "Помилка при видаленні тварини");
    }

    @Test
    void testAddAnimalToFullCage() {
        Lion lion1 = new Lion();
        Lion lion2 = new Lion();
        lionCage.addAnimal(lion1);
        lionCage.addAnimal(lion2);

        // Додаємо ще тварину після досягнення максимальної ємності
        assertThrows(IllegalStateException.class, () -> {
            lionCage.addAnimal(new Lion());
        }, "Вольєр не повинен дозволяти додавати тварин після досягнення максимального ліміту");
    }
}
