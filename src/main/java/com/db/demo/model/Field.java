package com.db.demo.model;

import lombok.Data;

@Data
public class Field {
    int maxLength;
    int maxRange;
    int minRange;
    String fieldType;
    String fieldName;
    String isMandatory;
    String isPrimaryKey;
}
