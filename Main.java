package tictactoe;
import java.util.Scanner;
public class Main {
    static final int n = 3;
    static int a;
    static int b;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = ' ';
            }
        }
        char x;
        char y;
        int player = 0;
        while (!hasWinner(arr) && player < 9) {
            printField(arr);
            do {
                System.out.print("Enter the coordinates: ");
                x = scanner.next().charAt(0);
                y = scanner.next().charAt(0);
            } while (!checkCoords(arr, x, y));
            arr[n - b][a - 1] = ++player % 2 == 1 ? 'X' : 'O';
        }
        printField(arr);
        System.out.println(winner(arr));
    }
    static String winner(char[][] arr) {
        String result = "";
        boolean p = arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2];
        boolean q = arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0];
        if (p || q) {
            result = arr[1][1] + " wins";
        } else {
            for (int i = 0; result.length() == 0 && i < n; i++) {
                if (arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2]) {
                    result = arr[i][0] + " wins";
                } else if (arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i]) {
                    result = arr[0][i] + " wins";
                }
            }
        }
        if (result.length() == 0) {
            result = "Draw";
        }
        return result;
    }
    static boolean hasWinner(char[][] arr) {
        boolean hasWinner = false;
        if (arr[1][1] != ' ') {
            if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]) {
                hasWinner = true;
            } else if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
                hasWinner = true;
            }
        }
        for (int i = 0; !hasWinner && i < n; i++) {
            if (arr[i][i] != ' ') {
                if (arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2]) {
                    hasWinner = true;
                } else if (arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i]) {
                    hasWinner = true;
                }
            }
        }
        return hasWinner;
    }
    static boolean checkCoords(char[][] arr, char x, char y) {
        boolean flag = false;
        String warning = "";
        if (digital(x, y)) {
            if (from1to3(x, y)) {
                if (unoccupied(arr)) {
                    flag = true;
                } else {
                    warning = "This cell is occupied! Choose another one!";
                }
            } else {
                warning = "Coordinates should be from 1 to 3!";
            }
        } else {
            warning = "You should enter numbers!";
        }
        System.out.println(warning);
        return flag;
    }
    static boolean digital(char x, char y) {
        return Character.isDigit(x) && Character.isDigit(y);
    }
    static boolean from1to3(char x, char y) {
        a = Character.digit(x, 10);
        b = Character.digit(y, 10);
        return a > 0 && a < 4 && b > 0 && b < 4;
    }
    static boolean unoccupied(char[][] arr) {
        return arr[n - b][a - 1] == ' ' ? true : false;
    }
    static void printField(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < n; i++) {
            System.out.println("| " + arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + " |");
        }
        System.out.println("---------");
    }
}