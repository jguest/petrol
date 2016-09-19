import com.jguest.petrol.Petrol;
import com.jguest.petrol.Query;
import org.junit.Test;

import javax.persistence.EntityManager;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author jguest
 */
public class PetrolTest {

   private void log(String message) {
      System.out.println(message);
   }

   @Test
   public void testBunnies() {

      String sql = new Query()
         .select("*")
         .from("bunnies")
         .toPlainString();

      log(sql);
      assertEquals("SELECT * FROM bunnies", sql);
   }

   @Test
   public void testCrazyManlyBunnies() {

      String sql = new Query()
         .select("b.*")
         .from("bunnies b")
         .where("b.age > 18", condition ->
            condition.and("b.type = 'rabid'"))
         .toPlainString();

      log(sql);
      assertEquals("SELECT b.* FROM bunnies b WHERE b.age > 18 AND b.type = 'rabid'", sql);
   }

   @Test
   public void testLigers() {

      String sql = new Query()
         .select("t.id", "l.id")
         .from("tigers t", table ->
            table.innerJoin("lions l").on("t.lion_id = l.id"))
         .toPlainString();

      log(sql);
      assertEquals("SELECT t.id, l.id FROM tigers t INNER JOIN lions l ON t.lion_id = l.id", sql);
   }

   class Sloth {

      private Integer slownessLevel;

      public Sloth(Integer slownessLevel) {
         this.slownessLevel = slownessLevel;
      }

      public String repeat(String message) {

         StringBuilder toRepeat = new StringBuilder();
         char[] chars = message.toCharArray();

         for (char aChar : chars)
            if (aChar == ' ') toRepeat.append(aChar);
            else for (int j = 0; j < slownessLevel; j++)
               toRepeat.append(aChar);

         return toRepeat.toString();
      }
   }
   @Test
   public void testSlothQuery() {

      javax.persistence.Query mockedQuery = mock(javax.persistence.Query.class);
      when(mockedQuery.getResultList()).thenReturn(Arrays.asList(new Sloth(3)));

      EntityManager entityManager = mock(EntityManager.class);
      when(entityManager.createNativeQuery(anyString(), eq(Sloth.class))).thenReturn(mockedQuery);

      Petrol petrol = new Petrol(entityManager);

      petrol.stream(Sloth.class, query ->
         query.select("s.*")
            .from("sloths s")
            .where("name = 'Kristen Bell'")
         )
         .findFirst()
         .ifPresent(sloth ->
            assertEquals("hhheeellllllooo wwwooorrrlllddd", sloth.repeat("hello world"))
         );

   }

   @Test
   public void testSpecificSloth() {

      String sql = new Query()
         .select("s.*")
         .from("sloths s", "slowness n")
         .where("s.slowness_id = n.id", condition ->
            condition.and(nested ->
               nested.apply("s.name = 'Kristen Bell'").or("n.level = 1.5")))
         .toPlainString();

      log(sql);
      assertEquals("SELECT s.* FROM sloths s, slowness n WHERE s.slowness_id = n.id AND ( s.name = 'Kristen Bell' OR n.level = 1.5 )", sql);
   }

}
