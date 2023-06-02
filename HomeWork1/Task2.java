package org.example.HomeWork1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        // 2. Реализуйте задачу, которая принимает строку с консоли и вычленяет все символы латиницы/кириллицы и сортирует их,
        // указывая количество вхождений каждого символа

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
    }
}
