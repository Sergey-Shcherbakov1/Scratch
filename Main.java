package Geekbrains_Homework;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static char DOT_EMPTY = '•';
    public static char DOT_X = 'X';
    public static char DOT_O = 'O';
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
//    3. Создать алгоритм работы хода человек/компьютер
//    4. Задать условия победы
        initMap();
        printMap();
        while (true) {
            humanTurn();
            if ( checkWin(DOT_X) ) {
                System.out.println("Вы выиграли!");
                break;
            }
            if ( isMapFull() ) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            if ( checkWin(DOT_O) ) {
                System.out.println("Победил компьютер!");
                break;
            }
            if ( isMapFull() ) {
                System.out.println("Ничья!");
                break;
            }
        }
        System.out.println("Game over");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map [i][j] = DOT_EMPTY;
            }
        }
    }
    private static void printMap() {
        for (int i=0; i <= SIZE; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }
    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите линию по горизонтали: ");
            y = scanner.nextInt()-1;
            System.out.println("Введите линию по вертикали:");
            x = scanner.nextInt()-1;
        } while (!isCellValid (x,y));
        map [x][y] = DOT_X;
        printMap();
    }
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid (x,y));
        map [y][x] = DOT_O;
        System.out.println("Компьютер пошел в точку " + (x+1) + " " + (y+1));
        printMap();
    }
    private static boolean isCellValid(int x, int y) {
        if ( x < 0 || x > SIZE || y < 0 || y > SIZE ) return false;
        if (map [x][y] == DOT_EMPTY) return true;
        return false;
    }
    private static boolean checkWin(char symb) {
//        //  Упрощенный цикл проверки метода checkWin
        int countRow = 0; // цикл для горизонтали
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ( map[i][j] == symb) countRow++;
                if (countRow == DOTS_TO_WIN) return true;
            }
        }
        int countColumn = 0; // цикл для вертикали
         for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ( map[j][i] == symb ) countColumn++;
                if ( countColumn == DOTS_TO_WIN ) return true;
            }
        }
        int countDiag1 = 0; //цикл для первой диагонали
        for (int i = 0, j = 0; i < SIZE ; i++, j++) {
            if ( map[i][j] == symb ) countDiag1++;
            if ( countDiag1 == DOTS_TO_WIN ) return true; }
        int countDiag2 = 0; //цикл для второй диагонали
        for (int i = 0, j = DOTS_TO_WIN; i < SIZE && j < SIZE; i++, j--) {
            if ( map [i][j] == symb ) countDiag2++;
            if ( countDiag2 == DOTS_TO_WIN ) return true;
        }
        return false;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j <SIZE; j++) {
                if (map [i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
}