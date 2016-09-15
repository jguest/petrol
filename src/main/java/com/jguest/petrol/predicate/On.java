package com.jguest.petrol.predicate;

import com.jguest.petrol.Stringable;
import com.jguest.petrol.predicate.Criteria;

/**
 * @author jguest
 */
public class On implements Stringable {

   private String criteria;

   public On(String criteria) {
      this.criteria = criteria;
   }

   @Override
   public String toPlainString() {
      return String.format("ON %s", criteria);
   }
}
