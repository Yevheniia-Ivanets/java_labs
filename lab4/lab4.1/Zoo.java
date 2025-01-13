import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Zoo implements Serializable {
    private List<Cage<? extends Animal>> cages = new ArrayList<>();

    public int getCountOfAnimals() {
        return cages.stream().mapToInt(Cage::getOccupiedPlaces).sum();
    }
    
    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    public List<Cage<? extends Animal>> getCages() {
        return cages;
    }
    

    // Save to file
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    // Load from file
    public static Zoo loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Zoo) in.readObject();
        }
    }

}
