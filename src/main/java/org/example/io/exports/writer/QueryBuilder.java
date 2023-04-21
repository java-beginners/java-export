package org.example.io.exports.writer;

public interface QueryBuilder {
    /**
     * Build and return a native SQL query string.
     *
     * @return a native SQL query string
     */
    String build();
}
