package Util.Reader;

import Domain.Client;
import Util.Writer.IXmlWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class XmlClientReader implements IXmlReader<Client>
{
    private String fileName;

    public XmlClientReader(String fileName)
    {
        this.fileName = fileName;
    }

    public List<Client> loadEntities()
    {
        List<Client> clients = new ArrayList<>();

        File file = new File("./data/clients.xml");
        if (!file.exists() || file.length() == 0)
            return null;

        try {
            DocumentBuilderFactory readerFacotry = DocumentBuilderFactory.newInstance();
            DocumentBuilder reader = readerFacotry.newDocumentBuilder();
            Document xmlFile = reader.parse("./data/clients.xml");
            xmlFile.getDocumentElement().normalize();

            Element root = xmlFile.getDocumentElement();
            NodeList childNodes = root.getChildNodes();

            //TODO
            int length = childNodes.getLength();
//            List<Client> clients = IntStream.range(0, childNodes.getLength())
//                    .filter(Client.class::isInstance )
//                    .map(item -> {
//                        Element e = (Element) childNodes;
//                        String name = e.getAttribute("name");
//                        String country = e.getAttribute("country");
//                        Long ID = Long.parseLong(e.getAttribute("id"));
//                        Client c = new Client(name, country);
//                        c.setID(ID);
//                        //clients.add(c);
//                        //return c;
//                    })
//                    .collect(Collectors.toList());

            for (int index = 0; index < childNodes.getLength(); index++){
                Node child = childNodes.item(index);
                if(child instanceof Element)
                {
                    Element clientElement = (Element) child;
                    String name = clientElement.getAttribute("name");

                    String country = IXmlReader.getTextByTagName(clientElement, "country");
                    String ID = IXmlReader.getTextByTagName(clientElement, "id");

                    Client client = new Client(name, country);
                    client.setID(Long.parseLong(ID));
                    clients.add(client);
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e)
    {
        e.printStackTrace();
    }
        return clients;
    }
}
