package com.jguest.petrol.clause;

import com.jguest.petrol.Stringable;

import static java.lang.String.format;

/**
 * @author jguest
 */
public class Select implements Stringable {

   private String[] columns;

   public Select(String[] columns) {
      this.columns = columns;
   }

   public String toPlainString() {
      return format("SELECT %s", String.join(", ", (CharSequence[]) columns));
   }
}
