package ru.netology;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReader {
    String loadEnabled;
    String loadStringFileName;
    String loadStringFormat;


    String saveEnabled;
    String saveFile;
    String saveStringFormat;

    String logEnabled;
    String logStringFileName;
    public void reader(File XMLFile) throws ParserConfigurationException, IOException, SAXException {
        //TODO примерная логика
        //TODO 1. Достаем каждый тег
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(XMLFile);
        Node root = doc.getDocumentElement();

        Element load = (Element) doc.getElementsByTagName("load").item(0);
        Element save = (Element) doc.getElementsByTagName("save").item(0);
        Element log = (Element) doc.getElementsByTagName("log").item(0);
        //TODO 2. Достаем необходимую информацию из каждого тега
        loadEnabled = load.getElementsByTagName("enabled").item(0).getTextContent();
        loadStringFileName = load.getElementsByTagName("fileName").item(0).getTextContent();
        loadStringFormat = load.getElementsByTagName("format").item(0).getTextContent();

        saveEnabled = save.getElementsByTagName("enabled").item(0).getTextContent();
        saveFile = save.getElementsByTagName("fileName").item(0).getTextContent();
        saveStringFormat = save.getElementsByTagName("format").item(0).getTextContent();

        logEnabled = log.getElementsByTagName("enabled").item(0).getTextContent();
        logStringFileName = log.getElementsByTagName("fileName").item(0).getTextContent();
    }
}
