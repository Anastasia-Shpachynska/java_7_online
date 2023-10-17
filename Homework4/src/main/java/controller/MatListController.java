package controller;

import service.MatList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MatListController {
    private MatList matList = new MatList<>();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! Please, select your option:" + '\n');
        menu();
        String select;
        for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
            inputMenu(bufferedReader, select);
        }
    }

    private void menu() {
        System.out.println("Add element - 1");
        System.out.println("Add n elements - 2");
        System.out.println("Join MatLists - 3");
        System.out.println("Intersection MatLists - 4");
        System.out.println("Sort desc all elements - 5");
        System.out.println("Sort desc from '' to '' - 6");
        System.out.println("Sort desc from '' - 7");
        System.out.println("Sort asc all elements- 8");
        System.out.println("Sort asc from '' to '' - 9");
        System.out.println("Sort asc from '' - 10");
        System.out.println("Get by index- 11");
        System.out.println("Get max - 12");
        System.out.println("Get min - 13");
        System.out.println("Get average - 14");
        System.out.println("Get median - 15");
        System.out.println("View all array - 16");
        System.out.println("View array from '' to '' - 17");
        System.out.println("Cut elements from '' to '' - 18");
        System.out.println("Clear all elements - 19");
        System.out.println("Clear select elements- 20");
    }

    private void inputMenu(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> add(bufferedReader);
            case "2" -> addElements(bufferedReader);
            case "3" -> joinMatLists(bufferedReader);
            case "4" -> intersection(bufferedReader);
            case "5" -> sortDescAll();
            case "6" -> sortDescFromTo(bufferedReader);
            case "7" -> sortDescValue(bufferedReader);
            case "8" -> sortAscAll();
            case "9" -> sortAscFromTo(bufferedReader);
            case "10" -> sortAscValue(bufferedReader);
            case "11" -> getIndex(bufferedReader);
            case "12" -> getMax();
            case "13" -> getMin();
            case "14" -> getAverage();
            case "15" -> getMedian();
            case "16" -> toArray();
            case "17" -> toArrayFromTo(bufferedReader);
            case "18" -> cut(bufferedReader);
            case "19" -> clear(bufferedReader);
            case "20" -> clearNumbers(bufferedReader);
            default -> System.out.println("Please, select option with menu!");
        }
    }

    private void add(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter numeric:");
        String numericStr = bufferedReader.readLine();
        try {
            int numeric = Integer.parseInt(numericStr);
            matList.add(numeric);
            System.out.println("Numeric added!");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a numeric value.");
            add(bufferedReader);
        }
    }

    private void addElements(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter numerics through a space:");
        String[] numericsStr = bufferedReader.readLine().split(" ");
        try {
            for (String numericStr : numericsStr) {
                int numeric = Integer.parseInt(numericStr);
                matList.addAll(numeric);
            }
            System.out.println("Numerics added!");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a numeric value.");
            addElements(bufferedReader);
        }
    }

    private void joinMatLists(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the number of MatLists to join:");
            int numList = Integer.parseInt(bufferedReader.readLine());

            if (numList <= 0) {
                System.out.println("Invalid input. The number of MatLists should be greater than 0.");
                joinMatLists(bufferedReader);
                return;
            }

            MatList[] matLists = new MatList[numList];

            for (int i = 0; i < numList; i++) {
                System.out.println("Enter MatList " + (i + 1) + ":");
                System.out.println("Please, enter numbers separated by spaces:");
                String numberStr = bufferedReader.readLine();
                String[] numberStrArray = numberStr.split(" ");

                Integer[] numbers = new Integer[numberStrArray.length];
                for (int j = 0; j < numberStrArray.length; j++) {
                    numbers[j] = Integer.parseInt(numberStrArray[j]);
                }
                matLists[i] = new MatList<>(numbers);
            }
            matList.join(matLists);
            System.out.println("MatLists joined successfully.");

        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid number of MatLists.");
        }
    }



    private void intersection(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the number of MatLists to join:");
            int numList = Integer.parseInt(bufferedReader.readLine());

            if (numList <= 0) {
                System.out.println("Invalid input. The number of MatLists should be greater than 0.");
                joinMatLists(bufferedReader);
                return;
            }

            MatList[] matLists = new MatList[numList];

            for (int i = 0; i < numList; i++) {
                System.out.println("Enter MatList " + (i + 1) + ":");
                System.out.println("Please, enter numbers separated by spaces:");
                String numberStr = bufferedReader.readLine();
                String[] numberStrArray = numberStr.split(" ");

                Integer[] numbers = new Integer[numberStrArray.length];
                for (int j = 0; j < numberStrArray.length; j++) {
                    numbers[j] = Integer.parseInt(numberStrArray[j]);
                }
                matLists[i] = new MatList<>(numbers);
            }
            matList.intersection(matLists);
            System.out.println("MatLists joined successfully.");

        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid number of MatLists.");
        }
    }

    private void sortDescAll() {
        System.out.println("Sort result:");
        matList.sortDesc();
    }

    private void sortDescFromTo(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the starting index:");
            int start = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Please, enter the finishing index:");
            int finish = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Sort result:");
            matList.sortDesc(start, finish);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid indices.");
            sortDescFromTo(bufferedReader);
        }
    }

    private void sortDescValue(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the starting value:");
            int start = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Sort result:");
            matList.sortDesc(start);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid value.");
            sortDescValue(bufferedReader);
        }
    }

    private void sortAscAll() {
        System.out.println("Sort result:");
        matList.sortAsc();
    }

    private void sortAscFromTo(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the starting index:");
            int start = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Please, enter the finishing index:");
            int finish = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Sort result:");
            matList.sortAsc(start, finish);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid indices.");
            sortAscFromTo(bufferedReader);
        }
    }

    private void sortAscValue(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the starting value:");
            int start = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Sort result:");
            matList.sortAsc(start);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid value.");
            sortAscValue(bufferedReader);
        }
    }

    private  void getIndex(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the index:");
            int index = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Element by index " + index + ":");
            matList.get(index);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid index.");
            getIndex(bufferedReader);
        }
    }

    private void getMax() {
        System.out.println("Max value:");
        matList.getMax();
    }

    public void getMin() {
        System.out.println("Min value:");
        matList.getMin();
    }

    private void getAverage() {
        System.out.println("Average value:");
        matList.getAverage();
    }

    private void getMedian() {
        System.out.println("Median:");
        matList.getMedian();
    }

    private void toArray() {
        System.out.println("Array:");
        matList.toArray();
    }

    private void toArrayFromTo(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter start index:");
            int start = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please, enter finish index:");
            int finish = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Array from " + start + " to " + finish + ":");
            matList.toArray(start, finish);
        }catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid indices.");
            toArrayFromTo(bufferedReader);
        }
    }

    private void cut(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter start index:");
            int start = Integer.parseInt(bufferedReader.readLine());
            System.out.println("Please, enter finish index:");
            int finish = Integer.parseInt(bufferedReader.readLine());

            System.out.println("Array after cut:");
            matList.cut(start, finish);
        }catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid indices.");
            cut(bufferedReader);
        }
    }

    private void clear(BufferedReader bufferedReader) throws IOException {
        System.out.println("This function will remove all items. Are you sure you want to continue?");
        System.out.println("Yes - 1");
        System.out.println("No - 2");
        String answer = bufferedReader.readLine();
        switch (answer) {
            case "1" -> matList.clear();
            case "2" -> menu();
            default -> System.out.println("No such option exists!");
        }
    }

    private void clearNumbers(BufferedReader bufferedReader) throws IOException {
        try {
            System.out.println("Please, enter the numbers you would like to delete:");
            String numbersStr = bufferedReader.readLine();
            String[] numbersStrArray = numbersStr.split(" ");
            Number[] numbers = new Number [numbersStrArray.length];

            for (int i = 0; i < numbersStrArray.length; i++) {
                numbers[i] = Integer.parseInt(numbersStrArray[i]);
            }
            matList.clear(numbers);
        }catch (NumberFormatException ex) {
            System.out.println("Please, enter numbers!");
            clearNumbers(bufferedReader);
        }
    }
}
