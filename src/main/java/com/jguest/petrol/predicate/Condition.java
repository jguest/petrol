package com.jguest.petrol.predicate;

import com.jguest.petrol.Stringable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.jguest.petrol.Keywords.AND;
import static com.jguest.petrol.Keywords.OR;

/**
 * @author jguest
 */
public class Condition implements Stringable {

   List<Stringable> expression = new ArrayList<>();

   public Condition apply(String condition) {
      expression.add(() -> condition);
      return this;
   }

   public Condition and(String condition) {
      expression.add(AND);
      expression.add(() -> condition);
      return this;
   }

   public Condition and(Function<Condition, Condition> condition) {
      expression.add(AND);
      expression.add(() -> "(");
      expression.add(condition.apply(new Condition()));
      expression.add(() -> ")");
      return this;
   }

   public Condition or(String condition) {
      expression.add(OR);
      expression.add(() -> condition);
      return this;
   }

   @Override
   public String toPlainString() {
      return String.join(" ", expression.stream()
         .map(Stringable::toPlainString)
         .collect(Collectors.toList()));
   }
}
