package services;
import java.util.ArrayList;

import data.models.Auction;
import data.models.Bid;

public interface AuctionServices {
    String postAction(Auction action);
    boolean bid(Bid bid);
    ArrayList<Auction> getAuctions();
    Auction getAuction(String id);
    void addAuction(Auction auction);
    Bid getBidDetail(String id);
}
