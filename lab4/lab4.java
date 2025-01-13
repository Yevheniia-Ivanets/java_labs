import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Base class for all animals
abstract class Animal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Specific animal classes
class Lion extends Animal {
    public Lion(String name) {
        super(name);
    }
}

class Zebra extends Animal {
    public Zebra(String name) {
        super(name);
    }
}

class Giraffe extends Animal {
    public Giraffe(String name) {
        super(name);
    }
}

class Eagle extends Animal {
    public Eagle(String name) {
        super(name);
    }
}

// Base class for cages
abstract class Cage<T extends Animal> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> animals;
    private int maxCapacity;

    public Cage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.animals = new ArrayList<>();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupiedPlaces() {
        return animals.size();
    }

    public void addAnimal(T animal) throws Exception {
        if (animals.size() >= maxCapacity) {
            throw new Exception("Cage is full!");
        }
        animals.add(animal);
    }

    public void removeAnimal(T animal) throws Exception {
        if (!animals.remove(animal)) {
            throw new Exception("Animal not found in the cage!");
        }
    }

    public List<T> getAnimals() {
        return animals;
    }
}

// Specific cage classes
class LionCage extends Cage<Lion> {
    public LionCage(int maxCapacity) {
        super(maxCapacity);
    }
}

class UngulateCage extends Cage<Animal> {
    public UngulateCage(int maxCapacity) {
        super(maxCapacity);
    }

    @Override
    public void addAnimal(Animal animal) throws Exception {
        if (!(animal instanceof Zebra || animal instanceof Giraffe)) {
            throw new Exception("Only zebras and giraffes can be added to this cage!");
        }
        super.addAnimal(animal);
    }
}

class BirdCage extends Cage<Eagle> {
    public BirdCage(int maxCapacity) {
        super(maxCapacity);
    }
}

// Zoo class
class Zoo {
    private List<Cage<? extends Animal>> cages = new ArrayList<>();

    public int getCountOfAnimals() {
        return cages.stream().mapToInt(Cage::getOccupiedPlaces).sum();
    }

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    public void saveAnimalsToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(cages);
        }
    }

    public void loadAnimalsFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            cages = (List<Cage<? extends Animal>>) ois.readObject();
        }
    }
}

// Unit tests
public class lab4 {
    public static void main(String[] args) {
        try {
            Zoo zoo = new Zoo();

            LionCage lionCage = new LionCage(2);
            lionCage.addAnimal(new Lion("Simba"));
            lionCage.addAnimal(new Lion("Mufasa"));

            UngulateCage ungulateCage = new UngulateCage(3);
            ungulateCage.addAnimal(new Zebra("Zara"));
            ungulateCage.addAnimal(new Giraffe("Gigi"));

            BirdCage birdCage = new BirdCage(2);
            birdCage.addAnimal(new Eagle("Eddie"));

            zoo.addCage(lionCage);
            zoo.addCage(ungulateCage);
            zoo.addCage(birdCage);

            System.out.println("Total animals in the zoo: " + zoo.getCountOfAnimals());

            // Save to file
            zoo.saveAnimalsToFile("zoo.dat");

            // Load from file
            Zoo newZoo = new Zoo();
            newZoo.loadAnimalsFromFile("zoo.dat");
            System.out.println("Total animals in the new zoo: " + newZoo.getCountOfAnimals());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}