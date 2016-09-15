package com.jguest.petrol;

import com.jguest.petrol.clause.*;
import com.jguest.petrol.predicate.*;
import com.jguest.petrol.struct.Statement;

import java.util.function.Function;

/**
 * @author jguest
 */
public class Query implements Stringable {

   private Statement statement;

   public Query() {
      this.statement = new Statement();
   }

   private Query add(Stringable stringable) {
      statement.enqueue(stringable);
      return this;
   }

   /**
    *
    * @param columns
    * @return
    */
   public Query select(String... columns) {
      return add(new Select(columns));
   }

   /**
    *
    * @param tables
    * @return
    */
   public Query from(String... tables) {
      return add(new From(tables));
   }


   /**
    *
    * @param table
    * @param joins
    * @return
    */
   public Query from(String table, Function<Joins, Joins> joins) {
      From from = new From(new String[]{ table });
      from.joins(joins.apply(new Joins()));
      return add(from);
   }

   /**
    *
    * @param conditions
    * @return
    */
   public Query where(Function<Conditions, Conditions> conditions) {
      Where where = new Where(conditions.apply(new Conditions()));
      return add(where);
   }

   /**
    *
    * @return
    */
   @Override
   public String toPlainString() {
      return statement.toPlainString();
   }
}
