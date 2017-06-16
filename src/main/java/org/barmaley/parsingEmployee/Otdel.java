package org.barmaley.parsingEmployee;

import java.util.ArrayList;
import java.util.HashMap;

//Класс отдела.
public class Otdel {
    //Поля отдела (из файла xml).
    final static public ArrayList<String> KEYS = new ArrayList<String>();
    static {
        KEYS.add("COD");
        KEYS.add("SUBCOD");
        KEYS.add("D_NAME");
        KEYS.add("is_closed");
        KEYS.add("c_path");
    }

    //Конструктор.
    public Otdel() {
        fields = new HashMap<String, String>();
        for (String key : KEYS) {
            fields.put(key, null);
        }

        number = -1;
    }

    //Сеттер для задания значения поля отдела.
    public void setField(String key, String value) {
        if (key.compareTo("COD") == 0) number = Integer.parseInt(value);
        else if (key.compareTo("D_NAME") == 0) value = value.replace("\"", "\\\"");

        fields.put(key, value);
    }

    //Получение номера отдела.
    public int getNumber() {
        return number;
    }

    //Получение имени отдела.
    public String getName() {
        return fields.get("D_NAME");
    }

    //Поля (отдела) и их значения.
    private HashMap<String, String> fields;
    //Номер отдела.
    int number;
}