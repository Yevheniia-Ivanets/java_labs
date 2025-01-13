// 7. Серед простих чисел, які не перевищують заданий n, знайти таке, в двійковій формі
// якого максимальна кількість нулів. Просте число – це натуральне число, яке ділиться на 1
// та на себе.
import java.util.Scanner;

public class lab1 {

    // перевірка чи просте число
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Розрахунок кількість нулів
    public static int countZerosInBinary(int num) {
        String binary = Integer.toBinaryString(num);
        int count = 0;
        for (char bit : binary.toCharArray()) {
            if (bit == '0') count++;
        }
        return count;
    }

    // Знаходження простого числа з максимальними нулями у двійковій формі
    public static int primeWithMaxBinaryZeros(int n) {
        int maxZeros = -1;
        int primeWithMaxZeros = -1;

        // Число з 2 до н
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                int zeroCount = countZerosInBinary(i);
                // Якщо це просте число має більше всього нулів у двійковій формі
                if (zeroCount > maxZeros) {
                    maxZeros = zeroCount;
                    primeWithMaxZeros = i;
                }
            }
        }
        return primeWithMaxZeros;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number n: ");
        int n = scanner.nextInt();

        int result = primeWithMaxBinaryZeros(n);
        if (result == -1) {
            System.out.println("No primes found in the range.");
        } else {
            System.out.println("The prime with the maximum number of zeros in binary form is: " + result);
        }

        scanner.close();
    }
}