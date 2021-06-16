package repository;

import java.util.ArrayList;

import data.Database;
import data.models.Auction;
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
    @Override
    public ArrayList<Auction> getAuctions() {
        ArrayList<Auction> auctions = (ArrayList<Auction>) this.db.get("auctions");
        return auctions;
    }
    @Override
    public void addAuction(Auction auction) {
        ArrayList<Auction> auctions = (ArrayList<Auction>) this.db.get("auctions");
        auctions.add(auction);
        System.out.println("Auction posted");
        
    }
    @Override
    public boolean bid(Bid bid) {
        ArrayList<Auction> auctions = (ArrayList<Auction>) this.db.get("auctions");
        
        boolean isAuctionIdCorrect = false;
        // find the auction by id 
        for(Auction auction: auctions){
            if(auction.getId().equals(bid.getAuctionId())){
                // bid on auction here
                // auction.addBidOnAuction(new Bid((String)db.get("currentUserName"), auction.getId(), Double.parseDouble( bidAmoutInput)));
                auction.getBids().add(bid);
                isAuctionIdCorrect = true;
                // list the auctions , like trying to verify
                // handelListOfAuctions();
            }
        }
        return isAuctionIdCorrect;
    }
   

    
}
