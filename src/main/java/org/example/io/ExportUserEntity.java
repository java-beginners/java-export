package org.example.io;

import org.example.io.exports.anotations.ExportAttribute;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class ExportUserEntity {
    @Id
    @ExportAttribute(fixedLength = 11, column = "id")
    private String id;
    @ExportAttribute(fixedLength = 10, column = "username")
    private String username;
    @ExportAttribute(fixedLength = 31, column = "email")
    private String email;
    @ExportAttribute(fixedLength = 20, column = "phone")
    private String phone;
    @ExportAttribute(fixedLength = 2, trueValue = "1", falseValue = "0", column = "status")
    private boolean status;
    @ExportAttribute(fixedLength = 22, datetimePattern = "yyyy-MM-dd HH:mm:ss Z", column = "createdDate")
    private ZonedDateTime createdDate;
    @ExportAttribute(fixedLength = 22, datetimePattern = "yyyy-MM-dd HH:mm:ss", column = "updateDate")
    private LocalDateTime updateDate;
    @ExportAttribute(fixedLength = 6, scale = 3, column = "value")
    private float value;
    @ExportAttribute(fixedLength = 17, scale = 6, column = "percent")
    private double percent;
    @ExportAttribute(fixedLength = 11, datetimePattern = "yyyy-MM-dd", column = "birthday")
    private LocalDate birthday;

    public ExportUserEntity() {
    }
}
