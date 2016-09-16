package com.jguest.petrol;

import javax.persistence.EntityManager;
import java.util.function.Function;

/**
 * @author jguest
 */
public class Petrol {

   private final EntityManager entityManager;

   public Petrol(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   /**
    * Creates runnable query instances
    *
    * @param target entity
    * @param query function providing query
    * @param <T> type of entity
    *
    * @return runnable query
    */
   public <T> Results<T> query(Class<T> target, Function<QueryBuilder, QueryBuilder> query) {
      Results<T> rq = new Results<>(target, entityManager);
      query.apply(rq.queryBuilder);
      return rq;
   }
}
