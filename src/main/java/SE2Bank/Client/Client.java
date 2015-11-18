package SE2Bank.Client;
import java.io.*;

import SE2Bank.Transaction;
import SE2Bank.XmlParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    private int terminalId;
    private String terminalType;
    private String outLogPath;
    private Transaction t;

    public Client(){
        serverName = "localhost";
        serverPort =1000;
    }

    public void start()
    {
       try{
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
           }
            InputStream inFromServer = client.getInputStream();
            ObjectInputStream in = new ObjectInputStream(inFromServer);
         // out.writeUTF("Hello from "
        //           + client.getLocalSocketAddress());
           try {
               System.out.println(String.valueOf(in.readObject()));
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }

           //System.out.println("Server says " + in.readUTF());
            client.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
}
    public void logToTerminal(){

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


