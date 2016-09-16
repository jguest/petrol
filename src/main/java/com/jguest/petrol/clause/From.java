package com.jguest.petrol.clause;

import com.jguest.petrol.Stringable;
import com.jguest.petrol.predicate.Table;

import static java.lang.String.format;
import static java.lang.String.join;

/**
 * @author jguest
 */
public class From implements Stringable {

   private String[] tables;
   private Table table;

   public From(String[] tables) {
      this.tables = tables;
   }

   public void joins(Table table) {
      this.table = table;
   }

   @Override
   public String toPlainString() {

      String sql = format("FROM %s", join(", ", (CharSequence[]) tables));

      if (table != null) {
         sql = sql
            .concat(" ")
            .concat(table.toPlainString());
      }

      return sql;
   }
}
