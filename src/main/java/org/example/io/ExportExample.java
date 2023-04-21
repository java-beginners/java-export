package org.example.io;

import org.example.io.exports.Exporter;
import org.example.io.exports.handler.ExceptionHandler;
import org.example.io.exports.handler.impl.ExceptionHandlerImpl;
import org.example.io.exports.transformer.impl.DelimiterTransformerImpl;
import org.example.io.exports.transformer.impl.ExportTransformerImpl;
import org.example.io.exports.transformer.impl.FixedLengthTransformerImpl;
import org.example.io.exports.writer.QueryBuilder;
import org.example.io.exports.writer.Writer;
import org.example.io.exports.writer.impl.FileWriterImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportExample {
    public ExportExample() {
    }

    public void run() {
        String connectionUrl = "jdbc:mysql://localhost:3306/users?allowMultiQueries=true";
        String user = "root";
        String pass = "P@sswi0rd";
        try(Connection connection = DriverManager.getConnection(connectionUrl, user, pass)) {
            QueryBuilder queryBuilder = new UserQueryBuilder();
            ExceptionHandler exceptionHandler = new ExceptionHandlerImpl();
            ExportTransformerImpl<ExportUserEntity> delimiterTransformer  =
                    new DelimiterTransformerImpl<>(ExportUserEntity.class, ",", exceptionHandler);

            Writer fileWriter = new FileWriterImpl(() -> {
                String dir = "/src/main/resources/";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                return dir + "export/users_" + simpleDateFormat.format(new Date()) + ".csv";
            });

            ExceptionHandler exceptionHandler1 = new ExceptionHandlerImpl();

            Exporter exporter = new Exporter(
                    connection,
                    queryBuilder,
                    delimiterTransformer,
                    fileWriter,
                    exceptionHandler1
            );
            exporter.execute();

            ExportTransformerImpl<ExportUserEntity> fixedLengthTransformer  =
                    new FixedLengthTransformerImpl<>(ExportUserEntity.class, " ", exceptionHandler1);
            Writer fixedlengthWriter = new FileWriterImpl(() -> {
                String dir = "/src/main/resources/";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                return dir + "export/users_fixed_length_" + simpleDateFormat.format(new Date()) + ".csv";
            });
            Exporter fixedLengthExporter = new Exporter(
                    connection,
                    queryBuilder,
                    fixedLengthTransformer,
                    fixedlengthWriter,
                    exceptionHandler1
            );
            fixedLengthExporter.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
