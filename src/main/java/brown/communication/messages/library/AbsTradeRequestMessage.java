package brown.communication.messages.library;

import java.util.List;

import brown.communication.bid.library.BidType;
import brown.communication.messages.ITradeRequestMessage;
import brown.platform.whiteboard.IWhiteboard;

public abstract class AbsTradeRequestMessage extends AbsServerToAgentMessage implements ITradeRequestMessage {
  
  private Integer agentID; 
  private BidType bidType; 
  private List<String> tradeableNames; 
  
  public AbsTradeRequestMessage() {
    super(null); 
  }
  
  public AbsTradeRequestMessage(Integer messageID, Integer agentID, BidType bidType, List<String> tradeableNames) {
    super(messageID);
    this.agentID = agentID; 
    this.bidType = bidType; 
    this.tradeableNames = tradeableNames; 
  }
  
  public Integer getAgentID() {
    return this.agentID; 
  }
  
  public BidType getBidType() {
    return this.bidType; 
  }
  
  public List<String> getTradeableTypes() {
    return this.tradeableNames; 
  }

  public void addInformation(IWhiteboard whiteboard) {
    
  }
  
  @Override
  public String toString() {
    return "AbsTradeRequestMessage [agentID=" + agentID + ", bidType=" + bidType
        + ", tradeableNames=" + tradeableNames + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((agentID == null) ? 0 : agentID.hashCode());
    result = prime * result + ((bidType == null) ? 0 : bidType.hashCode());
    result = prime * result
        + ((tradeableNames == null) ? 0 : tradeableNames.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbsTradeRequestMessage other = (AbsTradeRequestMessage) obj;
    if (agentID == null) {
      if (other.agentID != null)
        return false;
    } else if (!agentID.equals(other.agentID))
      return false;
    if (bidType != other.bidType)
      return false;
    if (tradeableNames == null) {
      if (other.tradeableNames != null)
        return false;
    } else if (!tradeableNames.equals(other.tradeableNames))
      return false;
    return true;
  }
  
  
}

