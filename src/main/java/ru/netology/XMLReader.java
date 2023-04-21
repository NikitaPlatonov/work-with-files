package ru.netology;

import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLReader {
    public XMLReader(File XMLFile) throws Exception {
        DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = db.newDocumentBuilder();
        Document doc = (Document) builder.parse(XMLFile);
    }

}
