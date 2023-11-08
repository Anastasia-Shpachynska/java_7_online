package service.Impl;

import service.GraphService;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphServiceImpl implements GraphService {

    GraphImpl graphImpl = new GraphImpl();

    @Override
    public void editView(String fileName) {
        try  {
            File file = new File(fileName);
            System.out.println("File -> " + file.getAbsolutePath());
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("The file has been created. Please enter 1 again to open it.");
            }else {
                Desktop.getDesktop().open(file);
            }
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void findPath(String fileName) {
        graphImpl.readFile(fileName);
    }
}
