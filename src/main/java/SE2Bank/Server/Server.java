package SE2Bank.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Peyman Zeynali on 11/14/2015.
 */
public class Server {
    protected int          port   = 1000;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    public Server() {
    }

    public void run() throws IOException {
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
                InputStream is=clientSocket.getInputStream();
                ObjectInputStream ois=new ObjectInputStream(is);
                try {

                    System.out.println(String.valueOf(ois.readObject()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                OutputStream out=clientSocket.getOutputStream();
                ObjectOutputStream oos=new ObjectOutputStream(out);
                String s=new String("Salam client!");
                oos.writeObject(s);

             /*   System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());*/
              /*  DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to "
                        + server.getLocalSocketAddress() + "\nGoodbye!");*/
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
