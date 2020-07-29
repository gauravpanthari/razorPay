package com.db.demo.manager;

import com.db.demo.interfaces.DbOperations;
import com.db.demo.model.Field;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DbOperationImpl implements DbOperations {
    public static Map<String, Map<String, Map<String, Map<String, String>>>> dbMap = null;
    public  static LinkedHashMap<String, Field> data = new LinkedHashMap<>();

    @Override
    public void createDB(String name) {
        dbMap =new HashMap<>();;
        Map<String, Map<String, Map<String, String>>> db= new HashMap<>();
        dbMap.put(name, db );
        System.out.println("DB Created: " +name);
    }

    @Override
    public void deleteRecord(Integer id) {

    }

    @Override
    public void createTable(String dbName, String tableName, List<Field> fieldName) {
        Map<String, Map<String, Map<String, String>>> db= dbMap.get(dbName);
        Map<String, Map<String, String>> table = new HashMap<>();
        db.put(tableName, table);
        for(Field f : fieldName) {
            data.put(f.getFieldName(), f);
        }
        System.out.println("Table Created: " +tableName);
    }

    @Override
    public void deleteTable(String tableName) {

    }

    @Override
    public void insert(String dbName, String tableName, String[] fields) {
        Map<String, Map<String, Map<String, String>>> db= dbMap.get(dbName);
        Map<String, Map<String, String>> table = db.get(tableName);
        Boolean isValid=true;
        try {
            int i = 0;
            Map<String, String> record = new HashMap<>();
            String primaryKey = String.valueOf(Integer.MAX_VALUE);
            for (Map.Entry<String, Field> entry : data.entrySet()) {
                Boolean isString= entry.getValue().getFieldType().equals("String")? true :false;

                if (entry.getValue().getFieldName().equals("id")  && !isString) { //for primary key
                    String fieldValue = fields[i];
                    Boolean flag = true;
                    if(entry.getValue().getIsMandatory() !=null && Boolean.valueOf(entry.getValue().getIsMandatory())){
                        if(ObjectUtils.isEmpty(fieldValue)) flag = false;
                    }
                    int id = Integer.parseInt(fields[i]);
                    if(flag && (entry.getValue().getMaxRange() > id && entry.getValue().getMinRange() < id) )
                    {
                        primaryKey = fields[i];
                        record.put(entry.getValue().getFieldName(), fields[i]);
                        i++;
                    }else {
                        isValid = false;
                    }
                }else {
                    if (!isString) {  //integer other than id
                        String fieldValue = fields[i];
                        Boolean flag = true;
                        if(entry.getValue().getIsMandatory() !=null && Boolean.valueOf(entry.getValue().getIsMandatory())){
                            if(ObjectUtils.isEmpty(fieldValue)) flag = false;
                        }
                        int field = Integer.parseInt(fields[i]);
                        if(flag && (entry.getValue().getMaxRange() > field && entry.getValue().getMinRange() < field) )
                        {
                            record.put(entry.getValue().getFieldName(), fields[i]);
                            i++;
                        }else {
                            isValid=false;
                        }
                    }

                    else if (isString) {   //String
                        int maxLength = fields[i].length();
                        if (entry.getValue().getMaxLength() > maxLength) {
                            record.put(entry.getValue().getFieldName(), fields[i]);
                            i++;
                        }else {
                            isValid=false;
                        }
                    }
                }

        }
        if(!isValid) {
            System.out.println("Invalid Data. Please enter again");
        }else{
            System.out.println("Inserted One Row.");
            table.put(primaryKey, record);
        }

    }catch(Exception e){
            System.out.println("Exception occured " +e);
        }
    }

    @Override
    public void displayAll(String dbName, String tableName) {
        Map<String, Map<String, Map<String, String>>> db= dbMap.get(dbName);
        Map<String, Map<String, String>> table = db.get(tableName);
        for (Map.Entry<String, Map<String, String>> idEntry : table.entrySet()) {
            for (Map.Entry<String, String> dataEntry : idEntry.getValue().entrySet()) {
                System.out.print(dataEntry.getValue() +" ");
            }
        }

    }

    @Override
    public void display(String dbName, String tableName, String id) {

    }
}
