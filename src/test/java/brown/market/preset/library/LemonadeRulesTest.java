package brown.market.preset.library;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import brown.rules.library.LemonadeAllocation;
import brown.rules.library.LemonadeAnonymous;
import brown.rules.library.LemonadePayment;
import brown.rules.library.LemonadeQuery;
import brown.rules.library.OneShotActivity;
import brown.rules.library.OneShotTermination;
import brown.rules.library.XRoundTermination;

/**
 * test the lemonade rules. 
 * I
 * @author andrew
 *
 */
public class LemonadeRulesTest {
  
  @Test
  public void testLemonadeRules() {
    LemonadeRules lemonadeRules = new LemonadeRules();
    assertEquals(lemonadeRules.aRule, new LemonadeAllocation());
    assertEquals(lemonadeRules.pRule, new LemonadePayment());
    assertEquals(lemonadeRules.qRule, new LemonadeQuery());
    assertEquals(lemonadeRules.actRule, new OneShotActivity());
    assertEquals(lemonadeRules.infoPolicy, new LemonadeAnonymous());
    assertEquals(lemonadeRules.innerTCondition, new OneShotTermination());
    assertEquals(lemonadeRules.outerTCondition, new XRoundTermination());
  }
}