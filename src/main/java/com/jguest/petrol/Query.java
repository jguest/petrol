package com.jguest.petrol;

import com.jguest.petrol.clause.*;
import com.jguest.petrol.predicate.*;
import com.jguest.petrol.struct.Statement;

import java.util.function.Function;

/**
 * @author jguest
 */
public class Query implements Stringable {

   private final Statement statement;

   public Query() {
      this.statement = new Statement();
   }

   /**
    * Helper method to enqueue stringable and return self
    * @param stringable to enqueue
    * @return this
    */
   private Query add(Stringable stringable) {
      statement.enqueue(stringable);
      return this;
   }

   /**
    * SELECT column(s)
    * @param columns column or columns to select
    * @return this
    */
   public Query select(String... columns) {
      return add(new Select(columns));
   }

   /**
    * SELECT table(s)
    * @param tables table or tables from which to select
    * @return this
    */
   public Query from(String... tables) {
      return add(new From(tables));
   }

   /**
    * FROM table JOIN(s)
    * @param table from which to select
    * @param joins function to populate join clauses
    * @return this
    */
   public Query from(String table, Function<Table, Table> joins) {
      From from = new From(new String[]{ table });
      from.joins(joins.apply(new Table()));
      return add(from);
   }

   /**
    * WHERE expression
    * @param expression to
    * @return this
    */
   public Query where(String expression) {
      Where where = new Where(new Condition().apply(expression));
      return add(where);
   }

   /**
    * WHERE expression [and, or]
    * @param condition function to populate where predicate
    * @return this
    */
   public Query where(String expression, Function<Condition, Condition> condition) {
      Where where = new Where(condition
         .apply(new Condition().apply(expression)));
      return add(where);
   }

   /**
    * Get the raw SQL string for built query
    * @return string
    */
   @Override
   public String toPlainString() {
      return statement.toPlainString();
   }
}
