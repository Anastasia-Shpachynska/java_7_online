package org.example.HomeWork1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        //1. реализуйте задачу, которая принимает строку с консоли и вычленяет все числа и находит их сумму.

        System.out.println("Завдання 1");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть строку символів.\n");
        String str = scanner.nextLine();
        char[] mass = str.toCharArray();
        int sum = 0;

        for (int i = 0; i < mass.length; i++) {
            Boolean isDigit = Character.isDigit(mass[i]);
            if (isDigit) {
                int num = Character.getNumericValue(mass[i]);
                sum = sum + num;
            }
        }
        System.out.println(sum);
    }
}
