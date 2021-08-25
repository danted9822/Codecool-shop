package com.codecool.shop.controller.Database;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    public DataSource connect() throws SQLException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        //        String dbName = "test";
        String dbName = System.getenv("PSQL_DB_NAME");
//        String user = "test";
        String user = System.getenv("PSQL_USER_NAME");
//        String password = "test";
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName("codecoolshop");
        dataSource.setUser("postgres");
        dataSource.setPassword("asd123");

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }

}
