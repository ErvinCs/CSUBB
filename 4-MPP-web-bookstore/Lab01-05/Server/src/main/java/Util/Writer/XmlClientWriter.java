package Util.Writer;

import Domain.Client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XmlClientWriter implements IXmlWriter<Client>
{

    private String fileName;

    public XmlClientWriter(String fileName)
    {
        this.fileName = fileName;
    }

    public void save(Client entity)
    {
        try {
            DocumentBuilderFactory saverFacotry = DocumentBuilderFactory.newInstance();
            DocumentBuilder saver = saverFacotry.newDocumentBuilder();
            Document xmlFile = saver.parse("./data/clients.xml");

            xmlFile.getDocumentElement().normalize();
            Element root = xmlFile.getDocumentElement();

            Element client = xmlFile.createElement("client");
            client.setAttribute("name", entity.getName());
            IXmlWriter.appendChildToElement(xmlFile, client, "country", entity.getCountry());
            IXmlWriter.appendChildToElement(xmlFile, client, "id", Objects.toString(entity.getID(), null));

            root.appendChild(client);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root), new StreamResult("./data/clients.xml"));

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e)
        {
        e.printStackTrace();
        }
    }
}
