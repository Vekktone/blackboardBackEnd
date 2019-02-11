package com.blackboard.backEnd;

import com.blackboard.backEnd.model.LogInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class simply configures the log, loads the properties file, and starts the spring application.
 * This project is a simple front end and backend product that displays customer information
 * from a database and allows users to manually upload customer files which are then inserted
 * into the database and shown on the web page. An email can also be sent to an arbitrary
 * address for analytics.
 */
@SpringBootApplication
public class Main {

    /**
     * Main simply configures the log and properties file, then starts the application.
     * @param args cmd line args
     */
    public static void main(String[] args) {

        LogInfo.Configure();
        LogInfo.loadProperties();
        SpringApplication.run(Main.class, args);
    }
}
