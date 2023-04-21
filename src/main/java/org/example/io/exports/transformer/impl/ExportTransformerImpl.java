package org.example.io.exports.transformer.impl;

import org.example.io.exports.anotations.ExportAttribute;
import org.example.io.exports.model.Attribute;
import org.example.io.exports.transformer.Transformer;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class ExportTransformerImpl<T> implements Transformer {
    private final Class<T> modelType;

    protected ExportTransformerImpl(Class<T> modelType) {
        this.modelType = modelType;
    }

    /**
     * Get field attribute of Object type T
     *
     * @return a list of attribute
     */
    protected List<Attribute> getAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        Field[] modelFields = this.modelType.getDeclaredFields();
        for (Field field : modelFields) {
            field.setAccessible(true);
            Attribute attribute = new Attribute();
            attribute.setName(field.getName());
            attribute.setType(field.getType().getSimpleName().toLowerCase());
            if (field.isAnnotationPresent(ExportAttribute.class)) {
                ExportAttribute exportAttribute = field.getAnnotation(ExportAttribute.class);
                attribute.setColumn(exportAttribute.column().trim());
                attribute.setDatetimePattern(exportAttribute.datetimePattern().trim());
                attribute.setZoneId(exportAttribute.zoneId());
                attribute.setScale(exportAttribute.scale());
                attribute.setFixedLength(exportAttribute.fixedLength());
                attribute.setTrueValue(exportAttribute.trueValue().trim());
                attribute.setFalseValue(exportAttribute.falseValue().trim());
            }
            attributes.add(attribute);
        }
        return attributes;
    }


    /**
     * Transform a String value by attribute
     * @param resultSet a result set
     * @param attribute attribute
     * @return a String value
     * @throws SQLException SQL Exception
     */
    protected String transform(ResultSet resultSet, Attribute attribute) throws SQLException {
        String value;
        switch (attribute.getType()) {
            case "boolean":
                value = resultSet.getBoolean(attribute.getColumn()) ?
                        attribute.getTrueValue() : attribute.getFalseValue();
                break;
            case "float":
                value = resultSet.getString(attribute.getColumn());
                if (attribute.getScale() > 0) {
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(attribute.getScale());
                    value = df.format(Float.parseFloat(value));
                }
                break;
            case "double":
                value = resultSet.getString(attribute.getColumn());
                if (attribute.getScale() > 0) {
                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(attribute.getScale());
                    value = df.format(Double.parseDouble(value));
                }
                break;
            case "localdate":
                LocalDate localDate = resultSet.getDate(attribute.getColumn()).toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(attribute.getDatetimePattern());
                value = localDate.format(formatter);
                break;
            case "localdatetime":
                localDate = resultSet.getDate(attribute.getColumn()).toLocalDate();
                LocalTime localTime = resultSet.getTime(attribute.getColumn()).toLocalTime();
                LocalDateTime localDateTime = localDate.atTime(localTime);

                formatter = DateTimeFormatter.ofPattern(attribute.getDatetimePattern());
                value = localDateTime.format(formatter);
                break;
            case "zoneddatetime":
                localDate = resultSet.getDate(attribute.getColumn()).toLocalDate();
                localTime = resultSet.getTime(attribute.getColumn()).toLocalTime();
                ZoneId zoneId = attribute.getZoneId().isEmpty() ?
                        ZoneId.systemDefault() : ZoneId.of(attribute.getZoneId());
                ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, zoneId);

                formatter = DateTimeFormatter.ofPattern(attribute.getDatetimePattern());
                value = zonedDateTime.format(formatter);
                break;
            default:
                value = resultSet.getString(attribute.getColumn());
                break;
        }
        return value;
    }
}
