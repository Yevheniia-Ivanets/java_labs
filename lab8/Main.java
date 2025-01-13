import java.util.concurrent.ForkJoinPool;  // Імпортуємо пул потоків ForkJoin
import java.util.concurrent.RecursiveTask; // Імпортуємо RecursiveTask для створення завдань
import java.util.Random;                   // Імпортуємо Random для генерації випадкових чисел

public class Main {

    // Клас завдання для обчислення суми підмасиву
    static class SumTask extends RecursiveTask<Long> {
        private final int[] array; // Масив для обчислення
        private final int start, end; // Початок і кінець поточного підмасиву
        private static final int THRESHOLD = 20; // Граничний розмір підмасиву для прямого обчислення

        // Конструктор для ініціалізації завдання
        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        // Метод, що обчислює суму елементів підмасиву
        @Override
        protected Long compute() {
            int length = end - start; // Довжина поточного підмасиву

            // Якщо довжина менша за поріг, обчислюємо суму безпосередньо
            if (length <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                // Ділимо масив на дві частини
                int mid = start + length / 2;
                
                // Створюємо підзавдання для обох частин
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);

                // Виконуємо ліве завдання асинхронно
                leftTask.fork(); 

                // Виконуємо праве завдання синхронно і отримуємо його результат
                long rightResult = rightTask.compute(); 

                // Очікуємо завершення лівого завдання і отримуємо його результат
                long leftResult = leftTask.join(); 

                // Повертаємо суму результатів обох підзавдань
                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[1_000_000]; // Ініціалізація масиву на 1 000 000 елементів
        Random random = new Random(); // Створення об'єкта для генерації випадкових чисел

        // Заповнюємо масив випадковими значеннями від 0 до 100
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        // Створюємо пул потоків ForkJoin
        ForkJoinPool pool = new ForkJoinPool(); 
        
        // Створюємо завдання для обчислення суми масиву
        SumTask task = new SumTask(array, 0, array.length);

        // Вимірюємо час виконання
        long startTime = System.currentTimeMillis(); 
        
        // Запускаємо обчислення
        long sum = pool.invoke(task); 
        
        long endTime = System.currentTimeMillis(); 

        // Виводимо результат
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
