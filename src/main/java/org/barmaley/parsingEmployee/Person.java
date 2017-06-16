package org.barmaley.parsingEmployee;

import java.util.ArrayList;
import java.util.HashMap;

//Класс сотрудника.
public class Person {
    //Поля сотрудника (из файла xml).
    final static public ArrayList<String> KEYS = new ArrayList<String>();
    final static public ArrayList<String> KEYS1 = new ArrayList<String>();
    static {
        KEYS.add("TAB_N");
        KEYS.add("OTN_DOLG");
        KEYS.add("FAMALY");
        KEYS.add("IMY_OT");
        KEYS.add("FAMALIO");
        KEYS.add("DOLGN");
        KEYS.add("PERS_KAT");
        KEYS.add("OTDEL");
        KEYS.add("EMAIL");
        KEYS.add("PASS_NO");
        KEYS.add("DAT_PR");
        KEYS.add("DAT_UV");
        KEYS.add("is_closed");

        KEYS1.add("FAMALY");
        KEYS1.add("IMY_OT");
        KEYS1.add("DOLGN");
        KEYS1.add("OTDEL");
        KEYS1.add("EMAIL");
    }

    //Конструктор.
    public Person() {
        fields = new HashMap<String, String>();
        fields1 = new HashMap<String, String>();
        for (String key : KEYS) {
            fields.put(key, null);
        }
    }

    //Сеттер для задания значения поля сотрудника.
    public void setField(String key, String value) {
        fields.put(key, value);
    }

    public void test1(String key, String value){
      for(String keystatic : KEYS1){
          if(key.equals(keystatic)){
              fields1.put(key, value);
          }
      }
    }

    public String getSurname(){
        return fields.get("FAMALY").trim();
    }

    public String getFirstSecondname(){
        return fields.get("IMY_OT").trim();
    }

    public String getId() {
        return fields.get("TAB_N").trim();
    }
    public String getPosition(){
        return fields.get("DOLGN").trim();
    }



    //Получение номера отдела сотрудника.
    public int getNumberOtdel() {
        return Integer.parseInt(fields.get("OTDEL").trim());
    }

    //Формирование json строки с данными о сотруднике.
    public String toJSON() {
        boolean flag = false;

        String json = "{\n";
        for (String key : KEYS) {
            if (flag) json += ",\n";
            json += "\t\"" + key + "\": \"" + fields.get(key) + "\"";
            flag = true;
        }
        json += "\n}\n";
        return json;
    }

    //Поля (сотрудника) и их значения.
    private HashMap<String, String> fields;
    private HashMap<String, String> fields1;
}