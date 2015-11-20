package SE2Bank.Client;
import java.io.*;

import SE2Bank.EventLogger;
import SE2Bank.Transaction;
import SE2Bank.XmlParser;

import org.xml.sax.SAXException;
/**
 * Hello world!
 *
 */
import javax.xml.parsers.ParserConfigurationException;
import java.net.*;
import java.util.List;

public class Client
{
    private int  serverPort;
    private String serverName;
    private String terminalId;
    private String terminalType;
    private String outLogPath;
    private Transaction t;

    public Client(){

    }

    public void start() throws ClassNotFoundException {
       try{
           outLogPath=readOutLogPath();
           serverPort=readPort();
           serverName=readIP();
           terminalId=readTerminalId();
           terminalType=readTerminalType();

            Socket client = new Socket(serverName, serverPort);
         //   System.out.println("Just connected to "
           //         + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream oos =
                   new ObjectOutputStream(outToServer);

           List<Transaction> t=null;
            t=readTransactions();
           oos.writeObject(t.size());
           for (int i = 0; i < t.size(); i++) {
               System.out.println(t.get(i).getId());
               oos.writeObject(t.get(i).getId());
               System.out.println(t.get(i).getType());
               oos.writeObject(t.get(i).getType());
               System.out.println(t.get(i).getAmount());
               oos.writeObject(t.get(i).getAmount());
               System.out.println(t.get(i).getDepositId());
               oos.writeObject(t.get(i).getDepositId());
               oos.writeObject(terminalId);
               oos.writeObject(terminalType);
               oos.writeObject(serverName);
               oos.writeObject(serverPort);
               System.out.println("Salam client!"+i);
               InputStream inFromServer = client.getInputStream();
               ObjectInputStream ois = new ObjectInputStream(inFromServer);
               String logFromServer=ois.readObject().toString();
               EventLogger.writeClientLogFile(outLogPath,logFromServer);
           }


           //  String logFromServer=ois.readObject().toString();
          // EventLogger.writeClientLogFile(this);
         // out.writeUTF("Hello from "
        //           + client.getLocalSocketAddress());
//           try {
//               System.out.println(String.valueOf(in.readObject()));
//           } catch (ClassNotFoundException e) {
//               e.printStackTrace();
//           }

           //System.out.println("Server says " + in.readUTF());
            client.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
}

    private String readTerminalType() {
        return XmlParser.readTerminalType("terminal.xml");
    }

    private String readTerminalId() {
        return XmlParser.readTerminalId("terminal.xml");
    }

    private String readOutLogPath() {
        return XmlParser.readOutLogPath("terminal.xml");
    }

    private String readIP() {
        return XmlParser.readIP("terminal.xml");
    }

    private int readPort() {
        return XmlParser.readPort("terminal.xml");

    }


    public List<Transaction> readTransactions(){
        List<Transaction> transactions=null;

        try {
            transactions= XmlParser.readTransactions("terminal.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    public void sendRequest(){

    }
}


