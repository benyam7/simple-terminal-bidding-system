package data.models;

import java.util.UUID;

public class Bid {
    private String id;
    private String userName;
    private String auctionId;
    private double amount;


    public Bid(String userName, String auctionId, Double amount){
        this.id =  UUID.randomUUID().toString();
        this.userName = userName;
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public String getAuctionId(){
        return this.auctionId;
    }


}
