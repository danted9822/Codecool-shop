package com.codecool.shop.controller.Database;

<<<<<<< HEAD
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
=======
import com.codecool.shop.dao.implementation.*;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
>>>>>>> dc55b42edc1181a812601a1665336040897a2a28

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
