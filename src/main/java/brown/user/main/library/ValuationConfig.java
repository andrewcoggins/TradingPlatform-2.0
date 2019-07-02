package brown.user.main.library;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import brown.user.main.IValuationConfig;

public class ValuationConfig implements IValuationConfig {
  
  private Constructor<?> valDistribution; 
  private Map<Constructor<?>, List<Double>> generators; 
  private List<String> itemNames; 
  
  public ValuationConfig(List<String> itemNames, Constructor<?> distCons, Map<Constructor<?>, List<Double>> generators) {
    this.itemNames = itemNames; 
    this.generators = generators; 
    this.valDistribution = distCons;
  }
  
  public Constructor<?> getValDistribution() {
    return this.valDistribution; 
  }
  
  public Map<Constructor<?>, List<Double>> getGenerators() {
    return this.generators;
  }
  
  public List<String> getItemNames() {
    return this.itemNames; 
  }

  @Override
  public String toString() {
    return "ValuationConfig [valDistribution=" + valDistribution
        + ", generators=" + generators + ", itemNames=" + itemNames
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
        prime * result + ((generators == null) ? 0 : generators.hashCode());
    result = prime * result
        + ((itemNames == null) ? 0 : itemNames.hashCode());
    result = prime * result
        + ((valDistribution == null) ? 0 : valDistribution.hashCode());
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
    ValuationConfig other = (ValuationConfig) obj;
    if (generators == null) {
      if (other.generators != null)
        return false;
    } else if (!generators.equals(other.generators))
      return false;
    if (itemNames == null) {
      if (other.itemNames != null)
        return false;
    } else if (!itemNames.equals(other.itemNames))
      return false;
    if (valDistribution == null) {
      if (other.valDistribution != null)
        return false;
    } else if (!valDistribution.equals(other.valDistribution))
      return false;
    return true;
  }
  
}
