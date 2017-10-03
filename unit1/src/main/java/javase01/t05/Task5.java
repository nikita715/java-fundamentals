package javase01.t05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task5 {
    private static int[][] generateMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - i - 1] = 1;
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int matrix_line[]: matrix) {
            for (int cell: matrix_line) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter size: "); //for instance, 10

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        if (size <= 0) {
            throw new InputMismatchException();
        }

        printMatrix(generateMatrix(size));
    }
}
