package org.example.io.exports;

import org.example.io.exports.handler.ExceptionHandler;
import org.example.io.exports.transformer.Transformer;
import org.example.io.exports.writer.QueryBuilder;
import org.example.io.exports.writer.Writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Exporter {
    private final Connection connection;
    private final QueryBuilder queryBuilder;
    private final Transformer transformer;
    private final Writer writer;
    private final ExceptionHandler exceptionHandler;

    public Exporter(Connection connection, QueryBuilder queryBuilder, Transformer transformer,
                    Writer writer, ExceptionHandler exceptionHandler) {
        this.connection = connection;
        this.queryBuilder = queryBuilder;
        this.transformer = transformer;
        this.writer = writer;
        this.exceptionHandler = exceptionHandler;
    }

    public void execute() {
        String query = queryBuilder.build();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            writer.open();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String value = transformer.transform(resultSet);
                writer.write(value);
            }
            writer.close();
        } catch (Exception e) {
            exceptionHandler.handleException(e);
        }
    }
}
