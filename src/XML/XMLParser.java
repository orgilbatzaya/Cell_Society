package XML;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//TODO: remove unused packages and instance variables
//import org.w3c.dom.Text;
//import javax.xml.transform.*;
//import javax.xml.transform.dom.*;
//import javax.xml.transform.stream.*;

/**
 * XMLParser
 *
 * reads and interprets an XML file to get information
 * for the initial grid conditions
 *
 * @author Brooke Keene
 */
public class XMLParser {
//    public static final String ERROR_MESSAGE = "XML file does not represent %s";
    private final DocumentBuilder DOC_BUILDER;

    private Document DOM;
    private Element rootElem;
    private String myFile;

    public XMLParser(File file) {
        DOC_BUILDER = getDocumentBuilder();
        myFile = file.toString();
    }

    public void readFile() {
        try {
            DOM = DOC_BUILDER.parse(myFile);
            rootElem = DOM.getDocumentElement();
        }
        catch (SAXException se) {
            System.out.println(se.getMessage());
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     *
     * @return String representing the simulation to run
     */
    public String getSimulation() {
        String mySim = getTextValue("simulation", rootElem);
        if(mySim != null) {
            if(!mySim.isEmpty()) {
                System.out.println(mySim);
            }
        }
        return mySim;
    }

    /**
     *
     * @return int representing the size of the grid
     */
    public int getGridSize() {
        String tempSize = getTextValue("size", rootElem);
        int myGridSize = -1;
        if(tempSize != null) {
            if(!tempSize.isEmpty()) {
                myGridSize = Integer.parseInt(tempSize);
                System.out.println(myGridSize);
            }
        }
        return myGridSize;
    }

    /**
     *
     * @param tag, tag of the specific data to look for
     * @return the parameter specified by tag
     */
    public double getParameter(String tag) {
        String temp = getTextValue(tag, rootElem);
        double param = -1.0;
        if(temp != null) {
            if(!temp.isEmpty()) {
                param = Double.parseDouble(temp);
                System.out.println(param);
            }
        }
        return param;
    }

    /**
     * returns DocumentBuilder that parser will extract data from
     *
     * @return DocumentBuilder to parse
     */
    private DocumentBuilder getDocumentBuilder() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            factory.setValidating(false);
            return factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException parserConfigE) {
            System.out.println("XML Error: Error trying to instantiate DocumentBuilder " + parserConfigE);//throw new XMLException(e);
            return null;
        }
    }

    /**
     * note: from Duvall's code
     *
     * @return
     */
    private String getTextValue(String tag, Element e) {
        var nodeList = e.getElementsByTagName(tag);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }

    }

}
