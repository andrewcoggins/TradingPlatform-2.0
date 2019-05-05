package brown.user.main.library;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import brown.platform.managers.IAccountManager;
import brown.platform.managers.IDomainManager;
import brown.platform.managers.IEndowmentManager;
import brown.platform.managers.IMarketManager;
import brown.platform.managers.ISimulationManager;
import brown.platform.managers.ITradeableManager;
import brown.platform.managers.IValuationManager;
import brown.platform.managers.IWorldManager;
import brown.platform.managers.library.AccountManager;
import brown.platform.managers.library.DomainManager;
import brown.platform.managers.library.EndowmentManager;
import brown.platform.managers.library.MarketManager;
import brown.platform.managers.library.SimulationManager;
import brown.platform.managers.library.TradeableManager;
import brown.platform.managers.library.ValuationManager;
import brown.platform.managers.library.WorldManager;
import brown.platform.market.IFlexibleRules;
import brown.platform.tradeable.ITradeable;
import brown.user.main.IEndowmentConfig;
import brown.user.main.IMarketConfig;
import brown.user.main.ISimulationConfig;
import brown.user.main.ITradeableConfig;
import brown.user.main.IValuationConfig;

public class ConfigRun {

  private List<ISimulationConfig> config;

  public ConfigRun(List<ISimulationConfig> config) {
    this.config = config;
  }

  public void run(Integer startingDelayTime, Integer simulationDelayTime,
      Integer numSimulations)
      throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, InterruptedException {
    ISimulationManager simulationManager = new SimulationManager();
    for (ISimulationConfig aConfig : this.config) {
      IWorldManager worldManager = new WorldManager();
      IDomainManager domainManager = new DomainManager();
      IEndowmentManager endowmentManager = new EndowmentManager();
      IAccountManager accountManager = new AccountManager();
      IValuationManager valuationManager = new ValuationManager();
      ITradeableManager tradeableManager = new TradeableManager();
      IMarketManager marketManager = new MarketManager();

      // tradeable manager should be easy so gonna start here.

      List<ITradeableConfig> tradeableConfig = aConfig.getTConfig();
      for (ITradeableConfig tConfig : tradeableConfig) {
        tradeableManager.createTradeables(tConfig.getTradeableName(),
            tConfig.getNumTradeables());
      }

      // valuation manager.

      List<IValuationConfig> vConfigs = aConfig.getVConfig();
      for (IValuationConfig vConfig : vConfigs) {
        List<String> tradeableNames = vConfig.getTradeableNames();
        Map<String, List<ITradeable>> valuationTradeables =
            new HashMap<String, List<ITradeable>>();
        for (String s : tradeableNames) {
          valuationTradeables.put(s, tradeableManager.getTradeables(s));
        }
        valuationManager.createValuation(vConfig.getValDistribution(),
            vConfig.getGenerators(), valuationTradeables);
      }

      // endowments also need a tradeable map, and a tradeable config.
      Map<String, List<ITradeable>> allTradeables =
          new HashMap<String, List<ITradeable>>();
      for (ITradeableConfig tConfig : tradeableConfig) {
        allTradeables.put(tConfig.getTradeableName(),
            tradeableManager.getTradeables(tConfig.getTradeableName()));
      }

      for (IEndowmentConfig eConfig : aConfig.getEConfig()) {

        endowmentManager.createEndowment(eConfig.getName(),
            eConfig.getEndowmentMapping(), eConfig.getFrequency(),
            allTradeables, eConfig.getMoney());
      }

      // for the market manager, gonna need the rules, map, and the mustInclude
      for (List<IMarketConfig> mConfigList : aConfig.getMConfig()) {
        List<IFlexibleRules> marketRules = new LinkedList<IFlexibleRules>();
        List<List<String>> marketTradeables = new LinkedList<List<String>>();
        for (IMarketConfig mConfig : mConfigList) {
          marketRules.add(mConfig.getRules());
          marketTradeables.add(mConfig.getTradeableNames());
        }
        marketManager.createSimultaneousMarket(marketRules, marketTradeables,
            allTradeables);
      }

      // todo: refactor account manager b/c endowments?

      domainManager.createDomain(tradeableManager, valuationManager,
          accountManager, endowmentManager);
      worldManager.createWorld(domainManager, marketManager);
      simulationManager.createSimulation(aConfig.getSimulationRuns(),
          worldManager);
      tradeableManager.lock();
      valuationManager.lock();
      simulationManager.lock();
      marketManager.lock();
    }

    simulationManager.runSimulation(startingDelayTime, simulationDelayTime,
        numSimulations);
  }
}
