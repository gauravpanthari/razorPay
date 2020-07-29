package com.db.demo.interfaces;

import com.db.demo.model.Field;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface DbOperations {
    public  void createDB(String name);
    public void createTable(String dbName, String tableName, List<Field> fieldName );
    public void deleteRecord(Integer id);
    public void deleteTable(String tableName);
    public void insert(String dbName, String tableName, String[] fields);
    public void displayAll(String dbName, String tableName);
    public void display(String dbName, String tableName,String id);

}
