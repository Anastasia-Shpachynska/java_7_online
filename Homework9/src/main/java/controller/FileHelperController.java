package controller;

import service.Impl.FileHelperServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileHelperController {
    FileHelperServiceImpl fileHelperServiceImpl = new FileHelperServiceImpl();

    public void start() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String select;
            for(select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
                select(select, bufferedReader);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("A list of all files and folders in the directory - 1");
        System.out.println("Create a new file - 2");
        System.out.println("Create a new folder - 3");
        System.out.println("Delete a file - 4");
        System.out.println("Delete a folder - 5");
        System.out.println("Move a file or folder - 6");
        System.out.println("Search for a file or folder - 7");
        System.out.println("Search for text in all files and folders - 8");
        System.out.println("Exit the program - 9");
    }

    private void select(String select, BufferedReader bufferedReader) throws IOException {
        switch (select) {
            case "1" -> allFiles(bufferedReader);
            case "2" -> createFile(bufferedReader);
            case "3" -> createFolder(bufferedReader);
            case "4" -> deleteFile(bufferedReader);
            case "5" -> deleteFolder(bufferedReader);
            case "6" ->move(bufferedReader);
            case "7" -> searchFile(bufferedReader);
            case "8" -> searchText(bufferedReader);
            case "9" -> System.exit(0);
            default -> System.out.println("Incorrect option. Please, select from the proposed menu!");
        }
    }

    private void allFiles(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        List<String> str = fileHelperServiceImpl.getAllFiles(fullDirectory, 0);
        if(str.isEmpty()) {
            System.out.println("Nothing found in this folder.");
        }
        for (String s : str) {
            System.out.println(s);
        }
    }

    private void createFile(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        System.out.println("Enter the name: ");
        String name = notEmpty(String.valueOf(bufferedReader.readLine()));
        File file = new File(fullDirectory);
        if(file.isDirectory()) {
            File[] filesInDirectory = file.listFiles();
            if (filesInDirectory != null) {
                for (File existingFile : filesInDirectory) {
                    if (existingFile.getName().equals(name)) {
                        System.out.println("A file or folder with the same name already exists in the directory.");
                    }
                }
            }
            boolean result = fileHelperServiceImpl.createFile(fullDirectory + File.separator + name);
            if (result) {
                System.out.println("File " + name + " has been created.");
            }else {
                System.out.println("Unable to create the file.");
            }
        }else {
            System.out.println("File cannot be created in file.");
        }
    }

    private void createFolder(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        System.out.println("Enter the name: ");
        String name = notEmpty(String.valueOf(bufferedReader.readLine()));
        File file = new File(fullDirectory);
        if(file.isDirectory()) {
            File[] filesInDirectory = file.listFiles();
            if (filesInDirectory != null) {
                for (File existingFile : filesInDirectory) {
                    if (existingFile.getName().equals(name)) {
                        System.out.println("A file or folder with the same name already exists in the directory.");
                    }
                }
            }
            boolean result = fileHelperServiceImpl.createFolder(fullDirectory + File.separator + name);
            if (result) {
                System.out.println("Folder " + name + " has been created.");
            }else {
                System.out.println("Unable to create the folder.");
            }
        }else {
            System.out.println("Folder cannot be created in file.");
        }
    }

    private void deleteFile(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        System.out.println("Enter the name: ");
        String name = notEmpty(String.valueOf(bufferedReader.readLine()));
        boolean result = fileHelperServiceImpl.deleteFile(fullDirectory + File.separator + name);
        if(result) {
            System.out.println("File " + name + " has been deleted.");
        }else{
            System.out.println("Unable to delete the file. Check if such a file actually exists.");
        }
    }

    private void deleteFolder(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        System.out.println("Enter the name: ");
        String name = notEmpty(String.valueOf(bufferedReader.readLine()));
        boolean result = fileHelperServiceImpl.deleteFolder(fullDirectory + File.separator + name);
        if(result) {
            System.out.println("Folder " + name + " has been deleted.");
        }else{
            System.out.println("Unable to delete the folder. Check if such a folder actually exists.");
        }
    }

    private void move(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the path to the folder/file: ");
        String oldPath = notEmpty( bufferedReader.readLine());
        System.out.println("Enter the path where you want to move the folder/file: ");
        String newPath = bufferedReader.readLine();
        boolean result = fileHelperServiceImpl.move(oldPath, newPath);
        if(result) {
            System.out.println("Folder/file has been moved successfully.");
        }else {
            System.out.println("Failed to move the folder/file, check the correctness of the data.");
        }
    }

    private void searchFile(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        System.out.println("Enter the name: ");
        String name = notEmpty(String.valueOf(bufferedReader.readLine()));
        File file = fileHelperServiceImpl.searchFile(fullDirectory + File.separator, name);
        if(file != null) {
            System.out.println(file);
        }else {
            System.out.println("The specified file was not found.");
        }
    }

    private void searchText(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the directory (each folder is separated by a character '/'): ");
        String fullDirectory = bufferedReader.readLine();
        System.out.println("Enter the search text: ");
        String text = notEmpty(String.valueOf(bufferedReader.readLine()));
        List<File> result = fileHelperServiceImpl.searchText(fullDirectory, text);

        if (result.isEmpty()) {
            System.out.println("No files found with the specified text.");
        } else {
            System.out.println("Files found:");
            for (File file : result) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    private String notEmpty(String input) {
        if(input.isEmpty()) {
            System.out.println("Field cannot be empty");
            start();
        }
        return input;
    }
}
