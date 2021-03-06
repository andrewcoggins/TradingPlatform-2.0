package brown.communication.messages.library;

import brown.communication.messages.IStatusMessage;
import brown.user.agent.IAgentBackend;

public abstract class AbsStatusMessage extends AbsServerToAgentMessage implements IStatusMessage {
  
  private String error; 
  
  public AbsStatusMessage() {
    super(null, null); 
    this.error = null; 
  }
  
  public AbsStatusMessage(Integer messageID, Integer agentID, String error) {
    super(messageID, agentID);
    this.error = error; 
  }
  
  @Override
  public void agentDispatch(IAgentBackend agent) {
    agent.onStatusMessage(this); 
  }
  
  @Override
  public String getStatus() {
    return this.error; 
  }
  
  @Override
  public String toString() {
    return "AbsErrorMessage [error=" + error + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((error == null) ? 0 : error.hashCode());
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
    AbsStatusMessage other = (AbsStatusMessage) obj;
    if (error == null) {
      if (other.error != null)
        return false;
    } else if (!error.equals(other.error))
      return false;
    return true;
  }
  
  
}
