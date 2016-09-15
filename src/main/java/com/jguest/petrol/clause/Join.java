package com.jguest.petrol.clause;

import com.jguest.petrol.Stringable;

import static java.lang.String.format;

/**
 * @author jguest
 */
public class Join implements Stringable {

   public enum JoinType {

      DEFAULT,
      INNER,
      LEFT_OUTER,
      RIGHT_OUTER,
      FULL_OUTER,
      CROSS;

      protected String toSQLString() {

         if (this == DEFAULT) {
            return "JOIN";
         } else {
            return format("%s %s", this.toString(), "JOIN");
         }
      }
   }

   private String table;
   private JoinType joinType;

   public Join(String table, JoinType type) {
      this.table = table;
      this.joinType = type;
   }

   @Override
   public String toPlainString() {
      return format("%s %s", joinType.toSQLString(), table);
   }
}
