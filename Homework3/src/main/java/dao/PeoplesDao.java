package dao;


import java.util.Random;

public class PeoplesDao {

    public String[] peoples = {"Anna", "Olena", "Oleksandr", "Andriy", "Anastasia", "Nikita", "Ivan", "Svetlana", "Oleg", "Nazar"};
     public int[][] friends = new int[10][2];
    Random random = new Random();

    public void getArray() {
        for (int i = 0; i < peoples.length; i++)
        {
            System.out.print(peoples[i] + '\n');
        }
        System.out.println();
    }

    //build random relationship
    public void relationship() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                friends[i][j] = random.nextInt(10);
            }
        }
    }
}
