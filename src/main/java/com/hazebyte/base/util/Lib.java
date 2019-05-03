package com.hazebyte.base.util;

import java.util.logging.Logger;

/**
 * Misc helper functions
 */
public class Lib {

    private static Logger logger = Logger.getLogger("Base");

    public static void debug(Object... messages) {
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
