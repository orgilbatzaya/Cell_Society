package XML;

import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;


/**
 * XMLWriter
 *
 * creates and saves an XML file containing information
 * for the initial grid conditions
 *
 * @author Brooke Keene
 */
public class XMLWriter {
    private File myFile;
    private Document DOM;
    private Element rootElem;
    private Element nextElem;

    public XMLWriter() {
        this.makeDoc();
    }

    /**
     * add a Node to next element
     */
    public void addNode() {

    }

    /**
     * creates Document using DocumentBuilderFactory
     */
    private void makeDoc() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            DOM = db.newDocument();

            rootElem = DOM.createElement("root");

            DOM.appendChild(rootElem);

            try {
                TransformerFactory trFactory = TransformerFactory.newInstance();
                Transformer tr = trFactory.newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");

                myFile = new File("data/output.xml");
                tr.transform(new DOMSource(DOM), new StreamResult(System.out));
                tr.transform(new DOMSource(DOM), new StreamResult(myFile));
            }
            catch (TransformerException transformerE) {
                System.out.println(transformerE.getMessage());
            }
//            catch (IOException ioE) {
//                System.out.println(ioE.getMessage());
//            }
        }
        catch (ParserConfigurationException parserConfigE) {
            System.out.println("XML Error: Error trying to instantiate DocumentBuilder " + parserConfigE);
        }
    }

}
