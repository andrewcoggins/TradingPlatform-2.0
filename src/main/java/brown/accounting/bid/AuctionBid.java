package brown.accounting.bid;

import java.util.Map;

import brown.tradeable.ITradeable;

/**
 * A map from Tradeables to a Bids.
 * @author andrew, modified by kerry
 *
 */
public class AuctionBid extends AbsBid {
  
  public final Map<ITradeable, Double> bids;
  
  /**
   * For Kryo 
   * DO NOT USE
   */
  public AuctionBid() {
    this.bids = null;
  }
  
  public AuctionBid(Map<ITradeable, Double> bids) {
    this.bids = bids; 
  }
  
  @Override
  public String toString() {
    return "SimpleBid [bids=" + bids + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bids == null) ? 0 : bids.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    return(obj instanceof AuctionBid && 
        ((AuctionBid) obj).bids.equals(this.bids));
  }
   
}