package com.jguest.petrol;

/**
 * @author jguest
 */
public class Petrol {

   /**
    *
    * @param columns
    * @return
    */
   public static Query select(String... columns) {
      return new Query().select(columns);
   }
}
