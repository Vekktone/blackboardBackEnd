package com.blackboard.backEnd.model;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The LogInfo class configures the logger to output appropriate messages
 */
public class LogInfo {

    static final Logger outputLog = Logger.getLogger("debugLogger");
    static final Logger errorLog = Logger.getLogger("reportsLogger");
    public static Properties prop;

    /**
     * Configure method to call properties files
     */
    public static void Configure() {
        PropertyConfigurator.configure(
                "/home/riley/IdeaProjects/blackboardBackEnd/src/main/resources/log4j.properties");

        sendOutput("***Student Info Generated Email Attachment***\n");
        sendError("***errorLog messages***\n");
    }

    /**
     * Output log which will contain the text we need to send in our email.
     * @param info String to be sent
     */
    public static void sendOutput(String info){ outputLog.debug(info); }

    /**
     * Error log which contains any errors
     * @param info String to be sent
     */
    public static void sendError(String info){ errorLog.debug(info); }

    /**
     * Properties file initializer, which uses config.properties to store essential login info and other credentials
     */
    public static void loadProperties(){
        prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(
                    "/home/riley/IdeaProjects/blackboardBackEnd/src/main/resources/config.properties");

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
