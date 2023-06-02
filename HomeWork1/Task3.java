package org.example.HomeWork1;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        // 3. В некоторой школе занятия начинаются в 9:00. Продолжительность урока — 45 минут, после 1-го, 3-го, 5-го и т.д. уроков перемена 5 минут, а после 2-го, 4-го, 6-го и т.д. — 15 минут.Определите, когда заканчивается указанный урок.

        System.out.println("Завдання 3");
        System.out.println("Введіть номер уроку.");
        Scanner scanner2 = new Scanner(System.in);
        int Lesson = scanner2.nextInt();
        Lesson = Lesson * 45 + (Lesson / 2) * 5 + ((Lesson + 1) / 2 - 1) * 15;
        System.out.println(Lesson / 60 + 9 + " " + Lesson % 60);
    }
}
