package com.ar.conde.reservaDeTurnos.log4j;

import org.apache.log4j.Logger;

    public class Log4j {
        private static final Logger logger = Logger.getLogger(Log4j.class);

        public static void info(String msg) {
            logger.info(msg);
        }

        public static void warn (String msg) {
            logger.warn(msg);
        }

        public static void error(String msg) {
            logger.error(msg);
        }
    }

