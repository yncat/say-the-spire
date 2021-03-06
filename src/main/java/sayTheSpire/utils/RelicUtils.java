package sayTheSpire.utils;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.shop.StoreRelic;
import sayTheSpire.TextParser;

public class RelicUtils {

  public static String getRelicShort(AbstractRelic relic) {
    if (relic.counter >= 0) {
      return relic.name + " (" + relic.counter + ")";
    }
    return relic.name;
  }

  public static String getRelicShort(StoreRelic relic) {
    return getRelicShort(relic.relic) + ", price: " + relic.price;
  }

  public static String getRelicDescription(AbstractRelic relic) {
    return TextParser.parse(relic.description, relic);
  }

  public static String getRelicFlavorText(AbstractRelic relic) {
    return TextParser.parse(relic.flavorText, relic);
  }
}
