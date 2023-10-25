package utiill;

public class ReverseUtil {
    private ReverseUtil() {}
    public static String reverse (String str) {
        String[] strArray = str.split(" ");
        String result = "";
        for (int i = 0; i < strArray.length; i++) {
            String reverseStrArray = "";
            reverseStrArray = reverseStr(strArray[i]);
            result += reverseStrArray;
            if(i != strArray.length-1) {
                result += " ";
            }
        }
        return result;
    }

    private static String reverseStr(String strArray) {
        char[] charArray = strArray.toCharArray();
        String reverseStr = "";
        for (int i = charArray.length - 1; i >= 0 ; i--) {
            reverseStr += charArray[i];
        }
        return reverseStr;
    }

    public  static String reverseSubstring(String str, String dest) {
        String[] destArray = dest.split(" ");

        String startArray = "";
        String finishArray = "";
        String reverse = "";
        boolean check = str.contains(dest);

        if(check) {
            int lastIndex = 0;
            for (String word : destArray) {
                int index = str.indexOf(word, lastIndex);
                if (index >= 0) {
                    startArray += str.substring(lastIndex, index);
                    reverse += reverse(word) + " ";
                    lastIndex = index + word.length();
                }
            }
            finishArray = str.substring(lastIndex);
            return startArray.trim() + " ".concat(reverse.trim() + " ".concat(finishArray.trim()));
        }else {
            return null;
        }
    }

    public static String fromToChar(String str, String firstChar, String lastChar) {
        int startIndex = str.indexOf(firstChar);
        int finishIndex = str.indexOf(lastChar);
        if(startIndex == -1 || finishIndex == -1) {
            return null;
        }
        String startArray = str.substring(0, startIndex);
        String substring = str.substring(startIndex, finishIndex + 1);
        String finishArray = str.substring(finishIndex + 1, str.length());

        String reverse = reverse(substring);
        return startArray.concat(reverse.concat(finishArray));
    }

    public static String fromToIndex(String str, int firstIndex, int lastIndex) {
        if(lastIndex > str.length() - 1 || firstIndex < 0) {
            return null;
        }
        String startString = str.substring(0, firstIndex);
        String substring = str.substring(firstIndex, lastIndex + 1);
        String finishString = str.substring(lastIndex + 1, str.length());

        String reverse = reverse(substring);
        return startString.concat(reverse.concat(finishString));
    }

    public static String fromToWord(String str, String startWord, String finishWord) {
        int startIndex = str.indexOf(startWord);
        startIndex = startIndex + startWord.length();
        int finishIndex = str.indexOf(finishWord);
        if(startIndex == -1 || finishIndex == -1) {
            return null;
        }
        String startArray = str.substring(0, startIndex);
        String substring = str.substring(startIndex, finishIndex + 1);
        String finishArray = str.substring(finishIndex + 1, str.length());

        String reverse = reverse(substring);
        return startArray.concat(reverse.concat(finishArray));
    }
}