package com.db.demo;

import com.db.demo.manager.DbOperationImpl;
import com.db.demo.model.Field;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DbDemoApplication {


    public static void main(String[] args){
        SpringApplication.run(DbDemoApplication.class, args);
        DbOperationImpl op = new DbOperationImpl();
        op.createDB("EmployeeDb");

        List<Field> fieldList= new ArrayList<>();
        Field field1= new Field();
        field1.setMinRange(-1024);
        field1.setMaxRange(1024);
        field1.setIsMandatory("true");
        field1.setFieldName("id");
        field1.setFieldType("Integer");

        Field field2= new Field();
        field2.setMaxLength(20);
        field2.setFieldName("name");
        field2.setFieldType("String");

        fieldList.add(field1);
        fieldList.add(field2);

        op.createTable("EmployeeDb","Employee", fieldList);
        String[] s = new String[2];
        s[0]= "24";
        s[1] = "ram";
        op.insert("EmployeeDb","Employee",s);
        s[0]= "35";
        s[1] = "Hari";
        op.insert("EmployeeDb","Employee",s);
        s[0]= "1055";  //out of range
        s[1] = "shreya";
        op.insert("EmployeeDb","Employee",s);
        op.displayAll("EmployeeDb","Employee");
    }
}
