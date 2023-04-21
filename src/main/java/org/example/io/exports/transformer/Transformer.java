package org.example.io.exports.transformer;

import java.sql.ResultSet;

public interface Transformer {

    /**
     * Transform data from sql result set to string
     * @param resultSet a result set
     *
     * @return a string value
     */
    String transform(ResultSet resultSet);
}
