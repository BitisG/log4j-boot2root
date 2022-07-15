package dtu.project.log4jboot2root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String userInput = "${env:JAVA_HOME}";

        // passing user input into the logger
        logger.info("Test: " + userInput);

        // %m{nolookups} has no effect for the following line
        // logger.printf(Level.INFO,"Test: %s", userInput);
    }
}