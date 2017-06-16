package org.barmaley.parsingEmployee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by impolun on 07.06.17.
 */
//Класс для парсинга файла txt
public class StudentParsingTxt {
    private static final Logger logger = LoggerFactory.getLogger("parsingTxt");
    //Конструктор
    public StudentParsingTxt(){};

    public List<String> parseTxtFile3() throws IOException
    {
        String pathTheFile ="/home/impolun/git/service/VKR-service/std.txt";
        String line = "";
        ArrayList<String> list = new ArrayList<String>();
        logger.info("Размер list до заполнения: "+list.size());
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathTheFile),"windows-1251"));
        while (line != null)
        {
            line = br.readLine();
            if (line==null)
            {
                continue;
            }
            line = line.replace(" ","");
            line = line.replace("|"," ");
            String tmp[] = line.split(" ");
            for(int i=0; i<tmp.length; i++)
            {
                logger.info("Элемемент["+i+"]= "+tmp[i]);
                list.add(tmp[i]);
            }
        }

        return list;
    }
}
