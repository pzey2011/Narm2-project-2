package SE2Bank.Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Peyman Zeynali on 11/17/2015.
 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected int client   = 0;

    public WorkerRunnable(Socket clientSocket,int client) {
        this.clientSocket = clientSocket;
        this.client   = client;
    }
    public void run() {
        try {
        InputStream is = clientSocket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(is);
            System.out.println(String.valueOf(ois.readObject()));
            OutputStream  out = clientSocket.getOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(out);
            String s=new String("Salam client!"+this.client);
            oos.writeObject(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
