package SE2Bank.Server;

import SE2Bank.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */
public class Deposit {
    private int transactionId;
    private String type;
    private int amount;
    private int id;
    private String serverloggingFile;
    private List<Customer> customers;
    public Deposit(){
        serverloggingFile="server.out";
    }
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getServerloggingFile() {
        return serverloggingFile;
    }

    public void setServerloggingFile(String serverloggingFile) {
        this.serverloggingFile = serverloggingFile;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < customers.size(); i++) {

            if (i == 0)
                result.concat("deposits: [\n{\n     customer:" + customers.get(i).getName() + "," + "\n     id:" + customers.get(i).getId() + "," + "\n     initialBalance:" + customers.get(i).getInitialBalance() + "," + "\n     upperBound:" + customers.get(i).getUpperBound() + ",\n},");
            else if (i + 1 == customers.size())
                result.concat("{\n     customer:" + customers.get(i).getName() + "," + "\n     id:" + customers.get(i).getId() + "," + "\n     initialBalance:" + customers.get(i).getInitialBalance() + "," + "\n     upperBound:" + customers.get(i).getUpperBound() + "\n}\n],\noutLog:" + serverloggingFile);
            else
                result.concat("{\n     customer:" + customers.get(i).getName() + "," + "\n     id:" + customers.get(i).getId() + "," + "\n     initialBalance:" + customers.get(i).getInitialBalance() + "," + "\n     upperBound:" + customers.get(i).getUpperBound() + ",\n},");


        }
        return result;
    }


}
