import com.jguest.petrol.Petrol;
import com.jguest.petrol.QueryBuilder;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

      String sql = new QueryBuilder()
         .select("*")
         .from("bunnies")
         .toPlainString();

      log(sql);
      assertEquals("SELECT * FROM bunnies", sql);
   }

   @Test
   public void testCrazyManlyBunnies() {

      String sql = new QueryBuilder()
         .select("b.*")
         .from("bunnies b")
         .where(conditions ->
            conditions.apply("b.age > 18").and("b.type = 'rabid'"))
         .toPlainString();

      log(sql);
      assertEquals("SELECT b.* FROM bunnies b WHERE b.age > 18 AND b.type = 'rabid'", sql);
   }

   @Test
   public void testLigers() {

      String sql = new QueryBuilder()
         .select("t.id", "l.id")
         .from("tigers t", table ->
            table.innerJoin("lions l").on("t.lion_id = l.id"))
         .toPlainString();

      log(sql);
      assertEquals("SELECT t.id, l.id FROM tigers t INNER JOIN lions l ON t.lion_id = l.id", sql);
   }

   @Test
   public void testSlothQuery() {

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

      Query mockedQuery = mock(Query.class);
      when(mockedQuery.getResultList()).thenReturn(Arrays.asList(new Sloth(3)));

      EntityManager entityManager = mock(EntityManager.class);
      when(entityManager.createNativeQuery(anyString(), eq(Sloth.class))).thenReturn(mockedQuery);

      Petrol petrol = new Petrol(entityManager);

      petrol.query(Sloth.class, query ->
         query.select("*")
            .from("sloths s")
            .where(conditions ->
               conditions.apply("name = 'Kristen Bell'")
            )
         )
         .stream()
         .findFirst()
         .ifPresent(sloth ->
            assertEquals("hhheeellllllooo wwwooorrrlllddd", sloth.repeat("hello world"))
         );

   }
}
