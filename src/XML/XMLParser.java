package XML;

import java.io.File;
import java.io.IOException;

import Simulation.Simulation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 * XMLParser
 *
 * reads and interprets an XML file to get information
 * for the initial grid conditions
 *
 * @author Brooke Keene
 */
public class XMLParser {
    public static final String ERROR_MESSAGE = "XML file does not represent %s";
    private final String TYPE;
    private final DocumentBuilder DOC_BUILDER;

    private File myFile;
    private Document DOM;
    private Element rootElem;

    private String mySim;
    private int mySimSpeed;
    private int myGridSize;

    public XMLParser(String type) {
        DOC_BUILDER = getDocumentBuilder();
        TYPE = type;
    }

    public boolean readFile(String xml) {
        try {
            DOM = DOC_BUILDER.parse(xml);
            Element elem = DOM.getDocumentElement();

            mySim = getTextValue("simulation", elem);
            if(mySim != null) {
                if(!mySim.isEmpty()) {
                    System.out.println(mySim);
                }
            }
            String tempSize = getTextValue("size", elem);
            if(tempSize != null) {
                if(!tempSize.isEmpty()) {
                    myGridSize = Integer.parseInt(tempSize);
                    System.out.println(myGridSize);
                }
            }

            //TODO: depending on type call another method that looks for simulation specific info

            return true;
        }
//        catch (ParserConfigurationException pce) {
//            System.out.println(pce.getMessage());
//        }
        catch (SAXException se) {
            System.out.println(se.getMessage());
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return false;
    }

    /**
     *
     * @return String representing the user-specified simulation to run
     */
    public String getSimulation() {
        return mySim;
    }

    /**
     *
     * @return
     */
    public int getSimSpeed() {
        return mySimSpeed;
    }

    /**
     *
     * @return
     */
    public int getGridSize() {
        return myGridSize;
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
