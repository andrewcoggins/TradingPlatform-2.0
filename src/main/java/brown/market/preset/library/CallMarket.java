package brown.market.preset.library;

import brown.market.preset.AbsMarketPreset;
import brown.rules.library.BlankAllocation;
import brown.rules.library.CallMarketActivity;
import brown.rules.library.CallMarketInformation;
import brown.rules.library.CallMarketPayment;
import brown.rules.library.CallMarketQuery;
import brown.rules.library.NoRecordKeeping;
import brown.rules.library.OneGrouping;
import brown.rules.library.OneShotTermination;
import brown.rules.library.XTimeTermination;

public class CallMarket extends AbsMarketPreset {
  /**
   * some of these are guesses.
   * need to pass in the market internal state, 
   * or otherwise delete it from this constructor.
   */
  public CallMarket(double minutes) {
    super(new BlankAllocation(),
        new CallMarketPayment(),
        new CallMarketQuery(), 
        new OneGrouping(),
        new CallMarketActivity(),
        new CallMarketInformation(),
        new OneShotTermination(), 
        new XTimeTermination(minutes),
        new NoRecordKeeping());
  }
}