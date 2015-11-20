package SE2Bank;
import SE2Bank.Client.Client;
import SE2Bank.Server.JsonParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Created by Peyman Zeynali on 11/20/2015.
 */
public class EventLogger {
    public static void writeClientLogFile(Client c){

    }
    public static void writeServerLogFile(String fileName,String content) throws IOException {

        File file = new File(fileName);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

        System.out.println("Done");

    }
}
