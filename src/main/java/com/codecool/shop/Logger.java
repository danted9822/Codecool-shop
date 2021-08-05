package com.codecool.shop;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;
import org.json.simple.JSONArray;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class Logger {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    public static void saveAdminLog(Order order, JSONArray transaxion) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
        LocalDateTime now = LocalDateTime.now();

        if (order.getStatus() == OrderStatus.CHECKED) {
            System.out.println(order);

            String filename = "src/main/webapp/static/Logger/" + order.getId() + "_" + dateFormat.format(new Date()) + ".json";
            System.out.println(filename);
            order.setFilename(filename);
            logger.info(String.format("Successfully checked out. Order id: %s", order.getId()));

            stringToFile(transaxion, filename);
        }
    }

    public static void stringToFile (JSONArray content, String filename) {
        FileWriter file = null;
        try {
            file = new FileWriter(filename);
            file.write(String.valueOf(content));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



