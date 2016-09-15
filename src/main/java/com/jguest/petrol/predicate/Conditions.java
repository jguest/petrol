package com.jguest.petrol.predicate;

import com.jguest.petrol.Stringable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

/**
 * @author jguest
 */
public class Conditions implements Stringable {

   List<String> conditions = new ArrayList<>();

   public Conditions apply(String condition) {
      this.conditions.add(condition);
      return this;
   }

   public Conditions and(String condition) {
      this.conditions.add("AND");
      this.conditions.add(condition);
      return this;
   }

   public Conditions or(String condition) {
      this.conditions.add("OR");
      this.conditions.add(condition);
      return this;
   }

   @Override
   public String toPlainString() {
      return join(" ", conditions);
   }
}
