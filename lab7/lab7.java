import java.util.Scanner;
import java.util.stream.IntStream;

public class lab7 {
    // Перевірка, чи є число досконалим
    public static boolean isPerfect(int number) {
        int sumOfDivisors = IntStream.range(1, number)
                                     .filter(i -> number % i == 0)
                                     .sum();
        return sumOfDivisors == number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть n: ");
        int n = scanner.nextInt();

        System.out.println("Досконалі числа від 1 до " + n + ":");
        IntStream.rangeClosed(1, n)
                 .filter(lab7::isPerfect)
                 .forEach(System.out::println);
        
        scanner.close();
    }
}
