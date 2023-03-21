package ru.itmo.cookingservice.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

//Custom kotlin logger
open class Logger {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Logger::class.java)

        @JvmStatic
        fun info(message: String) {
            logger.info(message)
        }

        @JvmStatic
        fun error(message: String, throwable: Throwable?) {
            logger.error(message, throwable)
        }
    }


}