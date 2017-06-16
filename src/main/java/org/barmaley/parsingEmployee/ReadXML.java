package org.barmaley.parsingEmployee;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

//Класс для работы с XML.
public class ReadXML {
    private static Document getDocument(String path) throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            return builder.parse(new File(path));
        } catch (Exception exception) {
            String message = "XML parsingEmployee error!";
            throw new Exception(message);
        }
    }

    //Считывание из файла данных о всех сотрудниках.
    static public ArrayList<Person> readAllPerson(String path) {
        ArrayList<Person> allPerson = new ArrayList<Person>();

        Document doc;
        try {
            //Получение документа по его пути
            doc = getDocument(path);
        }  catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        //1
        Node root = doc.getChildNodes().item(0);
        //2
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            if (nodeList.item(i).getNodeName().compareTo("PERSON") == 0)
                allPerson.add(ReadXML.readPerson(nodeList.item(i)));
        }

        return allPerson;
    }

    //Считывание данных из файла об обном сотруднике.
    static public Person readPerson(Node node) {
        Person person = new Person();

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node item = nodeList.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE) {
                person.test1(item.getNodeName(), item.getTextContent());
                person.setField(item.getNodeName(), item.getTextContent());
            }
        }

        return person;
    }

    //Считывание из файла данных об отделе.
    static public Otdel readOtdel(Node node) {
        Otdel otdel = new Otdel();

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node item = nodeList.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE)
                otdel.setField(item.getNodeName(), item.getTextContent());
        }

        return otdel;
    }

    //Считывание из файла данных о всех отделах.
    static public ArrayList<Otdel> readAllOtdel(String path) {
        ArrayList<Otdel> allOtdel = new ArrayList<Otdel>();

        Document doc;
        try {
            doc = getDocument(path);
        }  catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }


        Node root = doc.getChildNodes().item(0);
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            if (nodeList.item(i).getNodeName().compareTo("DEP") == 0)
                allOtdel.add(ReadXML.readOtdel(nodeList.item(i)));
        }

        return allOtdel;
    }


}
