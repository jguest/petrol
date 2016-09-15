package com.jguest.petrol.clause;

import com.jguest.petrol.Stringable;
import com.jguest.petrol.predicate.Joins;

import static java.lang.String.format;
import static java.lang.String.join;

/**
 * @author jguest
 */
public class From implements Stringable {

   private String[] tables;
   private Joins joins;

   public From(String[] tables) {
      this.tables = tables;
   }

   public void joins(Joins joins) {
      this.joins = joins;
   }

   @Override
   public String toPlainString() {

      String sql =  format("FROM %s", join(", ", (CharSequence[]) tables));

      if (joins != null) {
         sql = sql
            .concat(" ")
            .concat(joins.toPlainString());
      }

      return sql;
   }
}
