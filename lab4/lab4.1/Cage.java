import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Cage<T extends Animal> implements Serializable {
    private List<T> animals = new ArrayList<>();
    private int maxCapacity;

    public Cage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
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

