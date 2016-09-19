package com.jguest.petrol.clause;

import com.jguest.petrol.Stringable;
import com.jguest.petrol.predicate.Condition;

/**
 * @author jguest
 */
public class Where implements Stringable {

   private Condition condition;

   public Where(Condition condition) {
      this.condition = condition;
   }

   @Override
   public String toPlainString() {
      return String.format("WHERE %s", condition.toPlainString());
   }
}
