package com.jguest.petrol.predicate;

import com.jguest.petrol.Stringable;

import static java.lang.String.format;

/**
 * @author jguest
 */
public class Condition implements Stringable {

   String key;
   Object value;
   String operator;

   public Condition(String key,
                    Object value,
                    String operator) {

      this.key = key;
      this.value = value;
      this.operator = operator;
   }

   @Override
   public String toPlainString() {
      return format("%s %s %s", key, operator, value);
   }
}
