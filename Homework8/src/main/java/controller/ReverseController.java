package controller;

import utiill.ReverseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseController {
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String select;
            for (select = bufferedReader.readLine(); select != null; select = bufferedReader.readLine()) {
                select(select, bufferedReader);
            }
    }

    private void menu() {
        System.out.println("\n" + "Please, select option:");
        System.out.println("String reverse - 1");
        System.out.println("Substring reverse - 2");
        System.out.println("Reverse from ' ' to ' ' - 3");
        System.out.println("End the program - 4");
    }

    private void select(String select, BufferedReader bufferedReader) throws IOException {
        switch (select) {
            case "1" -> stringReverse(bufferedReader);
            case "2" -> substringReverse(bufferedReader);
            case "3" -> fromToReverse(bufferedReader);
            case "4" -> System.exit(0);
            default -> System.out.println("Incorrect option. Please, select from the proposed menu!");
        }
    }


    private void stringReverse(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the string:");
        String str = bufferedReader.readLine();
        if(!str.isEmpty()) {
            String result = ReverseUtil.reverse(str);
            System.out.println("Input string: " + str);
            System.out.println("Reverse string: " + result);
        }else {
            System.out.println("String not can be empty!");
            stringReverse(bufferedReader);
        }
    }

    private void substringReverse(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the string:");
        String str = bufferedReader.readLine();
        System.out.println("Please, enter the substring:");
        String dest = bufferedReader.readLine();
        if(!str.isEmpty() && !dest.isEmpty()) {
            String result = ReverseUtil.reverseSubstring(str, dest);
            if(result == null) {
                System.out.println("No such substring was found in the string you entered. Please check the correctness of the data and try again.");
                substringReverse(bufferedReader);
            }
            System.out.println("Input string: " + str);
            System.out.println("Reverse string: " + result);
        }else {
            System.out.println("String and substring not can be empty!");
            substringReverse(bufferedReader);
        }
    }

    private void fromToReverse(BufferedReader bufferedReader) throws IOException {
        menuFromToReverse();
        String select = bufferedReader.readLine();

        switch (select) {
            case "1" -> {
                System.out.println("Please, enter the string:");
                String str = bufferedReader.readLine();
                if(str.isEmpty()) {
                    System.out.println("String can not be empty!");
                    fromToReverse(bufferedReader);
                }
                System.out.println("Please, enter the starting symbol:");
                String firstSym = bufferedReader.readLine();
                System.out.println("Please, enter the finishing symbol:");
                String lastSym = bufferedReader.readLine();
                if(!firstSym.isEmpty() && !lastSym.isEmpty()) {
                    String result = ReverseUtil.fromToChar(str, firstSym, lastSym);
                    if (result != null) {
                        System.out.println("Input string: " + str);
                        System.out.println("Reverse string: " + result);
                    } else {
                        System.out.println("Make sure the entered characters exist and try again!");
                        fromToReverse(bufferedReader);
                    }
                }else {
                    System.out.println("The symbols can not be empty!");
                    fromToReverse(bufferedReader);
                }

            }
            case "2" -> {
                System.out.println("Please, enter the string:");
                String str = bufferedReader.readLine();
                if (str.isEmpty()) {
                    System.out.println("String can not be empty!");
                    fromToReverse(bufferedReader);
                }
                System.out.println("Please, enter the starting index:");
                String firstIndexStr = bufferedReader.readLine();
                System.out.println("Please, enter the finishing index:");
                String lastIndexStr = bufferedReader.readLine();
                if (!firstIndexStr.isEmpty() && !lastIndexStr.isEmpty()) {
                    try {
                        int firstIndex = Integer.parseInt(firstIndexStr);
                        int lastIndex = Integer.parseInt(lastIndexStr);
                        String result = ReverseUtil.fromToIndex(str, firstIndex, lastIndex);
                        if (result != null) {
                            System.out.println("Input string: " + str);
                            System.out.println("Reverse string: " + result);
                        } else {
                            System.out.println("Non-existent indexes entered!");
                        }
                    } catch (Exception ex) {
                        System.out.println("Check the correctness of the entered data. The index must be an integer!");
                    }
                } else {
                    System.out.println("The index can not be empty!");
                }
            }
            case "3" -> {
                System.out.println("Please, enter the string:");
                String str = bufferedReader.readLine();
                if(str.isEmpty()) {
                    System.out.println("String can not be empty!");
                    fromToReverse(bufferedReader);
                }
                System.out.println("Please, enter the starting word:");
                String firstWord = bufferedReader.readLine();
                System.out.println("Please, enter the finishing word:");
                String lastWord = bufferedReader.readLine();
                if(!firstWord.isEmpty() && !lastWord.isEmpty()) {
                    String result = ReverseUtil.fromToWord(str, firstWord, lastWord);
                    if(result != null) {
                        System.out.println("Input string: " + str);
                        System.out.println("Reverse string: " + result);
                    }else {
                        System.out.println("Make sure the entered words exist and try again!");
                        fromToReverse(bufferedReader);
                    }
                }else {
                    System.out.println("The words can not be empty!");
                    fromToReverse(bufferedReader);
                }
            }
            case "4" -> start();
            default -> System.out.println("Incorrect option. Please, select from the proposed menu!");
        }
    }

    private void menuFromToReverse() {
        System.out.println("\n" + "Please, select option:");
        System.out.println("Reverse from ' ' to ' ' symbol - 1");
        System.out.println("Reverse from ' ' to ' ' index - 2");
        System.out.println("Reverse from ' ' to ' ' word - 3");
        System.out.println("Return to the main menu - 4");
    }
}
