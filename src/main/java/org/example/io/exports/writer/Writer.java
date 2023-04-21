package org.example.io.exports.writer;

import java.io.IOException;

public interface Writer {
    /**
     * Open writer
     * @throws IOException
     */
    void open() throws IOException;

    /**
     * Write a line
     * @param value a line string
     * @throws IOException
     */
    void write(String value) throws IOException;

    /**
     * Close writer
     * @throws IOException
     */
    void close() throws IOException;
}
