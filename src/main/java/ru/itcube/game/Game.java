package ru.itcube.game;

import java.util.Scanner;

public class Game {
    private int nextTurn;
    private int[][] field = new int[3][3];

    public Game() {
        nextTurn = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = 0;
            }
        }
    }

    public boolean user1Turn() {
        return userTurn(1);
    }

    public boolean user2Turn() {
        return userTurn(2);
    }

    private boolean userTurn(int user) {
        Scanner in = new Scanner(System.in);
        String coords = in.nextLine();
        if (coords.matches("\\d,\\d")) {
            int x = Integer.valueOf(coords.split(",")[0]);
            int y = Integer.valueOf(coords.split(",")[1]);
            if (x < 3 && x >= 0 && y < 3 && y >= 0) {
                if (field[x][y] != 0) {
                    System.out.println("Вы ввели неверные координаты");
                    return false;
                } else {
                    field[x][y] = user;
                }
            } else {
                System.out.println("Вы ввели неверные координаты");
                return false;
            }
        } else {
            System.out.println("Вы ввели неверные координаты");
            return false;
        }
        return true;
    }

    public int whoNext() {
        return nextTurn;
    }

    public void changeNext(int next) {
        nextTurn = next;
    }


    private boolean userWon(int user) {
        for (int i = 0; i < 3; i++) {
            if (all(user, field[i])) return true;
        }
        for (int i = 0; i < 3; i++) {
            int[] a = new int[3];
            for (int j = 0; j < 3; j++) {
                a[j] = field[j][i];
            }
            if (all(user, a)) return true;
        }

        int[] diag1 = new int[3];
        for (int i = 0; i < 3; i++) {
            diag1[i] = field[i][i];
        }
        if (all(user, diag1)) return true;

        int[] diag2 = new int[3];
        for (int i = 0; i < 3; i++) {
            diag2[i] = field[i][3 - i - 1];
        }
        if (all(user, diag2)) return true;

        return false;
    }

    public boolean user1Won() {
        return userWon(1);
    }

    public boolean user2Won() {
        return userWon(2);
    }

    private boolean all(int pattern, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != pattern) return false;
        }
        return true;
    }

    public boolean noWinner() {
        return noEmpty() && !user1Won() && !user2Won();
    }

    private boolean noEmpty() {
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) return false;
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + format(field[i][j]));
            }
            System.out.println();
        }
    }

    private String format(int a) {
        if (a == 0) return "-";
        else if (a == 1) return "X";
        else return "O";
    }
}
