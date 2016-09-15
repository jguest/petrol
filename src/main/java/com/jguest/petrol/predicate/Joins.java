package com.jguest.petrol.predicate;

import com.jguest.petrol.Stringable;
import com.jguest.petrol.clause.Join;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

/**
 * @author jguest
 */
public class Joins implements Stringable {

   List<Stringable> stringables = new ArrayList<>();

   public Joins inner(String table) {
      stringables.add(new Join(table, Join.JoinType.INNER));
      return this;
   }

   public Joins on(String condition) {
      stringables.add(new On(condition));
      return this;
   }

   @Override
   public String toPlainString() {
      return join(" ", stringables
         .stream()
         .map(Stringable::toPlainString)
         .collect(toList()));
   }
}
