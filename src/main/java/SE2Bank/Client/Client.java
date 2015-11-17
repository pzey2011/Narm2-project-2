package SE2Bank.Client;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Hello world!
 *
 */
import java.net.*;
public class Client
{
    private int  port =1000;
    private String serverName="localhost";
    public Client(){}
    public void start()
    {
       try{
            Socket client = new Socket(serverName, port);
         //   System.out.println("Just connected to "
           //         + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream oos =
                   new ObjectOutputStream(outToServer);
            String s=new String("Salam server!");
            oos.writeObject(s);
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
}


