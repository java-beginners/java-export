package org.example.io.exports.handler;

public interface ExceptionHandler {

    /**
     * Handle transform exception
     *
     * @param e Exception
     */
    void handleTransformException(Exception e);

    /**
     * Handle exception
     *
     * @param e Exception
     */
    void handleException(Exception e);
}
