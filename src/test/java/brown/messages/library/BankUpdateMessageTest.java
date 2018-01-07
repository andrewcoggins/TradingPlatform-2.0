package brown.messages.library;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

import brown.accounting.Account;
import brown.tradeable.library.Tradeable;

/**
 * test for the bank update message class. 
 * C
 * @author andrew
 *
 */
public class BankUpdateMessageTest {
  
  @Test 
  public void testBankUpdateMessage() { 
    // just test constructor.
    Account anAccount = new Account(0, 1000.0, new LinkedList<Tradeable>());
    Account anotherAccount = anAccount.copyAccount(); 
    anotherAccount.add(-100.0);
    BankUpdateMessage bu = new BankUpdateMessage(0, anAccount, anotherAccount);
    assertEquals(bu.oldAccount, anAccount); 
    assertEquals(bu.newAccount, anotherAccount); 
  }
  
}