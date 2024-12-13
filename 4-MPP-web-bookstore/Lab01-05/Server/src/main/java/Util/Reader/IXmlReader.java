package Util.Reader;

import org.w3c.dom.Element;

import java.util.List;

public interface IXmlReader<T>
{
    List<T> loadEntities();
    static String getTextByTagName(Element element, String tag){
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}
