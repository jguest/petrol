package com.jguest.petrol;

/**
 * @author jguest
 */
public enum Keywords implements Stringable {

   AND, OR;

   @Override
   public String toPlainString() {
      return this.toString();
   }
}
