package service;

import java.util.*;

public class MatList<E extends Number & Comparable<E>> {

    private List<E> numbers;

    public MatList() {
        this.numbers = new ArrayList<>();
    }

    ;

    @SafeVarargs
    public MatList(E[]... numbers) {
        this.numbers = new ArrayList<>();
        for (E[] arr : numbers) {
            this.numbers.addAll(Arrays.asList(arr));
        }
    }

    @SafeVarargs
    public MatList(MatList<E>... lists) {
        this.numbers = new ArrayList<>();
        for (MatList<E> list : lists) {
            this.numbers.addAll(list.numbers);
        }
    }

    public void add(E n) {
        numbers.add(n);
    }

    @SafeVarargs
    public final void addAll(E... n) {
        numbers.addAll(Arrays.asList(n));
    }

    public void join(MatList... ml) {
        for (MatList<E> list : ml) {
            this.numbers.addAll(list.numbers);
        }
    }



    public void intersection(MatList... ml) {
        for (MatList<E> list : ml) {
            this.numbers.retainAll(list.numbers);
        }
    }

    public void sortDesc() {
        if(numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
        }
        Collections.sort(numbers, Collections.reverseOrder());
        for (E element : numbers) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || firstIndex >= numbers.size() || lastIndex < 0 || lastIndex >= numbers.size() || firstIndex > lastIndex) {
            System.out.println("Incorrect index!");
            return;
        }

        List<E> tempList = new ArrayList<>(numbers.subList(firstIndex, lastIndex + 1));
        tempList.sort(Collections.reverseOrder());

        for (E n : tempList) {
            System.out.print(n + "\t");
        }
        System.out.println();
    }

    public void sortDesc(E value) {
     int startNum = numbers.indexOf(value);

     if (startNum == -1 || startNum > numbers.size()) {
         System.out.println("Incorrect value!");
         return;
     }

     List<E> tempList = new ArrayList<>(numbers.subList(startNum, numbers.size()));
     tempList.sort(Collections.reverseOrder());

        for (E n : tempList) {
            System.out.print(n + "\t");
        }
        System.out.println();
    }

    public void sortAsc() {
        if(numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
        }
        Collections.sort(numbers);
        for (E element : numbers) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if(firstIndex < 0 || firstIndex >= numbers.size() || lastIndex < 0 || lastIndex >= numbers.size() || firstIndex > lastIndex) {
            System.out.println("Incorrect index!");
            return;
        }
        List<E> tempList = new ArrayList<>(numbers.subList(firstIndex, lastIndex + 1));
        tempList.sort(Comparator.naturalOrder());

        for (E n : tempList) {
            System.out.print(n + "\t");
        }
        System.out.println();
    }

    public void sortAsc(E value) {
        int startNum = numbers.indexOf(value);

        if (startNum == -1 || startNum > numbers.size()) {
            System.out.println("Incorrect value!");
            return;
        }

        List<E> tempList = new ArrayList<>(numbers.subList(startNum, numbers.size()));
        tempList.sort(Comparator.naturalOrder());

        for (E n : tempList) {
            System.out.print(n + "\t");
        }
        System.out.println();
    }

    public Number get(int index) {
        if (index >= 0 && index < numbers.size()) {
            Number result = numbers.get(index);
            System.out.println(result);
            return result;
        } else {
            System.out.println("Incorrect index!");
            return null;
        }
    }

    public Number getMax() {
        if (numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
            return null;
        }
        Number max = Collections.max(numbers);
        System.out.println(max);
        return max;
    }

    public Number getMin() {
        if (numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
            return null;
        }
        Number min = Collections.min(numbers);
        System.out.println(min);
        return min;
    }

    public Number getAverage() {
        if (numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
            return null;
        }
        double sum = 0;
        for (E number : numbers) {
          sum = sum + number.intValue();
        }
        double average = sum / numbers.size();
        System.out.println(average);
        return average;
    }

    public Number getMedian() {
        if (numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
            return null;
        }
        Collections.sort(numbers);
        int middle = numbers.size() / 2;
        if (numbers.size() % 2 == 1) {
            E result = numbers.get(middle);
            System.out.println(result);
            return result;
        } else {
            double result = (numbers.get(middle - 1).doubleValue() + numbers.get(middle).doubleValue()) / 2;
            System.out.println(result);
            return result;
        }
    }

    public Number[] toArray() {
        if (numbers.size() == 0) {
            System.out.println("You haven't added any items yet!");
            return null;
        }
        System.out.println(Arrays.toString(numbers.toArray(new Number[0])));
        return numbers.toArray(new Number[0]);
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || firstIndex >= numbers.size() || lastIndex < 0 || lastIndex >= numbers.size() || firstIndex > lastIndex) {
            System.out.println("Incorrect index!");
            return null;
        }
        List<E> tempList = new ArrayList<>(numbers.subList(firstIndex, lastIndex + 1));
        System.out.println(Arrays.toString(tempList.toArray(new Number[0])));
        return tempList.toArray(new Number[0]);
    }

    public List<E> cut(int firstIndex, int lastIndex) {
        if (firstIndex < 0 || firstIndex >= numbers.size() || lastIndex < 0 || lastIndex >= numbers.size() || firstIndex > lastIndex) {
            System.out.println("Incorrect index!");
            return null;
        }
        numbers.subList(firstIndex, lastIndex + 1).clear();
        System.out.println(numbers);
        return numbers;
    }

    public void clear() {
        numbers.clear();
    }

    public void clear(Number[] number) {
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (number[i].equals(numbers.get(j))) {
                    numbers.remove(j);
                    j--;
                }
            }
        }
        System.out.println(numbers);
    }
}

