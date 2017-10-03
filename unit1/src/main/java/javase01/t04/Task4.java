package javase01.t04;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Task4 {
    private static double[] generateRandomArray(int length) {
        Random random = new Random();
        double a[] = new double[length];
        for (int i = 0; i < length; i++) {
            a[i] = random.nextDouble();
        }
        return a;
    }

    private static double findMaxSum(double[] a) {
        double maxSum = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < a.length / 2; i++) {
            maxSum = Math.max(maxSum, a[i] + a[a.length - i - 1]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter an even n: "); //for instance, 20
        int n = s.nextInt();
        if (n <= 0 || n % 2 != 0) {
            throw new InputMismatchException();
        }

        double a[] = generateRandomArray(n);
        System.out.println(findMaxSum(a));
    }
}
