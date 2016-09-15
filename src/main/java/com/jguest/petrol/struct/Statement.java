package com.jguest.petrol.struct;

import com.jguest.petrol.Stringable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * @author jguest
 */
public class Statement implements SimpleQueue<Stringable>, Stringable {

   private Deque<Stringable> deque;

   public Statement() {
      this.deque = new ArrayDeque<>();
   }

   @Override
   public void enqueue(Stringable stringable) {
      deque.add(stringable);
   }

   @Override
   public String toPlainString() {
      return String
         .join(" ", deque
            .stream()
            .map(Stringable::toPlainString)
            .collect(Collectors
               .toList()));
   }
}
