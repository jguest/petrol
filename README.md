# Petrol

Because maintaining strings of SQL leads to :shit:

## Install

*Maven instructions incoming.*

## Use It

### As a query builder

This:

```java
new QueryBuilder().select("*").from("table");
```

will produce the following query:

```sql
SELECT * FROM table
```

*"Oh, that's even longer than writing the SQL! Why would you want that?"* 

Oh, because:

```java
String sql = "select m.* " + // <-- eww multiline concatenation
   "from flarbs f " +
   "inner join scrabs s on f.id = s.flarbs_id " + // <-- accidentally miss a space and you die
   "left outer join medals m on s.medals_id = m.id " +
   "where f.teacher_id = :teacher_id and s.teacher_type_id = 42";
```

:flushed: :gun:

And that was the neatest string of SQL I could find.

Here's the same query built with petrol:

```Java
String sql = new QueryBuilder()
   .select("m*")
   .from("flarbs f", (table) -> {
      table.innerJoin("scrabs s").on("f.id = s.flarbs.id");
      table.leftOuterJoin("medals m").on("s.medals_id = m.id")); })
   .where("f.teacher_id = :teacher_id", condition ->
      condition.and("s.teacher_type_id = 42"))
   .toPlainString();
```

*"That's nice, but why not use an ORM?"* I like ORMs for writes but prefer plain ol' SQL for reads.

### As a database interface

**Coming soon.**

## Incoming Features

* JPA integration
* better support for sub-queries
* group by
* count
* distinct
* better documentation
