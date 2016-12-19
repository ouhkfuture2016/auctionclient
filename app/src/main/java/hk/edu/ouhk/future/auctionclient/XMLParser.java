package hk.edu.ouhk.future.auctionclient;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * XMLParser reads server responses in XML format,
 * and gives meaningful output for further processing.
 */

public class XMLParser extends DefaultHandler{
    String fileContent;
    ArrayList<Hashtable<String, String>> responseList;

    public XMLParser(String fileContent) {
        this.fileContent = fileContent;
    }

    public void startDocument() throws SAXException {
        responseList = new ArrayList<>();
    }

    public void startElement(String namespaceURI,
                             String localName,
                             String qName,
                             Attributes atts) throws SAXException {
        if (localName.equals("response")) {
            Hashtable<String, String> ht = new Hashtable<>();
            for (int i = 0; i < atts.getLength(); i++)
                ht.put(atts.getQName(i), atts.getValue(i));
            responseList.add(ht);
        }
    }

    /**
     * Invoke the parser to read the XML response.
     */
    public ArrayList<Hashtable<String, String>> invoke()
            throws ParserConfigurationException,
            SAXException,
            IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(this);
        xmlReader.parse(new InputSource(new StringReader(fileContent)));
        return responseList;
    }
}
