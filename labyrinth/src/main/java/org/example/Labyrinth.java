package org.example;

import java.util.Random;

public class Labyrinth {
    private int lines;
    private int columns;
    private char[][] arr;

    public char[][] generateLabyrinth(int lines, int columns, int fullness){
        char[][] arr = new char[lines][columns];

        int allCells = lines*columns;
        int filledCells = (int)((float)fullness/100*allCells);



        for (int i = 0; i<lines; i++){
            for (int j = 0; j<columns; j++){
                arr[i][j] = ' ';
            }
        }
        arr[0][0] = 'S';
        arr[(lines-1)][(columns-1)] = 'E';
        Random random = new Random();
        int count = filledCells;
        do {
            int i = random.nextInt(lines);
            int j = random.nextInt(columns);
            if (arr[i][j] == ' ') {
                arr[i][j] = '▓';
                count--;
            }
        } while (count>0);
        return arr;
    }

    public void printLabyrinth(char[][] arr){
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    public char[][] cleanLabyrinth(char[][] arr){
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                if (arr[i][j] == '*') arr[i][j] = ' ';
            }
        }
        return arr;
    }

    public void DFSearch(char[][] arr, int lines, int columns){
        int i = 0, j = 0;

        do {
            if ((i!=lines-1)&&(arr[i+1][j]==' ')) ++i;

            else if ((j!=columns-1)&&(arr[i][j+1] == ' ')) ++j;

            else if ((i == lines-1)&(j!=columns-1)&(arr[i][j+1] == '▓')) {
                do {
                    arr[i][j]='*';
                    i--;
                }while (arr[i][j+1] != ' ');
                arr[i][j]= '*';
                j++;

            }
            else if ((i!=lines-1)&(j!=columns-1)&&(arr[i+1][j] == '▓')&&(arr[i][j+1] == '▓')){
                do {
                    arr[i][j]='*';
                    i--;
                }while (arr[i][j+1] != ' ');
                arr[i][j]= '*';
                j++;

            }
            if (arr[i][j] == ' ') arr[i][j]= '*';

        } while (((i!=lines-1)&&(arr[i+1][j]!='E'))||((j!=columns-1)&&arr[i][j+1]!='E'));
    }

    public void BFSearch(char[][] arr, int lines, int columns){
        int i = 0, j = 0;

        do {
            if ((j!=columns-1)&&(arr[i][j+1]==' ')) ++j;

            else if ((i!=lines-1)&&(arr[i+1][j] == ' ')) ++i;

            else if ((j == columns-1)&(i!=lines-1)&(arr[i+1][j] == '▓')) {
                do {
                    arr[i][j]='*';
                    j--;
                }while (arr[i+1][j] != ' ');
                arr[i][j]= '*';
                i++;

            }
            else if ((j!=lines-1)&(j!=columns-1)&&(arr[i+1][j] == '▓')&&(arr[i][j+1] == '▓')){
                do {
                    arr[i][j]='*';
                    j--;
                }while (arr[i+1][j] != ' ');
                arr[i][j]= '*';
                i++;

            }
            if (arr[i][j] == ' ') arr[i][j]= '*';

        } while (((i!=lines-1)&&(arr[i+1][j]!='E'))||((j!=columns-1)&&arr[i][j+1]!='E'));
    }

}
