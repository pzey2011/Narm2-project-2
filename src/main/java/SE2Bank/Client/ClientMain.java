package SE2Bank.Client;

import SE2Bank.Server.Server;

import java.io.IOException;

/**
 * Created by Peyman Zeynali on 11/17/2015.
 */
public class ClientMain {
    public static void main(String[] args) {
        Client c=new Client();

           c=new Client();

           try {
                c.start();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


