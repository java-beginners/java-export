package org.example.io;

import org.example.io.exports.writer.QueryBuilder;

public class UserQueryBuilder implements QueryBuilder {
    @Override
    public String build() {
        return "SELECT * FROM io_user ORDER BY id ASC";
    }
}
