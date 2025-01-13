// використовуй gradle test 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZooAppTest Í{
    private Zoo zoo;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        zoo = new Zoo();ß
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddLion() {
        String input = "1\n1\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});

        assertEquals("Лева додано!\n", outContent.toString().split("\n")[2]);
    }

    @Test
    public void testAddZebra() {
        String input = "1\n2\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});

        assertEquals("Зебру додано!\n", outContent.toString().split("\n")[2]);
    }

    @Test
    public void testAddGiraffe() {
        String input = "1\n3\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});

        assertEquals("Жирафа додано!\n", outContent.toString().split("\n")[2]);
    }

    @Test
    public void testAddEagle() {
        String input = "1\n4\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});

        assertEquals("Орла додано!\n", outContent.toString().split("\n")[2]);
    }

    @Test
    public void testListAnimals() {
        zoo.addCage(new LionCage(5));
        zoo.addCage(new HoofedCage(5));
        zoo.addCage(new BirdCage(5));

        String input = "2\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});

        String expected = "Загальна кількість тварин: 0\nСписок тварин у кожному вольєрі:\n\n" +
                "Вольєр: LionCage\nВольєр порожній.\n\n" +
                "Вольєр: HoofedCage\nВольєр порожній.\n\n" +
                "Вольєр: BirdCage\nВольєр порожній.\n";
        assertEquals(expected, outContent.toString().split("\n", 5)[2]);
    }

    @Test
    public void testSaveAnimals() {
        String input = "3\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});
        assertEquals("Тварини збережені у файл.\n", outContent.toString().split("\n")[2]);
    }

    @Test
    public void testLoadAnimals() {
        String input = "4\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});
        assertEquals("Тварини завантажені з файлу.\n", outContent.toString().split("\n")[2]);
    }

    @Test
    public void testRemoveLion() {
        LionCage lionCage = new LionCage(5);
        lionCage.addAnimal(new Lion());
        zoo.addCage(lionCage);

        String input = "6\n1\n7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ZooApp.main(new String[]{});
        assertEquals("Лева видалено!\n", outContent.toString().split("\n")[2]);
    }
} 
