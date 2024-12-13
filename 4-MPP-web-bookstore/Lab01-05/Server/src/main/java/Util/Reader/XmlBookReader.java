package Util.Reader;

import Domain.Book;
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

public class XmlBookReader implements IXmlReader<Book>
{
    private String fileName;

    public XmlBookReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Book> loadEntities()
    {
        List<Book> books = new ArrayList<>();

        try{
            DocumentBuilderFactory readerFacotry = DocumentBuilderFactory.newInstance();
            DocumentBuilder reader = readerFacotry.newDocumentBuilder();
            Document xmlFile = reader.parse("./data/books.xml");
            xmlFile.getDocumentElement().normalize();

            Element root = xmlFile.getDocumentElement();
            NodeList childNodes = root.getChildNodes();

            for (int index = 0; index < childNodes.getLength(); index++){
                Node child = childNodes.item(index);
                if(child instanceof Element)
                {
                    Element bookElement = (Element) child;
                    String name = bookElement.getAttribute("name");

                    String country = IXmlReader.getTextByTagName(bookElement, "author");
                    String ISBN = IXmlReader.getTextByTagName(bookElement, "isbn");
                    String ID = IXmlReader.getTextByTagName(bookElement, "id");

                    Book book = new Book(name, country, Integer.parseInt(ISBN));
                    book.setID(Long.parseLong(ID));
                    books.add(book);
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }

        return books;
    }


}
