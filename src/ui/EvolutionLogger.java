package ui;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvolutionLogger {

    private static final String LOG_FILE_NAME = "evolution.log";

    private final Logger logger;
    private final Lock loggerLock;

    public EvolutionLogger() {
        logger = LogManager.getLogger(LOG_FILE_NAME);
        loggerLock = new ReentrantLock();
    }

    public void log(String message) {
        loggerLock.lock();
        logger.info(message);
        loggerLock.unlock();
    }
}
