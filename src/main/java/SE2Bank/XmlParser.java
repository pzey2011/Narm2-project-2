package SE2Bank;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.*;

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
    public static int readPort(String fileName) {
        NodeList nList = null;
        int port=0;
        try {
            nList = readXmlFileByTagName(fileName, "server");
            Node node = nList.item(0);
            Element eElement=(Element)node;
            port=Integer.valueOf(eElement.getAttribute("port").toString());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return port;
    }

    public static String readIP(String fileName) {
        NodeList nList = null;
        String ip=null;
        int port=0;
        try {
            nList = readXmlFileByTagName(fileName, "server");
            Node node = nList.item(0);
            Element eElement=(Element)node;
            ip=eElement.getAttribute("ip").toString();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String readOutLogPath(String fileName) {
        NodeList nList = null;
        String path=null;
        int port=0;
        try {
            nList = readXmlFileByTagName(fileName, "outLog");
            Node node = nList.item(0);
            Element eElement=(Element)node;
            path=eElement.getAttribute("path").toString();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return path;
    }
}
