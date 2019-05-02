package com.hazebyte.base;

import java.util.logging.Logger;

public class Lib {

    private static Logger logger = Logger.getLogger("Base");

    public static void debug(Object... messages) {
        for (Object object : messages) {
            logger.info(object.toString());
        }
    }

}
