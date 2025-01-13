import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        // Додаємо вольєри через загальний метод addCage
        zoo.addCage(new LionCage(5));
        zoo.addCage(new HoofedCage(5));
        zoo.addCage(new BirdCage(5));
    }

    @Test
    void testAddAnimalToCage() {
        // Додаємо тварин через загальний доступ до вольєрів через метод findCage
        Lion lion = new Lion();
        Cage<? extends Animal> lionCage = zoo.findCage(LionCage.class);
        lionCage.addAnimal(lion);
        assertEquals(1, lionCage.getAnimals().size(), "Лев не був доданий до вольєра");

        Zebra zebra = new Zebra();
        Cage<? extends Animal> hoofedCage = zoo.findCage(HoofedCage.class);
        hoofedCage.addAnimal(zebra);
        assertEquals(1, hoofedCage.getAnimals().size(), "Зебра не була додана до вольєра");

        Eagle eagle = new Eagle();
        Cage<? extends Animal> birdCage = zoo.findCage(BirdCage.class);
        birdCage.addAnimal(eagle);
        assertEquals(1, birdCage.getAnimals().size(), "Орел не був доданий до вольєра");
    }

    @Test
    void testRemoveAnimalFromCage() {
        Lion lion = new Lion();
        Cage<? extends Animal> lionCage = zoo.findCage(LionCage.class);
        lionCage.addAnimal(lion);
        lionCage.removeAnimal(lion);
        assertEquals(0, lionCage.getAnimals().size(), "Лев не був видалений з вольєра");
    }

    @Test
    void testAddAnimalToFullCage() {
        // Заповнюємо вольєр левами до максимального ліміту
        Cage<? extends Animal> lionCage = zoo.findCage(LionCage.class);
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());
        lionCage.addAnimal(new Lion());

        // Додаємо ще одну тварину в повний вольєр
        assertThrows(IllegalStateException.class, () -> lionCage.addAnimal(new Lion()), "Вольєр не повинен дозволяти додавати тварин після досягнення максимального ліміту");
    }

    @Test
    void testRemoveNonExistentAnimal() {
        Lion lion = new Lion();
        Cage<? extends Animal> lionCage = zoo.findCage(LionCage.class);
        assertThrows(IllegalArgumentException.class, () -> lionCage.removeAnimal(lion), "Тварина не знайдена у вольєрі!");
    }

    @Test
    void testFindCage() {
        // Пошук вольєрів через метод findCage()
        Cage<? extends Animal> foundCage = zoo.findCage(LionCage.class);
        assertNotNull(foundCage, "Вольєр для левів не знайдений");

        foundCage = zoo.findCage(HoofedCage.class);
        assertNotNull(foundCage, "Вольєр для копитних не знайдений");

        foundCage = zoo.findCage(BirdCage.class);
        assertNotNull(foundCage, "Вольєр для птахів не знайдений");
    }

    @Test
    void testGetCountOfAnimals() {
        Lion lion = new Lion();
        zoo.findCage(LionCage.class).addAnimal(lion);

        Zebra zebra = new Zebra();
        zoo.findCage(HoofedCage.class).addAnimal(zebra);

        int totalAnimals = zoo.getCountOfAnimals();
        assertEquals(2, totalAnimals, "Невірна загальна кількість тварин у зоопарку");
    }

    @Test
    void testGetMaxCapacity() {
        int totalCapacity = zoo.getMaxCapacity();
        assertEquals(15, totalCapacity, "Невірна загальна максимальна ємність");
    }

    @Test
    void testSaveAndLoadZoo() {
        try {
            zoo.saveToFile("zoo_data.dat");
            Zoo loadedZoo = new Zoo();
            loadedZoo.loadFromFile("zoo_data.dat");

            assertEquals(zoo.getCages().size(), loadedZoo.getCages().size(), "Не вдалося правильно завантажити дані з зоопарку");
        } catch (Exception e) {
            fail("Помилка при збереженні чи завантаженні зоопарку: " + e.getMessage());
        }
    }
}
