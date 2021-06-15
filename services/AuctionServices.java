package services;
import data.models.Auction;
import data.models.Bid;

public interface AuctionServices {
    String postAction(Auction action);
    String bid(String auctionId, Bid bid);
    Auction[] getAuctions();
    Auction getAuction(String id);
    Bid getBidDetail(String id);
}
