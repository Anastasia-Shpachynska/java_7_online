package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
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

        // 2. Реализуйте задачу, которая принимает строку с консоли и вычленяет все символы латиницы/кириллицы и сортирует их, указывая количество вхождений каждого символа

        System.out.println("Завдання 2");
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Введіть строку символів.\n");
        String str1 = scanner1.nextLine();
        char[] chars = str1.toCharArray();

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : str1.toCharArray()) {
            if (Character.isLetter(ch)) {
                char lowercaseCh = Character.toLowerCase(ch);
                charCountMap.put(lowercaseCh, charCountMap.getOrDefault(lowercaseCh, 0) + 1);
            }
        }

        System.out.println("Кількість входжень кожної літери:");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        // 3. В некоторой школе занятия начинаются в 9:00. Продолжительность урока — 45 минут, после 1-го, 3-го, 5-го и т.д. уроков перемена 5 минут, а после 2-го, 4-го, 6-го и т.д. — 15 минут.Определите, когда заканчивается указанный урок.

        System.out.println("Завдання 3");
        System.out.println("Введіть номер уроку.");
        Scanner scanner2 = new Scanner(System.in);
        int Lesson = scanner2.nextInt();
        Lesson = Lesson * 45 + (Lesson / 2) * 5 + ((Lesson + 1) / 2 - 1) * 15;
        System.out.println(Lesson / 60 + 9 + " " + Lesson % 60);
    }
}
