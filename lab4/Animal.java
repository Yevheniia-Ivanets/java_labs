// // Базовий клас Тварина
// abstract class Animal {
//     private String name;

//     public Animal(String name) {
//         this.name = name;
//     }

//     public String getName() {
//         return name;
//     }

//     @Override
//     public String toString() {
//         return name;
//     }
// }

// // Ссавці та птахи
// abstract class Mammal extends Animal {
//     public Mammal(String name) {
//         super(name);
//     }
// }

// abstract class Bird extends Animal {
//     public Bird(String name) {
//         super(name);
//     }
// }

// // Конкретні тварини
// class Lion extends Mammal {
//     public Lion() {
//         super("Лев");
//     }
// }

// class Zebra extends Mammal {
//     public Zebra() {
//         super("Зебра");
//     }
// }

// class Giraffe extends Mammal {
//     public Giraffe() {
//         super("Жираф");
//     }
// }

// class Eagle extends Bird {
//     public Eagle() {
//         super("Орел");
//     }
// }
import java.io.Serializable;

// Базовий клас Тварина
abstract class Animal implements Serializable {  // Додано Serializable
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Ссавці та птахи
abstract class Mammal extends Animal {
    public Mammal(String name) {
        super(name);
    }
}

abstract class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }
}

// Конкретні тварини
class Lion extends Mammal {
    public Lion() {
        super("Лев");
    }
}

class Zebra extends Mammal {
    public Zebra() {
        super("Зебра");
    }
}

class Giraffe extends Mammal {
    public Giraffe() {
        super("Жираф");
    }
}

class Eagle extends Bird {
    public Eagle() {
        super("Орел");
    }
}
