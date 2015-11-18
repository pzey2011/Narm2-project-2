package SE2Bank.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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

    public Server() {
    }

    public void run() throws IOException {
        int counter=0;
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

}
