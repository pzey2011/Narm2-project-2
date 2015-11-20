package SE2Bank;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import SE2Bank.Server.Deposit;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class JsonParser {

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
            System.out.println(obj1.get("deposits").toString());
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

}