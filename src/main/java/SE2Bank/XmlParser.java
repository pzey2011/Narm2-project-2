package SE2Bank;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */
public class XmlParser {
    public static NodeList readXmlFileByTagName(String fileName,String tagName) throws ParserConfigurationException, IOException, SAXException {
        DOMParser parser = new DOMParser();
        parser.parse("terminal.xml");
        Document doc = parser.getDocument();

        NodeList nList = doc.getElementsByTagName(tagName);

       return  nList ;

//	System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

    }
    public static List<Transaction> readTransactions(String fileName) throws ParserConfigurationException, SAXException, IOException {
        List<Transaction> transactions=new ArrayList<Transaction>();
        NodeList nList = null;
        nList = readXmlFileByTagName(fileName, "transaction");
       

        for (int i = 0; i <nList.getLength() ; i++) {
            Node transaction =nList.item(i);
            NamedNodeMap transAttrs = transaction.getAttributes();
        //	System.out.println("annnn " + eElement.getElementsByTagName("customerNumber").item(0).getTextContent());
    //        getNodeAttr(String tagName, String attrName, NodeList nodes )
         int transactionId= Integer.valueOf(transAttrs.getNamedItem("id").getTextContent());
            String type=transAttrs.getNamedItem("type").getTextContent();
            int amount=Integer.valueOf(transAttrs.getNamedItem("amount").getTextContent().replace(",", ""));
            int depositId=Integer.valueOf(transAttrs.getNamedItem("deposit").getTextContent());
            transactions.add(new Transaction(transactionId,type,amount,depositId));
        }
        return transactions;
    }
    protected static Node getNode(String tagName, NodeList nodes) {
        for ( int x = 0; x < nodes.getLength(); x++ ) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }
    protected String getNodeAttr(String tagName, String attrName, NodeList nodes ) {
        for ( int x = 0; x < nodes.getLength(); x++ ) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++ ) {
                    Node data = childNodes.item(y);
                    if ( data.getNodeType() == Node.ATTRIBUTE_NODE ) {
                        if ( data.getNodeName().equalsIgnoreCase(attrName) )
                            return data.getNodeValue();
                    }
                }
            }
        }

        return "";
    }
}
