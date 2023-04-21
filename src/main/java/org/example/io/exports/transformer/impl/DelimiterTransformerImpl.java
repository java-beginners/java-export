package org.example.io.exports.transformer.impl;

import org.example.io.exports.handler.ExceptionHandler;
import org.example.io.exports.model.Attribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DelimiterTransformerImpl<T> extends ExportTransformerImpl<T> {
    private final List<Attribute> attributes;
    private final String separator;
    private final String end;
    private final ExceptionHandler exceptionHandler;

    public DelimiterTransformerImpl(Class<T> clazz, String separator, ExceptionHandler exceptionHandler, String ...options) {
        super(clazz);
        this.attributes = super.getAttributes();
        this.separator = separator;
        this.exceptionHandler = exceptionHandler;
        if (options.length > 0) {
            this.end = options[0];
        } else {
            this.end = "\n";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String transform(ResultSet resultSet) {
        StringBuilder record = new StringBuilder();
        attributes.forEach(attribute -> {
            try {
                String value = super.transform(resultSet, attribute);
                if (attribute.getType().equals("string")) {
                    value = String.format("\"%s\"", value);
                }
                if (record.length() > 0) {
                    record.append(separator);
                }
                record.append(value);
            } catch (SQLException e) {
                exceptionHandler.handleTransformException(e);
            }
        });
        return record.append(end).toString();
    }
}
