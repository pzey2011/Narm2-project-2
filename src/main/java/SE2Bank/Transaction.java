package SE2Bank;

/**
 * Created by Peyman Zeynali on 11/18/2015.
 */
public class Transaction {
    private int depositId;
    private String type;
    private int amount;
    private int id;
    private int  serverPort;
    private String serverName;
    private int terminalId;
    private String terminalType;
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

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
