package com.ar.conde.reservaDeTurnos.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

    public class Log4j {
        private static final Logger logger = LoggerFactory.getLogger(Log4j.class);

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

