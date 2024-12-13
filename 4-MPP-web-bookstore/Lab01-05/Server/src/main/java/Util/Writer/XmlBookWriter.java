package Util.Writer;

import Domain.Book;

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
import java.io.IOException;
import java.util.Objects;

public class XmlBookWriter implements IXmlWriter<Book>
{

    private String fileName;

    public XmlBookWriter(String fileName)
    {
        this.fileName = fileName;
    }

    public void save(Book entity)
    {
        try {
            DocumentBuilderFactory saverFacotry = DocumentBuilderFactory.newInstance();
            DocumentBuilder saver = saverFacotry.newDocumentBuilder();
            Document xmlFile = saver.parse("./data/books.xml");

            xmlFile.getDocumentElement().normalize();
            Element root = xmlFile.getDocumentElement();

            Element book = xmlFile.createElement("book");
            book.setAttribute("name", entity.getName());
            IXmlWriter.appendChildToElement(xmlFile, book, "author", entity.getAuthor());
            IXmlWriter.appendChildToElement(xmlFile, book, "isbn", String.valueOf(entity.getISBN()));
            IXmlWriter.appendChildToElement(xmlFile, book, "id", Objects.toString(entity.getID(), null));

            root.appendChild(book);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(root), new StreamResult("./data/books.xml"));
        }catch (ParserConfigurationException | SAXException | IOException | TransformerException e)
        {
            e.printStackTrace();
        }
    }
}
