package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
        final String USERNAME = "username";
        final String PASSWORD = "password";


        Labyrinth labyrinth = new Labyrinth();
        char[][] arr = labyrinth.generateLabyrinth(10, 10, 20);
        labyrinth.printLabyrinth(arr);

        labyrinth.DFSearch(arr, 10, 10);
        labyrinth.printLabyrinth(arr);

        labyrinth.cleanLabyrinth(arr);

        labyrinth.BFSearch(arr, 10, 10);
        labyrinth.printLabyrinth(arr);

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            // Создаем подготовленное выражение для вставки данных в таблицу
            String sql = "INSERT INTO mytable (value) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Проходимся по всем элементам массива и записываем их в базу данных
            for (char[] row : arr) {
                for (int value : row) {
                    statement.setInt(1, value);
                    statement.executeUpdate();
                }
            }

            System.out.println("Данные успешно записаны в базу данных.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
