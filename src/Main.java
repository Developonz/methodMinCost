import java.io.*;
import java.util.Scanner;

public class Main {
    public static int countRow;
    public static int countCol;
    public static int[] supplier;
    public static int[] consumer;
    public static double[][] payment;
    public static int[][] plan;
    public static double[] vector;

    public static void main(String[] args) {
        inpData();
        calcPlan();
        System.out.println();
        for (int i = 0; i < countRow; ++i) {
            for (int j = 0; j < countCol; ++j) {
                System.out.printf("%8d", plan[i][j]);
            }
            System.out.println();
        }
    }

    public static void calcPlan() {
        int g = 0;
        while (g < vector.length) {
            for (int i = 0; i < countRow; ++i) {
                for (int j = 0; j < countCol;++j) {
                    if (payment[i][j] == vector[g]) {
                        if (supplier[i] <= consumer[j]) {
                            plan[i][j] = supplier[i];
                            consumer[j] -= supplier[i];
                            supplier[i] = 0;
                        }
                        else {
                            plan[i][j] = consumer[j];
                            supplier[i] -= consumer[j];
                            consumer[j] = 0;
                        }
                        ++g;
                        if (g == vector.length) {
                            return ;
                        }
                    }
                }
            }
        }
    }
    public static void inpData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество поставщиков: ");
        countRow = scanner.nextInt();
        supplier = new int[countRow];
        System.out.print("Введите количество потребителей: ");
        countCol = scanner.nextInt();
        consumer = new int[countCol];
        int sumSup = 0;
        System.out.println("Введите запасы у постовщиков: ");
        for (int i = 0; i < countRow; ++i) {
            System.out.print("Запас поставщика " + (i + 1) + ": ");
            supplier[i] = scanner.nextInt();
            sumSup += supplier[i];
        }
        int sumCon = 0;
        System.out.println("\nВведите заказ потребителя: ");
        for (int i = 0; i < countCol; ++i) {
            System.out.print("Заказ потребителя " + (i + 1) + ": ");
            consumer[i] = scanner.nextInt();
            sumCon += consumer[i];
        }
        payment = new double[countRow][countCol];
        System.out.println("Введите затраты на перевозку: ");
        vector = new double[countRow * countCol];
        int g = 0;
        for (int i = 0; i < countRow; ++i) {
            for (int j = 0; j < countCol; ++j) {
                System.out.print("Затрата[" + (i + 1) + "][" + (j + 1) + "]: ");
                payment[i][j] = scanner.nextInt();
                vector[g++] = payment[i][j];
            }
        }
        sortVector();
        plan = new int[countRow][countCol];
    }
    public static void sortVector() {
        for (int i = 0; i < countRow * countCol; ++i) {
            for (int j = 0; j < countRow * countCol - i - 1; ++j) {
                if ((vector[j] > vector[j + 1] && vector[j + 1] != 0) || vector[j] == 0) {
                    double tmp = vector[j + 1];
                    vector[j + 1] = vector[j];
                    vector[j] = tmp;
                }
            }
        }
    }
}