package service;

import dao.PeoplesDao;

public class PeoplesService {

    private PeoplesDao peoplesDao = new PeoplesDao();
    private int[] path;
    private int pathIndex;

    public void getArray() {
        peoplesDao.getArray();
    }

    public void relationship() {
        peoplesDao.relationship();
        numbersEqual();
    }

    public void numbersEqual() {

        //checking for identical pairs of connections
        boolean hasDuplicatePairs = false;
        int n = peoplesDao.friends.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (peoplesDao.friends[i][0] == peoplesDao.friends[j][0] && peoplesDao.friends[i][1] == peoplesDao.friends[j][1]) {
                    hasDuplicatePairs = true;
                }
            }
            if (hasDuplicatePairs) {
                peoplesDao.relationship();
            }
        }

        //checking for 2 identical numbers in an array string
        boolean numbersEqual = true;
        for (int i = 0; i < peoplesDao.friends.length; i++) {
            int num1 = peoplesDao.friends[i][0];
            int num2 = peoplesDao.friends[i][1];

            if (num1 != num2) {
                numbersEqual = false;
            } else {
                numbersEqual = true;
                if (num1 >= 0 && num1 < 9) {
                    peoplesDao.friends[i][0] = num1 + 1;
                } else {
                    peoplesDao.friends[i][0] = num1 - 1;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(peoplesDao.friends[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void path(String firstPerson, String secondPerson) {

        int index1 = getIndex(firstPerson);
        int index2 = getIndex(secondPerson);

        reset();
        boolean[] visited = new boolean[peoplesDao.peoples.length];
        visited[index1] = true;
        findPath(index1, index2, visited);

        if (pathIndex > 1) {
            System.out.println(firstPerson + " know " + secondPerson + " through:");
            for (int i = 0; i < pathIndex - 1; i++) {
                System.out.print(peoplesDao.peoples[path[i]] + " -> ");
            }
            System.out.println(secondPerson);
        } else {
            System.out.println("It is impossible to reach " + secondPerson + " through " + firstPerson);
        }
    }

    private void reset() {
        path = new int[peoplesDao.peoples.length];
        pathIndex = 0;
    }

    private void findPath(int currentIndex, int endIndex, boolean[] visited) {
        visited[currentIndex] = true;
        path[pathIndex] = currentIndex;
        pathIndex++;
        if (pathIndex > 6) {
            System.out.println("The path exceeded 6 handshakes.");
            System.exit(0);
        }

        if (currentIndex == endIndex) {
            return;
        }

        for (int[] friendship : peoplesDao.friends) {
            int person1Index = friendship[0];
            int person2Index = friendship[1];

            if (currentIndex == person1Index && !visited[person2Index]) {
                findPath(person2Index, endIndex, visited);
                if (path[pathIndex - 1] == endIndex) {
                    return;
                }
                pathIndex--;
            } else if (currentIndex == person2Index && !visited[person1Index]) {
                findPath(person1Index, endIndex, visited);
                if (path[pathIndex - 1] == endIndex) {
                    return;
                }
                pathIndex--;
            }
        }
    }

    private int getIndex(String Person) {
        for (int i = 0; i < peoplesDao.peoples.length; i++) {
            if (peoplesDao.peoples[i].equalsIgnoreCase(Person)) {
                return i;
            }
        }
        return -1;
    }
}

