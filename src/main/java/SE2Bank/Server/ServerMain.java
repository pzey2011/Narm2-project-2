package SE2Bank.Server;

import SE2Bank.Server.Server;

import java.io.IOException;

/**
 * Created by Peyman Zeynali on 11/17/2015.
 */
public class ServerMain {
    public static void main(String[] args) {
        Server s=new Server();
        try {
            s.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
