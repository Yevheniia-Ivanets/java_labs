// import java.util.ArrayList;
// import java.util.List;

// abstract class Cage<T extends Animal> {
//     private int maxCapacity;
//     private List<T> animals;

//     public Cage(int maxCapacity) {
//         this.maxCapacity = maxCapacity;
//         this.animals = new ArrayList<>();
//     }

//     public int getMaxCapacity() {
//         return maxCapacity;
//     }    

//     public int getOccupiedPlaces() {
//         return animals.size();
//     }

//     public void addAnimal(T animal) {
//         if (animals.size() >= maxCapacity) {
//             throw new IllegalStateException("Всі місця зайняті!");
//         }
//         animals.add(animal);
//     }

//     public void removeAnimal(Animal animal) {
//         boolean found = false;
//         for (Animal a : animals) {
//             if (a.equals(animal)) {
//                 animals.remove(a);
//                 found = true;
//                 break;
//             }
//         }
//         if (!found) {
//             // Якщо тварина не знайдена, ініціюємо виключення
//             throw new IllegalArgumentException("Тварина " + animal.getClass().getSimpleName() + " не знайдена у вольєрі!");
//         }
//     }
    


//     public List<T> getAnimals() {
//         return animals;
//     }

//     @Override
//     public String toString() {
//         return "Вольєр з " + animals;
//     }
// }

// // Конкретні вольєри
// class LionCage extends Cage<Lion> {
//     public LionCage(int maxCapacity) {
//         super(maxCapacity);
//     }
// }

// class HoofedCage extends Cage<Mammal> {
//     public HoofedCage(int maxCapacity) {
//         super(maxCapacity);
//     }
// }

// class BirdCage extends Cage<Bird> {
//     public BirdCage(int maxCapacity) {
//         super(maxCapacity);
//     }
// }
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract class Cage<T extends Animal> implements Serializable {  // Додано Serializable
    private int maxCapacity;
    private List<T> animals;

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

    public void addAnimal(T animal) {
        if (animals.size() >= maxCapacity) {
            throw new IllegalStateException("Всі місця зайняті!");
        }
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        boolean found = false;
        for (Animal a : animals) {
            if (a.equals(animal)) {
                animals.remove(a);
                found = true;
                break;
            }
        }
        if (!found) {
            // Якщо тварина не знайдена, ініціюємо виключення
            throw new IllegalArgumentException("Тварина " + animal.getClass().getSimpleName() + " не знайдена у вольєрі!");
        }
    }

    public List<T> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return "Вольєр з " + animals;
    }
}

// Конкретні вольєри
class LionCage extends Cage<Lion> {
    public LionCage(int maxCapacity) {
        super(maxCapacity);
    }
}

class HoofedCage extends Cage<Mammal> {
    public HoofedCage(int maxCapacity) {
        super(maxCapacity);
    }
}

class BirdCage extends Cage<Bird> {
    public BirdCage(int maxCapacity) {
        super(maxCapacity);
    }
}
