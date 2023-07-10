package controller;

import entity.Cats;
import service.CatsService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CatsController {

    private CatsService catsService = new CatsService();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("Please, select your options:");
        menu();
        String select;
        for(select = bufferedReader.readLine(); select!= null; select = bufferedReader.readLine())
        {
            crud(bufferedReader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create cats, please enter 1");
        System.out.println("If you want update cats, please enter 2");
        System.out.println("If you want delete cats, please enter 3");
        System.out.println("If you want find cats, please enter 4");
        System.out.println("If you want find all cats, please enter 5");
        System.out.println("If you want close application, please enter 6");
    }

    private void crud(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> delete(bufferedReader);
            case "4" -> findOne(bufferedReader);
            case "5" -> findAll(bufferedReader);
            case "6" -> System.exit(0);
        }
        menu();
    }

    private void create(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter nickname");
        String Nickname = bufferedReader.readLine();
        System.out.println("Please enter spesies animal");
        String SpesiesAnimal = bufferedReader.readLine();
        System.out.println("Please enter age. Example: 1.6 (1 year 6 months). ");
        String ageString = bufferedReader.readLine();
        float age = Float.parseFloat(ageString);
        Cats cat = new Cats();
        cat.setNickname(Nickname);
        cat.setSpesiesAnimal(SpesiesAnimal);
        cat.setAge(age);
        catsService.create(cat);
    }

    private void update(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id cat");
        String id = bufferedReader.readLine();
        Cats cat = catsService.findOne(id);
        if (cat != null) {
            System.out.println("Nickname: " + cat.getNickname());
            System.out.println("Spesies animal: " + cat.getSpesiesAnimal());
            System.out.println("Age: " + cat.getAge());
            System.out.println(" ");
        } else {
            System.out.println("Cat not found");
        }
        System.out.println("If you want update nickname cat, please enter 1");
        System.out.println("If you want update spesies animal cat, please enter 2");
        System.out.println("If you want update age cat, please enter 3");
        System.out.println("If you want update all information about the cat, please enter 4");
        String update = bufferedReader.readLine();
        switch (update) {
            case "1" -> {
                System.out.println("Please enter a new nickname");
                String newNickname = bufferedReader.readLine();
                cat.setNickname(newNickname);
                System.out.println("Nickname has been successfully updated");
            }
            case "2" -> {
                System.out.println("Please enter a new spesies animal");
                String newSpesiesAnimal = bufferedReader.readLine();
                cat.setSpesiesAnimal(newSpesiesAnimal);
                System.out.println("Spesies animal has been successfully updated");
            }
            case "3" -> {
                System.out.println("Please enter a new age");
                String newAge = bufferedReader.readLine();
                cat.setAge(Float.parseFloat(newAge));
                System.out.println("Age has been successfully updated");
            }
            case "4" -> {
                System.out.println("Please enter a new nickname");
                String newNickname = bufferedReader.readLine();
                cat.setNickname(newNickname);
                System.out.println("Nickname has been successfully updated");
                System.out.println("Please enter a new spesiesAnimal");
                String newSpesiesAnimal = bufferedReader.readLine();
                cat.setSpesiesAnimal(newSpesiesAnimal);
                System.out.println("Spesies animal has been successfully updated");
                System.out.println("Please enter a new age");
                String newAge = bufferedReader.readLine();
                cat.setAge(Float.parseFloat(newAge));
                System.out.println("Age has been successfully updated");
            }
            default -> System.out.println("An error occurred. Please try again.");
        }
    }

    private void delete(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id cat");
        String id = bufferedReader.readLine();
        Cats cat = catsService.findOne(id);
        if (cat != null) {
            System.out.println("Nickname: " + cat.getNickname());
            System.out.println("Spesies animal: " + cat.getSpesiesAnimal());
            System.out.println("Age: " + cat.getAge());
            System.out.println(" ");
        } else {
            System.out.println("Cat not found");
        }
        System.out.println("Are you really running to remove the cat?");
        System.out.println("Yes. - Press 1");
        System.out.println("No. - Press 2");
        String delete = bufferedReader.readLine();
        switch (delete) {
            case "1" -> {
                CatsService.delete(id);
                System.out.println("Cat successfully deleted.");
            }
            case "2" -> System.out.println("All cats remained in place.");
            default -> System.out.println("Something went wrong. Please try again.");
        }
    }

    private void findOne(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id");
        String id = bufferedReader.readLine();
        Cats cat = catsService.findOne(id);
        if (cat != null) {
            System.out.println("Nickname: " + cat.getNickname());
            System.out.println("Spesies animal: " + cat.getSpesiesAnimal());
            System.out.println("Age: " + cat.getAge());
        } else {
            System.out.println("Cat not found");
        }
    }

    private void findAll(BufferedReader bufferedReader) {
        Cats[] cats = catsService.findAll();
        for (int i = 0; i < cats.length; i++) {
            Cats cat = cats[i];
            if (cat != null) {
                System.out.println("Id: " + cat.getId());
                System.out.println("Nickname: " + cat.getNickname());
                System.out.println("Spesies animal: " + cat.getSpesiesAnimal());
                System.out.println("Age: " + cat.getAge());
                System.out.println("");
            }
        }
    }
}
