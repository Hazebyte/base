package com.hazebyte.base.util;

import java.util.logging.Logger;

/**
 * Misc helper functions
 */
public class Lib {

    private static boolean DEBUG = false;
    private static Logger logger = Logger.getLogger("Base");

    public static void debug(Object... messages) {
        if (!DEBUG) return;
        for (Object object : messages) {
            logger.info(object.toString());
        }
    }

    public static void info(Object... messages) {
        for (Object object : messages) {
            logger.info(object.toString());
        }
    }
}
