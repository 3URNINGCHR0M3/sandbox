package net.forje.common.logging;

public class Logger {

    public static enum Level {

        DEBUG(org.apache.log4j.Level.DEBUG),
        INFO(org.apache.log4j.Level.INFO),
        WARN(org.apache.log4j.Level.WARN),
        ERROR(org.apache.log4j.Level.ERROR),
        FATAL(org.apache.log4j.Level.FATAL)
        ;

        private final org.apache.log4j.Level _level;

        private Level(org.apache.log4j.Level level) {
            _level = level;
        }

        private org.apache.log4j.Level getLevel() {
            return _level;
        }

    }

    public static void record(Level level, Object subject, String message) {

        if (subject == null) {
            throw new IllegalArgumentException("subject was null");
        }

        final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(subject.getClass());
        logger.log(level.getLevel(), message);
    }

    public static void record(Level level, Object subject, String message, Throwable throwable) {
        if (subject == null) {
            throw new IllegalArgumentException("subject was null");
        }
        final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(subject.getClass());
        logger.log(level.getLevel(), message, throwable);
    }
    
}
