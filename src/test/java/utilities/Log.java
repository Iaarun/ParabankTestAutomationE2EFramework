package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.*;

/**
 * Lightweight logging utility using java.util.logging.
 * Logs to console and to target/logs/framework.log (creates folder if missing).
 */
public final class Log {
    private static final String LOG_DIR = "target/logs";
    private static final String LOG_FILE = LOG_DIR + "/framework.log";
    private static final Logger LOGGER = Logger.getLogger("framework");
    static {
        try {
            // Ensure log directory exists
            Path p = Paths.get(LOG_DIR);
            if (Files.notExists(p)) {
                Files.createDirectories(p);
            }

            // Remove default handlers and set our own
            Logger root = Logger.getLogger("");
            for (Handler h : root.getHandlers()) {
                root.removeHandler(h);
            }

            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter() {
                private static final String FORMAT = "[%1$tF %1$tT] [%2$s] [thread-%3$d] %4$s %n";
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(FORMAT,
                            lr.getMillis(),
                            lr.getLevel().getLocalizedName(),
                            Thread.currentThread().getId(),
                            lr.getMessage()
                    );
                }
            });

            // File handler (append)
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(consoleHandler.getFormatter());

            // Configure logger
            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.ALL);
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            // If logging setup fails, print minimal info to stderr
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Log() {}

    public static void info(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

    public static void debug(String msg) {
        LOGGER.log(Level.FINE, msg);
    }

    public static void warn(String msg) {
        LOGGER.log(Level.WARNING, msg);
    }

    public static void error(String msg) {
        LOGGER.log(Level.SEVERE, msg);
    }

    public static void error(String msg, Throwable t) {
        LOGGER.log(Level.SEVERE, msg + " - " + t.getMessage(), t);
    }
}
