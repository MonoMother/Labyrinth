package org.example;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


    public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //путь к БД
        final String DB_URL = "jdbc:mysql://localhost:3306/labyrinthdatabase";

        System.out.println("Введите имя пользователя MySQL: ");
        String USERNAME = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String PASSWORD = scanner.nextLine();

        //количество столбцов и строк в лабиринте
        int lines = 10, columns = 10;
        //процент разреженности лабиринта
        int fullness = 20;

        Labyrinth labyrinth = new Labyrinth();
        char[][] arr = labyrinth.generateLabyrinth(lines, columns, fullness);
        labyrinth.printLabyrinth(arr);

        labyrinth.DFSearch(arr, lines, columns);
        labyrinth.printLabyrinth(arr);



        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            // Создаем подготовленное выражение для вставки данных в таблицу
            String sql = "INSERT INTO mytable (value) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Проходимся по всем элементам массива и записываем их в базу данных
            for (int i = 0; i<lines; i++){
                for (int j = 0; j<columns; j++){
                    statement.setString(1, String.valueOf(arr[i][j]));
                    statement.executeUpdate();
                }
            }

            System.out.println("Данные прохождения лабиринта DF поиском успешно записаны в базу данных.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        labyrinth.cleanLabyrinth(arr);

        labyrinth.BFSearch(arr, lines, columns);
        labyrinth.printLabyrinth(arr);

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            // Создаем подготовленное выражение для вставки данных в таблицу
            String sql = "INSERT INTO mytable (value) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Проходимся по всем элементам массива и записываем их в базу данных
            for (int i = 0; i<lines; i++){
                for (int j = 0; j<columns; j++){
                    statement.setString(1, String.valueOf(arr[i][j]));
                    statement.executeUpdate();
                }
            }

            System.out.println("Данные прохождения лабиринта BF поиском успешно записаны в базу данных.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
