package repository;

import java.util.ArrayList;

import data.Database;
import data.models.Auction;
import data.models.BaseModel;
import data.models.Bid;
import services.AuctionServices;

public class AuctionRepository   implements AuctionServices, Repository{

    private Database  db;

    public AuctionRepository(Database db){
        this.db = db;
    }
    @Override
    public String postAction(Auction action) {
        System.out.println("posted action");
        return null;
    }

    @Override
    public String bid(String auctionId, Bid bid) {
        System.out.println("Bid on auction");
        return null;
    }

    @Override
    public Auction[] getAuctions() {
        System.out.println("got list of acutoions");
        return null;
    }

    @Override
    public Auction getAuction(String id) {
        System.out.println("got auction");
        return null;
    }

    @Override
    public Bid getBidDetail(String id) {
        System.out.println("got bid detail");
        return null;
    }
    public void setUpRepo(String collectionName, ArrayList<Auction> collection) {
       this.db.put(collectionName, collection);
    }
   

    
}
