package data.models;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private String id;
    private Item item;
    private String  ownerId;
    private ArrayList<Bid> bids;
    private Date startTime;
    private Date endTime;
    private Double intialPrice;


   public Auction(String id,
   Item item,
   String ownerId,
   ArrayList<Bid> bids,
   Date startTime,
   Date  endTime,
   Double intialPrice
   ){
       this.id = id;
       this.item = item;
       this.ownerId = ownerId;
       this.bids = bids;
       this.startTime = startTime;
       this.endTime = endTime;
       this.intialPrice = intialPrice;
   }

   public Item getItem(){
       return this.item;
   }

   public String getOwner(){
       return this.ownerId;
   }

   public ArrayList<Bid> getBids(){
       return this.bids;
   }

   public Double getIntialPrice(){
       return this.intialPrice;
   }

   public Date getEndTime(){
       return this.endTime;
   }
}
