import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;
    private Cage<Lion> lionCage;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        lionCage = new Cage<>(5); // максимальна місткість 5
        zoo.addCage(lionCage);
    }

    // Тест для додавання тварин у вольєр
    @Test
    void testAddAnimalToCage() {
        Lion lion = new Lion();
        boolean added = lionCage.addAnimal(lion);
        assertTrue(added, "Лев не був доданий до вольєра");
        assertEquals(1, lionCage.getAnimals().size(), "Невірна кількість тварин у вольєрі");
    }

    // Тест для додавання тварини в переповнений вольєр
    @Test
    void testAddAnimalToFullCage() {
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());

        assertThrows(IllegalStateException.class, () -> lionCage.addAnimal(new Lion()), 
            "Вольєр не повинен дозволяти додавати тварин після досягнення максимального ліміту");
    }

    // Тест для видалення тварини з вольєра
    @Test
    void testRemoveAnimalFromCage() {
        Lion lion = new Lion();
        lionCage.addAnimal(lion);
        boolean removed = lionCage.removeAnimal(lion);
        assertTrue(removed, "Тварина не була видалена з вольєра");
        assertEquals(0, lionCage.getAnimals().size(), "Невірна кількість тварин після видалення");
    }

    // Тест для видалення неіснуючої тварини
    @Test
    void testRemoveNonExistentAnimal() {
        Lion lion = new Lion();
        boolean removed = lionCage.removeAnimal(lion);
        assertFalse(removed, "Тварина не повинна бути видалена, оскільки її немає в вольєрі");
    }

    // Тест для перевірки загальної кількості тварин у зоопарку
    @Test
    void testGetCountOfAnimals() {
        Lion lion = new Lion();
        lionCage.addAnimal(lion);
        assertEquals(1, zoo.getCountOfAnimals(), "Невірна загальна кількість тварин у зоопарку");

        // Додаємо ще одну тварину
        lionCage.addAnimal(new Lion());
        assertEquals(2, zoo.getCountOfAnimals(), "Невірна загальна кількість тварин після додавання");
    }

    // Тест для перевірки пошуку вольєра за типом тварини
    @Test
    void testFindCage() {
        Lion lion = new Lion();
        lionCage.addAnimal(lion);

        Cage<? extends Animal> foundCage = zoo.findCage(Lion.class);
        assertNotNull(foundCage, "Вольєр для левів не знайдений");

        // Перевіряємо, що знайдений вольєр містить тварину
        assertEquals(1, foundCage.getAnimals().size(), "Не знайдено тварин в знайденому вольєрі");
    }
}
