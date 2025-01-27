package LOGGER;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class LogManager {

    private static final String LOG4J_CONFIG_FILE = "src/main/java/LOGGER/log4j2.xml";
    private static LogManager instance;
    private static Logger logger;

    // Private constructor to prevent instantiation
    private LogManager() {
        configureLogger();
    }

    // Singleton pattern with double-checked locking
    public static LogManager getInstance() {
        if (instance == null) {
            synchronized (LogManager.class) {
                if (instance == null) {
                    instance = new LogManager();
                }
            }
        }
        return instance;
    }

    // Initialize the logger once
    private void configureLogger() {
        if (logger == null) {
            Configurator.initialize(null, LOG4J_CONFIG_FILE);
            logger = org.apache.logging.log4j.LogManager.getLogger(LogManager.class);
        }
    }

    //TODO: Log info level messages
    public void info(String message) {
        logger.info(message);
    }

    //TODO: Log error level messages with optional exception stack trace
    public void error(String message) {
        logger.error(message);
    }

    //TODO: Log warning level messages
    public void warn(String message) {
        logger.warn(message);
    }

    //TODO: Log fatal level messages with optional exception stack trace
    public void Fatal(String message) {
        logger.fatal(message);
    }

}
