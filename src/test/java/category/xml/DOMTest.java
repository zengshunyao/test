package category.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lenovo on 2015/5/22.
 */
public class DOMTest {
    /* build a DocumentBuilderFactory */
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory
            .newInstance();

    public static void main(String[] args) {
        DOMTest parser = new DOMTest();
        Document document = parser.parse(ClassLoader.getSystemResource("").getFile().substring(1) + "books.xml");
        /* get root element */
        Element rootElement = document.getDocumentElement();

        /* get all the nodes whose name is book */
        NodeList nodeList = rootElement.getElementsByTagName("book");
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                /* get every node */
                Node node = nodeList.item(i);
                /* get the next lever's ChildNodes */
                NodeList nodeList2 = node.getChildNodes();
                NamedNodeMap namedNodeMap = node.getAttributes();
                //获得属性
                for (int k = 0; k < namedNodeMap.getLength(); k++)
                    System.out.println(namedNodeMap.item(k).getNodeName() + "=" + namedNodeMap.item(k).getNodeValue());
                //获得子节点
                for (int j = 0; j < nodeList2.getLength(); j++) {
                    Node node2 = nodeList2.item(j);
                    if (node2.hasChildNodes()) {
                        System.out.println(node2.getNodeName() + ":"
                                + node2.getFirstChild().getNodeValue());
                    }
                }
            }
        }
    }

    /* Load and parse XML file into DOM */
    public Document parse(String filePath) {
        Document document = null;
        try {
            /* DOM parser instance */
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            /* parse an XML file into a DOM tree */
            document = builder.parse(new File(filePath));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
