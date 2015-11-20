package SE2Bank.Server;

import SE2Bank.Customer;
import SE2Bank.JsonParser;
import SE2Bank.ProgramException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Peyman Zeynali on 11/14/2015.
 */
public class Server {
    protected int          id;
    protected int          port   = 1000;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(10);
    static List<Customer> customerDeposits=new ArrayList<Customer>();
    public Server() {
    }

    public void run() throws IOException {
        int counter=0;

        customerDeposits=readJson();

        while(!isStopped())
        {
            try
            {
              //  System.out.println("Waiting for client on port " +
                //        socket.getLocalPort() + "...");
                ServerSocket listener = new ServerSocket(port);

                Socket clientSocket = null;
                try {
                    clientSocket = listener.accept();
                } catch (IOException e) {
                    if(isStopped()) {
                        System.out.println("Server Stopped.") ;
                        break;
                    }
                    throw new RuntimeException(
                            "Error accepting client connection", e);
                }
                counter++;//*****

                new Thread(
                        new WorkerRunnable(clientSocket,counter)).start();//*****
              //  Thread.currentThread().run();
                listener.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                return;
             //   break;
            }catch(IOException e)
            {
                e.printStackTrace();
                return;
             //   break;
            }
        }
    }
    private synchronized boolean isStopped() {
        return this.isStopped;
    }
   ///////readJson
    public List<Customer> readJson(){
        try {
            return JsonParser.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /////////
    //////updateCustomerDeposits
    public static void updateCustomerDeposits(Deposit d)
    {
        for (int i = 0; i <customerDeposits.size() ; i++) {
            if(customerDeposits.get(i).getId().equals(d.getId()))
            {
                calcNewCustomerDeposit(i,d);
            }
        }
    }
////////
    ///////////calcNewCustomerDeposits
    private static void calcNewCustomerDeposit(int index, Deposit d) {
        ProgramException p = null;
        int newBalanceAmount = customerDeposits.get(index).getInitialBalance() + d.getAmount();
        Customer currentCustomer=customerDeposits.get(index);

        if (newBalanceAmount > currentCustomer.getUpperBound()) {
            throw new ProgramException("Customer: "+currentCustomer.getName()+" UpperBound is not observed!");
        } else {
            customerDeposits.get(index).setInitialBalance(newBalanceAmount);
            System.out.println("successfully "+currentCustomer.getName()+" updated!");
        }
    }

//    public List<Customer> addCustomerسFromJsonFile(String jsonString){
//        List<Customer> addedCostumers=new ArrayList<Customer>();
//        jsonString.
//
//
//    }
}
