package com.codecool.shop.controller.Database;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {

        DatabaseManager db = new DatabaseManager();
        db.connect();

        System.out.println("Hello World!");

    }
}
