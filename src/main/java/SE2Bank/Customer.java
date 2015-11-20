package SE2Bank;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */
public class Customer {
    private String id;
    private String name;
    private int initialBalance;
    private int upperBound;
    public Customer(String name,String id,int upperBound,int initialBalance){
        this.name=name;
        this.id=id;
        this.upperBound=upperBound;
        this.initialBalance=initialBalance;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(int initialBalance) {
        this.initialBalance = initialBalance;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
}
