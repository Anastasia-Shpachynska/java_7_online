package service.Impl;

import service.FileHelperService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelperServiceImpl implements FileHelperService {
    @Override
    public List<String> getAllFiles(String directory, int depth) {
        File folder = new File(directory);
        File[] fileList = folder.listFiles();
        List<String> str = new ArrayList<>();

        if (fileList != null) {
            for (File file : fileList) {
                String formattedName = getIndentation(depth) + file.getName();
                str.add(formattedName);
                if (file.isDirectory()) {
                    List<String> subList = getAllFiles(file.getAbsolutePath(), depth + 1);
                    str.addAll(subList);
                }
            }
        }
        return  str;
    }

    @Override
    public boolean createFile(String directory) {
        File file = new File(directory);
        boolean newFile = false;
        try {
            newFile = file.createNewFile();
        }catch (IOException ex) {
            System.out.println("Error creating the file: " + ex.getMessage());
        }
        return newFile;
    }

    @Override
    public boolean createFolder(String directory) {
        File folder = new File(directory);
        boolean newFolder = folder.mkdir();
        return newFolder;
    }

    @Override
    public boolean deleteFile(String directory) {
        File file = new File(directory);

        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteFolder(String directory) {
        File folder = new File(directory);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }
            if (folder.delete()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(String fromDirectory, String toDirectory) {
        File oldPath = new File(fromDirectory);
        try {
            oldPath.renameTo(new File(toDirectory + File.separator + oldPath.getName()));
            return true;
        }catch (RuntimeException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public File searchFile(String directory, String name) {
        File folder = new File(directory);
        if (!folder.exists()) {
            return null;
        }

        if (folder.getName().equals(name)) {
            return folder;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    File result = searchFile(file.getAbsolutePath(), name);
                    if (result != null) {
                        return result;
                    }
                }
                if(file.isFile() && file.getName().equals(name)) {
                    return new File(name, file.getAbsolutePath());
                }
            }
        }
        return null;
    }

    @Override
    public List<File> searchText(String directory, String text) {
        List<File> result = new ArrayList<>();
        File folder = new File(directory);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    result.addAll(searchText(file.getAbsolutePath(), text));
                } else {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.contains(text)) {
                                result.add(file);
                                break;
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }
        }
        return result;
    }

    public static String getIndentation(int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("   ");
        }
        return indentation.toString();
    }
}