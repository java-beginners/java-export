package org.example.io.exports.handler.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.io.exports.handler.ExceptionHandler;

public class ExceptionHandlerImpl implements ExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ExceptionHandlerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleTransformException(Exception e) {
        logError(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleException(Exception e) {
        logError(e);
    }

    private void logError(Exception e) {
        logger.error(e);
    }
}
