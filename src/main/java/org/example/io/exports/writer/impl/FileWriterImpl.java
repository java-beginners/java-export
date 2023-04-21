package org.example.io.exports.writer.impl;

import org.example.io.exports.writer.FilePathBuilder;
import org.example.io.exports.writer.Writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterImpl implements Writer {
    private final FilePathBuilder filePathBuilder;
    private BufferedWriter bufferedWriter;

    public FileWriterImpl(FilePathBuilder filePathBuilder) {
        this.filePathBuilder = filePathBuilder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() throws IOException {
        File file = new File(filePathBuilder.build());
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(String value) throws IOException {
        bufferedWriter.write(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        bufferedWriter.close();
    }
}
