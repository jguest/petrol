package com.jguest.petrol.predicate;

import com.jguest.petrol.Stringable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * @author jguest
 */
public class Criteria implements Stringable {

   public static String quote(String value) {
      return format("'%s'", value);
   }

   private List<Condition> conditions = new ArrayList<>();
   private List<String> separators = new ArrayList<>();

   public Criteria eq(String key, Object value) {
      conditions.add(new Condition(key, value, "="));
      return this;
   }

   public Criteria greaterThan(String column, Object compareTo) {
      conditions.add(new Condition(column, compareTo, ">"));
      return this;
   }

   public Criteria and() {
      separators.add(" AND ");
      return this;
   }

   public Criteria or() {
      separators.add(" OR ");
      return this;
   }

   @Override
   public String toPlainString() {

      StringBuilder str = new StringBuilder();

      for (int i = 0; i < conditions.size(); i++) {
         str.append(conditions.get(i).toPlainString());
         if (separators.size() > i) {
            str.append(separators.get(i));
         }
      }

      return str.toString();
   }
}
