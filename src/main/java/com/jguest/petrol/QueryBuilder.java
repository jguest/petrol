package com.jguest.petrol;

import com.jguest.petrol.clause.*;
import com.jguest.petrol.predicate.*;
import com.jguest.petrol.struct.Statement;

import java.util.function.Function;

/**
 * @author jguest
 */
public class QueryBuilder implements Stringable {

   private final Statement statement;

   public QueryBuilder() {
      this.statement = new Statement();
   }

   /**
    * Helper method to enqueue stringable and return self
    * @param stringable to enqueue
    * @return this
    */
   private QueryBuilder add(Stringable stringable) {
      statement.enqueue(stringable);
      return this;
   }

   /**
    * SELECT column(s)
    * @param columns column or columns to select
    * @return this
    */
   public QueryBuilder select(String... columns) {
      return add(new Select(columns));
   }

   /**
    * SELECT table(s)
    * @param tables table or tables from which to select
    * @return this
    */
   public QueryBuilder from(String... tables) {
      return add(new From(tables));
   }

   /**
    * FROM table JOIN(s)
    * @param table from which to select
    * @param joins function to populate join clauses
    * @return this
    */
   public QueryBuilder from(String table, Function<Table, Table> joins) {
      From from = new From(new String[]{ table });
      from.joins(joins.apply(new Table()));
      return add(from);
   }

   /**
    * WHERE condition(s)
    * @param conditions function to populate conditions
    * @return this
    */
   public QueryBuilder where(Function<Conditions, Conditions> conditions) {
      Where where = new Where(conditions.apply(new Conditions()));
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
