package SE2Bank.Server;

import SE2Bank.EventLogger;
import SE2Bank.ProgramException;
import SE2Bank.Transaction;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by Peyman Zeynali on 11/17/2015.
 */
public class WorkerRunnable implements Runnable{

    protected static Socket clientSocket = null;
    protected int client   = 0;
    private String logContentText="";
    private String fileName="";
    public WorkerRunnable(Socket clientSocket,int client) {
        this.clientSocket = clientSocket;
        this.client   = client;
    }
    public void run() {
        try {
        InputStream is = clientSocket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(is);
            Transaction t=new Transaction();
            int sizeOfTransactions=Integer.valueOf(ois.readObject().toString());//receive
            System.out.println("size:"+sizeOfTransactions);
            for (int i = 0; i <sizeOfTransactions ; i++) {
                String id=ois.readObject().toString();
                //transaction id
                String type=ois.readObject().toString();
                //type
                int amount=Integer.valueOf(ois.readObject().toString());
                //amount
                String deposit=ois.readObject().toString();
                //deposit id

                //terminal id
                String terminalId=ois.readObject().toString();
                //terminal type
                String terminalType=ois.readObject().toString();


                //server IP
                String serverIP=ois.readObject().toString();
                //server Port
                String serverPort=ois.readObject().toString();
                //setContent text log file because successfulliness of the transaction
                System.out.println("Salam server!"+i);
                logContentText="transaction id:"+id+'\n'+"amount:"+amount+'\n'+"deposit id:"+deposit+'\n'+"terminal id:"+terminalId+'\n'+"terminal type:"+terminalType+'\n';
                Server.setLogContentText(logContentText);
                try
                {
                    Server.updateCustomerDeposits(new Deposit(deposit, type, amount));
                }catch(ProgramException p)
                {
                    System.out.println(p.getMessage());
                }

                OutputStream  out = clientSocket.getOutputStream();
                ObjectOutputStream oos=new ObjectOutputStream(out);
                oos.writeObject(Server.getLogContentText());
            }


          //  Server.updateJson();


//            oos.writeObject(s);                                 //send
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

public static void sendLogFileContext(String content) throws IOException {

}

}
