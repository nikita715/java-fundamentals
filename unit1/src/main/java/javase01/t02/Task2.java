package javase01.t02;

import java.util.Scanner;

public class Task2 {
    private static double getA(int n) {
        return 1 / Math.pow(n + 1, 2);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter eps (from 0 to 1): "); //for instance, 0.003
        double eps = s.nextDouble();

        int i = 0;
        double a;
        while (true) {
            i++;
            a = getA(i);
            if (a < eps) {
                break;
            }
            System.out.printf("%.4f%n", a);
        }
        System.out.println(i - 1);
    }
}
