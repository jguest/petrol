package com.jguest.petrol.clause;

import com.jguest.petrol.Stringable;
import com.jguest.petrol.predicate.Conditions;

/**
 * @author jguest
 */
public class Where implements Stringable {

   private Conditions conditions;

   public Where(Conditions conditions) {
      this.conditions = conditions;
   }

   @Override
   public String toPlainString() {
      return String.format("WHERE %s", conditions.toPlainString());
   }
}
