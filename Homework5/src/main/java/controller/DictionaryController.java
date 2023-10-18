package controller;

import service.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DictionaryController<K, V> {

    Dictionary<K, V> dictionary = new Dictionary<>();
    Dictionary dictionaryService = new Dictionary<>();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please, select your option:");
        menu();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            select(bufferedReader, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Size - 1");
        System.out.println("Is empty - 2");
        System.out.println("Contains key - 3");
        System.out.println("Contains value - 4");
        System.out.println("Get key - 5");
        System.out.println("Put - 6");
        System.out.println("Remove - 7");
        System.out.println("Put all - 8");
        System.out.println("Clear - 9");
        System.out.println("Key set - 10");
        System.out.println("Values - 11");
    }

    private void select(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> size();
            case "2" -> isEmpty();
            case "3" -> containsKey(bufferedReader);
            case "4" -> containsValue(bufferedReader);
            case "5" -> get(bufferedReader);
            case "6" -> put(bufferedReader);
            case "7" -> remove(bufferedReader);
            case "8" -> putAll(bufferedReader);
            case "9" -> clear();
            case "10" -> keySet();
            case "11" -> values();
        }
    }

    private void size() {
        System.out.println("Size is " + dictionaryService.size());
    }

    private void isEmpty() {
        if(dictionaryService.isEmpty()) {
            System.out.println("Dictionary is empty.");
        }else {
            System.out.println("Dictionary is not empty.");
        }
    }

    private void containsKey(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the key you want to find:");
        K key = (K) bufferedReader.readLine();
        if (key == null) {
            System.out.println("Please, enter the key.");
            containsKey(bufferedReader);
        }
        if (dictionaryService.containsKey(key)) {
            System.out.println("The key is found in the dictionary.");
        }else {
            System.out.println("The key was not found in the dictionary.");
        }
    }

    private void containsValue(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the value you want to find:");
        V value = (V) bufferedReader.readLine();
        if (value == null) {
            System.out.println("Please, enter the value.");
            containsValue(bufferedReader);
        }
        if (dictionaryService.containsValue(value)) {
            System.out.println("The value is found in the dictionary.");
        }else {
            System.out.println("The value was not found in the dictionary.");
        }
    }

    private void get(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the key:");
        K key = (K) bufferedReader.readLine();
        V value = (V) dictionaryService.get(key);
        if (key == null) {
            System.out.println("Please, enter the key.");
            get(bufferedReader);
        }
       if (value == null) {
           System.out.println("The key not found!");
       }else {
           System.out.println("Value:");
           System.out.println(value);
       }
    }

    private void put(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the key:");
        K key = (K) bufferedReader.readLine();
        System.out.println("Please, enter the value:");
        V value = (V) bufferedReader.readLine();
        if (key == null || value == null) {
            System.out.println("Key and value must not be empty.");
            put(bufferedReader);
        }
        Boolean bool = dictionaryService.put(key, value);
        if (bool) {
            System.out.println("Key and value added.");
        }else {
            System.out.println("Value is updated.");
        }
    }

    private void remove(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the key:");
        K key = (K) bufferedReader.readLine();
        if (key == null) {
            System.out.println("Please, enter the key.");
            remove(bufferedReader);
        }
        if (dictionaryService.remove(key)) {
            System.out.println("Element successfully delete.");
        }else {
            System.out.println("The key nor found.");
        }
    }


    private void putAll(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter key and value by format 'key value; key value; ...':");
        String input = bufferedReader.readLine();
        String[] keyValuePairs = input.split("; ");

        Dictionary<K, V> dictionaryToAdd = new Dictionary<>();

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(" ");
            if (keyValue.length == 2) {
                K key = (K) keyValue[0];
                V value = (V) keyValue[1];

                dictionaryToAdd.put(key, value);
            } else {
                System.out.println("Invalid input format.");
            }
        }

        Boolean bool = dictionaryService.putAll(dictionaryToAdd);
        if (bool) {
            System.out.println("Elements successfully added.");
        }
    }

    private void clear() {
        if (dictionaryService.clear()) {
            System.out.println("Dictionary is successfully clear.");
        }
    }

    private void keySet() {
        System.out.println("Unique keys:");
        System.out.println(dictionaryService.keySet());
    }

    private void values() {
        System.out.println("All values:");
        System.out.println(dictionaryService.values());
    }
}
