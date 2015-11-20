package SE2Bank.Server;

import SE2Bank.Transaction;

import java.io.*;
import java.net.Socket;
import java.util.List;

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
            Transaction t=new Transaction();
            int sizeOfTransactions=Integer.valueOf(ois.readObject().toString());//receive
            System.out.println("size:"+sizeOfTransactions);
            for (int i = 0; i <sizeOfTransactions ; i++) {
                String id=ois.readObject().toString();
                System.out.println("transaction id:"+id);
                String type=ois.readObject().toString();
                System.out.println("type:"+type);
                int amount=Integer.valueOf(ois.readObject().toString());
                System.out.println("amount:"+amount);
                String deposit=ois.readObject().toString();
                System.out.println("deposit:"+deposit);
                Server.updateCustomerDeposits(new Deposit(deposit,type,amount));
            }
 //           OutputStream  out = clientSocket.getOutputStream();
//            ObjectOutputStream oos=new ObjectOutputStream(out);
//            String s=new String("Salam client!"+this.client);
//            oos.writeObject(s);                                 //send
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
