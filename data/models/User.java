package data.models;
public class User{
    private String id;
    private String username;
    private String password;
    private int totalItemsSold;
    private int totalAmountSpent;
    private int totalAuctionsWon;

    private void setId(String id){
        this.id = id;
    }
    public String  getId(){
        return this.id;
    }
    public String  getUsername(){
        return this.username;
    }

    public User(String id,
    String username, 
    String password, 
    int totalItemsSold, 
    int totalAmountSpent, 
    int totalAuctionsWon  ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.totalItemsSold = totalItemsSold;
        this.totalAmountSpent = totalAmountSpent;
        this.totalAuctionsWon = totalAuctionsWon;
    }
    public String getPassword() {
        return this.password;
    }
}