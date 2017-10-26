package javase01.t03;

import java.util.Scanner;

public class Task3 {
    private static double a = -0.4;
    private static double b = 5.8;
    private static double h = 0.4;

    private static double getF(double x) {
        return Math.tan(2 * x) - 3;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the left border: "); //for instance, -0.4
        double a = s.nextDouble();
        System.out.println("Enter the right border: "); //for instance, 5.8
        double b = s.nextDouble();
        System.out.println("Enter step: "); //for instance, 0.4
        double h = s.nextDouble();

        for (double x = a; x <= b; x += h) {
            System.out.printf("x = %.4f | F = %.4f%n", x, getF(x));
        }
    }
}
