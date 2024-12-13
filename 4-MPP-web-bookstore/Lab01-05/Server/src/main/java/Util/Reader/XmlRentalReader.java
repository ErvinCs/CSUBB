package Util.Reader;

import Domain.Rental;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlRentalReader implements IXmlReader<Rental>
{
    private String fileName;

    public XmlRentalReader(String fileName)
    {
        this.fileName = fileName;
    }

    public List<Rental> loadEntities()
    {
        List<Rental> rentals = new ArrayList<>();

        try {
            DocumentBuilderFactory readerFacotry = DocumentBuilderFactory.newInstance();
            DocumentBuilder reader = readerFacotry.newDocumentBuilder();
            Document xmlFile = reader.parse("./data/rentals.xml");
            xmlFile.getDocumentElement().normalize();

            Element root = xmlFile.getDocumentElement();
            NodeList childNodes = root.getChildNodes();

            for (int index = 0; index < childNodes.getLength(); index++){
                Node child = childNodes.item(index);
                if(child instanceof Element)
                {
                    Element rentalElement = (Element) child;
                    String bookID = rentalElement.getAttribute("bookid");

                    String clientID = IXmlReader.getTextByTagName(rentalElement, "clientid");
                    String ID = IXmlReader.getTextByTagName(rentalElement, "id");

                    Rental rental = new Rental(Long.parseLong(bookID), Long.parseLong(clientID));
                    rental.setID(Long.parseLong(ID));
                    rentals.add(rental);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }



        return rentals;
    }


}
