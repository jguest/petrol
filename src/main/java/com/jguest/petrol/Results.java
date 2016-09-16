package com.jguest.petrol;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author jguest
 */
public class Results<T> {

   private final Class<T> target;
   private final EntityManager entityManager;

   final QueryBuilder queryBuilder;

   Results(Class<T> target, EntityManager entityManager) {
      this.target = target;
      this.entityManager = entityManager;
      this.queryBuilder = new QueryBuilder();
   }

   /**
    * Get a stream of all results after running query
    * @return stream of T
    */
   @SuppressWarnings("unchecked")
   public Stream<T> stream() {

      List<T> results = entityManager
         .createNativeQuery(queryBuilder.toPlainString(), target)
         .getResultList();

      return results == null ? Stream.empty() : results.stream();
   }
}
