package org.example.io.exports.anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExportAttribute {
    String column() default "";
    String datetimePattern() default "";
    String zoneId() default "";
    String trueValue() default "";
    String falseValue() default "";
    int scale() default 0;
    int fixedLength() default 0;
}