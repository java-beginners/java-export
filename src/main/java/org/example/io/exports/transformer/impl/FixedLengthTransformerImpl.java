package org.example.io.exports.transformer.impl;

import org.apache.logging.log4j.util.Strings;
import org.example.io.exports.handler.ExceptionHandler;
import org.example.io.exports.model.Attribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FixedLengthTransformerImpl<T> extends ExportTransformerImpl<T> {
    private final List<Attribute> attributes;
    private final String pad;
    private final String end;
    private final ExceptionHandler exceptionHandler;

    public FixedLengthTransformerImpl(Class<T> clazz, String pad, ExceptionHandler exceptionHandler, String ...options) {
        super(clazz);
        this.attributes = super.getAttributes();
        this.pad = pad;
        this.exceptionHandler = exceptionHandler;
        if (options.length > 0) {
            this.end = options[1];
        } else {
            this.end = "\n";
        }
    }

    private String toFixedLength(String value, int length) {
        int diff = length - value.length();
        for (int i = 0; i < diff; i++) {
            value = Strings.concat(pad, value);
        }
        return value;
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
                value = toFixedLength(value, attribute.getFixedLength());
                record.append(value);
            } catch (SQLException e) {
                exceptionHandler.handleTransformException(e);
            }
        });
        return record.append(end).toString();
    }
}


