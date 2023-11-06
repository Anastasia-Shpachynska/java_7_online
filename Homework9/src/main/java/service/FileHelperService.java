package service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileHelperService {
    List<String> getAllFiles(String directory, int depth);
    boolean createFile(String directory) throws IOException;
    boolean createFolder(String directory);
    boolean  deleteFile(String directory);
    boolean deleteFolder(String directory);
    boolean move(String fromDirectory, String toDirectory) throws IOException;
    File searchFile(String directory, String name);
    List<File> searchText(String directory, String text);
}
