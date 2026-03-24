package utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Simple Log4j2 wrapper used in tests.
 */
public final class Log4j {
    private Log4j() {}

    public static Logger getLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }

    public static void info(Class<?> cls, String msg) {
        getLogger(cls).info(msg);
    }

    public static void debug(Class<?> cls, String msg) {
        getLogger(cls).debug(msg);
    }

    public static void warn(Class<?> cls, String msg) {
        getLogger(cls).warn(msg);
    }

    public static void error(Class<?> cls, String msg, Throwable t) {
        getLogger(cls).error(msg, t);
    }
}
