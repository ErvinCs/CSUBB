package Util.Writer;

import Domain.Rental;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.Objects;

public class XmlRentalWriter implements IXmlWriter<Rental>
{

    private String fileName;

    public XmlRentalWriter(String fileName) {
        this.fileName = fileName;
    }

    public void save(Rental entity)
    {
        try {
            DocumentBuilderFactory saverFacotry = DocumentBuilderFactory.newInstance();
            DocumentBuilder saver = saverFacotry.newDocumentBuilder();
            Document xmlFile = saver.parse("./data/rentals.xml");

            xmlFile.getDocumentElement().normalize();
            Element root = xmlFile.getDocumentElement();

            Element rental = xmlFile.createElement("rental");
            rental.setAttribute("bookid", Objects.toString(entity.getBookID(),null));
            IXmlWriter.appendChildToElement(xmlFile, rental, "clientid", Objects.toString(entity.getClientID(),null));
            IXmlWriter.appendChildToElement(xmlFile, rental, "id", Objects.toString(entity.getID(), null));

            root.appendChild(rental);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root), new StreamResult("./data/rentals.xml"));
        }catch (ParserConfigurationException | SAXException | IOException | TransformerException e)
        {
            e.printStackTrace();
        }
    }
}
