import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Zoo implements Serializable {  // Додано Serializable
    private List<Cage<? extends Animal>> cages = new ArrayList<>();

    // Повернення списку вольєрів
    public List<Cage<? extends Animal>> getCages() {
        return cages;
    }

    public int getCountOfAnimals() {
        return cages.stream()
                .mapToInt(c -> c.getAnimals().size())
                .sum();
    }

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    public <T extends Cage<? extends Animal>> T findCage(Class<T> cageType) {
        for (Cage<? extends Animal> cage : cages) {
            if (cageType.isInstance(cage)) {
                return cageType.cast(cage);
            }
        }
        return null;
    }

    // Метод для збереження в файл
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);  // Зберігаємо весь об'єкт Zoo
        }
    }

    // Метод для завантаження з файлу
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Zoo zoo = (Zoo) in.readObject();  // Завантажуємо об'єкт Zoo
            this.cages = zoo.getCages();  // Оновлюємо поточні дані
        }
    }

    public int getMaxCapacity() {
        return cages.stream()
                    .mapToInt(Cage::getMaxCapacity)
                    .sum();
    }
}
