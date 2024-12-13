package Util.Writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface IXmlWriter<T>
{
    void save(T entity);
    static void appendChildToElement(Document doc, Element parent, String tag, String value){
        Element element = doc.createElement(tag);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
