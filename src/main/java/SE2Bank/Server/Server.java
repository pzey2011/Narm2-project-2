package SE2Bank.Server;

import SE2Bank.Customer;
import SE2Bank.EventLogger;
import SE2Bank.ProgramException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private static boolean      isSuccessfull    = true;
    static List<Customer> customerDeposits=new ArrayList<Customer>();
    private static String logContentText=null;

    public Server() {
    }

    public static void setLogContentText(String logContentText) {
        Server.logContentText = logContentText;
    }

    public static String getLogContentText() {
        return logContentText;
    }

    public void run() throws IOException {
        int counter=0;

        customerDeposits=readJsonAddCustomers();

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

   ///////readJsonAddCustomers
    public List<Customer> readJsonAddCustomers(){
        try {
            return JsonParser.readJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /////////

    //////updateCustomerDeposits
    public static void updateCustomerDeposits(Deposit d) throws IOException {
        for (int i = 0; i <customerDeposits.size() ; i++) {
            if(customerDeposits.get(i).getId().equals(d.getId()))
            {
                calcNewCustomerDeposit(i,d);
            }
        }
    }
////////
    ///////////calcNewCustomerDeposits
    public static void calcNewCustomerDeposit(int index, Deposit d) throws IOException {
        ProgramException p = null;
        int newBalanceAmount = customerDeposits.get(index).getInitialBalance() + d.getAmount();
        Customer currentCustomer=customerDeposits.get(index);

        if (newBalanceAmount > currentCustomer.getUpperBound()) {
            isSuccessfull=false;
            eventLog();
            throw new ProgramException("Customer: "+currentCustomer.getName()+" UpperBound was not observed!");

        } else {
            customerDeposits.get(index).setInitialBalance(newBalanceAmount);
            eventLog();
            System.out.println("successfully "+currentCustomer.getName()+" updated!");
        }
    }
    ////////////
    ////////////updateJSON
    public static void updateJson() throws IOException {
        JsonParser.writeJson(customerDeposits);
    }
    public static void eventLog() throws IOException {
        String content=logContentText+"successfull:"+Server.isSuccessfull()+'\n';
        EventLogger.writeServerLogFile(JsonParser.getServerLoggingFile(),content);
    }

    public static boolean isSuccessfull() {
        return isSuccessfull;
    }


//    public List<Customer> addCustomerسFromJsonFile(String jsonString){
//        List<Customer> addedCostumers=new ArrayList<Customer>();
//        jsonString.
//
//
//    }
}
