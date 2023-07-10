package org.example;
public class Main {
    public static void main(String[] args) {
        //количество столбцов и строк в лабиринте
        int lines = 10, columns = 10;
        //процент разреженности лабиринта
        int fullness = 20;

        Labyrinth labyrinth = new Labyrinth();
        char[][] arr = labyrinth.generateLabyrinth(lines, columns, fullness);
        labyrinth.printLabyrinth(arr);

        labyrinth.DFSearch(arr, lines, columns);
        labyrinth.printLabyrinth(arr);

        labyrinth.cleanLabyrinth(arr);

        labyrinth.BFSearch(arr, lines, columns);
        labyrinth.printLabyrinth(arr);


    }
    }
