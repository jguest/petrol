## petrol

because maintaining strings of SQL leads to :shit:

### install

**Maven instructions incoming.**

### use it as a query builder

```java
Petrol.select("*").from("table").toPlainString();
```

will produce:

```sql
SELECT * FROM table;
```

"Oh, that's even longer than writing the SQL! Why would you want that?"

Because

```java
String sql = "select m.* " +
   "from flarbs f " +
   "inner join scrabs s on f.id = s.flarbs_id " + // <-- accidentally miss a space and you die
   "outer join medals m on s.medals_id = m.id " +
   "where f.teacher_id = :teacher_id and s.teacher_type_id = 42";
```

:flushed: :gun:

Here's the same query with petrol:

```Java
String sql = select("m*")
   .from("flarbs f", joins -> joins
      .inner("scrabs s").on("f.id = s.flarbs.id")
      .outer("medals m").on("s.medals_id = m.id")
   .where(conditions -> conditions
      .apply("f.teacher_id = :teacher_id")
      .and("s.teacher_type_id = 42")
   .toPlainString();
```

Incoming features

* JPA integration
* better support for sub-queries
* group by
* count
* distinct
* better documentation
