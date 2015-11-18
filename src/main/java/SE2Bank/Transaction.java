package SE2Bank;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */
public class Transaction {
    private int depositId;
    private String type;
    private int amount;
    private int id;
    public Transaction(){}
    public Transaction(int id, String type, int amount, int depositId){
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.depositId= depositId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDepositId() {
        return depositId;
    }

    public void setDepositId(int id) {
        this.depositId = id;
    }
}
