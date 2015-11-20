package SE2Bank.Server;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import SE2Bank.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class JsonParser {
    private static String serverPort;
    private static String serverLoggingFile;
    public static List<Customer> readJson() throws IOException {
        JSONArray obj3=new JSONArray();
        JSONObject obj1=new JSONObject();

        //String s="[0,{\"1\": {\"2\" : {}]";
        JSONParser parser = new JSONParser();
        try {
            Object obj2=parser.parse(new FileReader("core.json"));
            String s=obj2.toString();
            Object obj =  parser.parse(s);
            obj1=(JSONObject)obj;
            serverPort=obj1.get("port").toString();
            serverLoggingFile=obj1.get("outLog").toString();
            obj3=(JSONArray)obj1.get("deposits");
        } catch (ParseException e) {
            e.printStackTrace();
        }
      //  System.out.println(a[2].toString());
        List<Customer> addedCostumers=new ArrayList<Customer>();
        for (int i = 0; i <obj3.size() ; i++) {
            JSONObject tempObj=(JSONObject)obj3.get(i);
            String customerName=tempObj.get("customer").toString();
            String id=tempObj.get("id").toString();

            Integer upperBound=Integer.parseInt(tempObj.get("upperBound").toString().replace(",", ""));
            Integer initialBalance=Integer.parseInt(tempObj.get("initialBalance").toString().replace(",", ""));
            addedCostumers.add(new Customer(customerName, id, upperBound, initialBalance));
        }
        return addedCostumers;
    }

    public static void writeJson(List<Customer> customerDeposits) throws IOException {
        JSONObject jsonRoot = new JSONObject();
        jsonRoot.put("port", serverPort);
        JSONArray jsonDepositsList = new JSONArray();
        for (int i = 0; i <customerDeposits.size() ; i++) {
            JSONObject jsonDepositsObject=new JSONObject();
            Customer currentCustomer=customerDeposits.get(i);
            jsonDepositsObject.put("upperBound",currentCustomer.getUpperBound());
            jsonDepositsObject.put("initialBalance",currentCustomer.getInitialBalance());
            jsonDepositsObject.put("id",currentCustomer.getId());
            jsonDepositsObject.put("customer", currentCustomer.getName());

            jsonDepositsList.add(jsonDepositsObject);
        }

        jsonRoot.put("deposits", jsonDepositsList);
        jsonRoot.put("outLog",serverLoggingFile);
        FileWriter file = new FileWriter("core.json");
        file.write(jsonRoot.toJSONString());
        file.flush();
        file.close();
    }

    public static String getServerLoggingFile() {
        return serverLoggingFile;
    }

    public static void setServerLoggingFile(String serverLoggingFile) {
        JsonParser.serverLoggingFile = serverLoggingFile;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
}