import org.junit.Test;

import static com.jguest.petrol.Petrol.select;
import static org.junit.Assert.assertEquals;

/**
 * @author jguest
 */
public class PetrolTest {

   private void log(String message) {
      System.out.println(message);
   }

   @Test
   public void testBunnies() {

      String sql = select("*")
         .from("bunnies")
         .toPlainString();

      log(sql);
      assertEquals("SELECT * FROM bunnies", sql);
   }

   @Test
   public void testCrazyManlyBunnies() {

      String sql = select("b.*")
         .from("bunnies b")
         .where(conditions ->
            conditions.apply("b.age > 18").and("b.type = 'rabid'"))
         .toPlainString();

      log(sql);
      assertEquals("SELECT b.* FROM bunnies b WHERE b.age > 18 AND b.type = 'rabid'", sql);
   }

   @Test
   public void testLigers() {

      String sql = select("t.id", "l.id")
         .from("tigers t", joins ->
            joins.inner("lions l").on("t.lion_id = l.id"))
         .toPlainString();

      log(sql);
      assertEquals("SELECT t.id, l.id FROM tigers t INNER JOIN lions l ON t.lion_id = l.id", sql);
   }
}
